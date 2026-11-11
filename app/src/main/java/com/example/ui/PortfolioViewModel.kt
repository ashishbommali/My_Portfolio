package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.PortfolioRepository
import com.example.model.JourneyFilter
import com.example.model.JourneyMilestone
import com.example.model.MilestoneType
import com.example.model.OwnerProfile
import com.example.model.PortfolioProject
import com.example.model.ProjectCategory
import com.example.model.SkillCategory
import com.example.model.SkillItem
import com.example.model.ThemePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface PortfolioLoadState {
    data object Loading : PortfolioLoadState
    data class Success(val data: PortfolioUiState) : PortfolioLoadState
    data class Error(val message: String) : PortfolioLoadState
}

data class PortfolioUiState(
    val profile: OwnerProfile,
    val allProjects: List<PortfolioProject>,
    val filteredProjects: List<PortfolioProject>,
    val allMilestones: List<JourneyMilestone>,
    val filteredMilestones: List<JourneyMilestone>,
    val allSkills: List<SkillItem>,
    val filteredSkills: List<SkillItem>,
    val projectSearchQuery: String = "",
    val selectedProjectCategory: ProjectCategory = ProjectCategory.ALL,
    val activeSkillTagFilter: String? = null,
    val journeyFilter: JourneyFilter = JourneyFilter.ALL,
    val expandedMilestoneIds: Set<String> = emptySet(),
    val skillSearchQuery: String = "",
    val selectedSkillCategory: SkillCategory = SkillCategory.ALL,
    val themePreference: ThemePreference = ThemePreference.SYSTEM,
    val isConnectSheetVisible: Boolean = false
) {
    val experienceCount: Int
        get() = allMilestones.count { it.type == MilestoneType.EXPERIENCE }

    val educationCount: Int
        get() = allMilestones.count { it.type == MilestoneType.EDUCATION }
}

class PortfolioViewModel(
    private val repository: PortfolioRepository = PortfolioRepository()
) : ViewModel() {

    private val _loadState = MutableStateFlow<PortfolioLoadState>(PortfolioLoadState.Loading)
    val loadState: StateFlow<PortfolioLoadState> = _loadState.asStateFlow()

    init {
        loadPortfolio()
    }

    fun loadPortfolio() {
        runCatching {
            val profile = repository.getOwnerProfile()
            val projects = repository.getProjects()
            val milestones = repository.getMilestones()
            val skills = repository.getSkills()
            PortfolioUiState(
                profile = profile,
                allProjects = projects,
                filteredProjects = projects,
                allMilestones = milestones,
                filteredMilestones = milestones,
                allSkills = skills,
                filteredSkills = skills,
                expandedMilestoneIds = setOfNotNull(milestones.firstOrNull()?.id)
            )
        }.onSuccess { initialState ->
            _loadState.value = PortfolioLoadState.Success(initialState)
        }.onFailure { throwable ->
            _loadState.value = PortfolioLoadState.Error(
                throwable.message ?: "Unable to load portfolio content."
            )
        }
    }

    fun getProjectById(projectId: String): PortfolioProject? {
        val current = (_loadState.value as? PortfolioLoadState.Success)?.data
        return current?.allProjects?.firstOrNull { it.id == projectId }
            ?: repository.getProjectById(projectId)
    }

    fun updateProjectSearchQuery(query: String) {
        updateSuccessState { state ->
            val updated = state.copy(projectSearchQuery = query)
            updated.copy(filteredProjects = filterProjects(updated))
        }
    }

    fun selectProjectCategory(category: ProjectCategory) {
        updateSuccessState { state ->
            val updated = state.copy(selectedProjectCategory = category)
            updated.copy(filteredProjects = filterProjects(updated))
        }
    }

    fun applySkillFilterToProjects(skillTag: String) {
        updateSuccessState { state ->
            val updated = state.copy(
                selectedProjectCategory = ProjectCategory.ALL,
                projectSearchQuery = "",
                activeSkillTagFilter = skillTag
            )
            updated.copy(filteredProjects = filterProjects(updated))
        }
    }

    fun clearProjectFilters() {
        updateSuccessState { state ->
            val updated = state.copy(
                projectSearchQuery = "",
                selectedProjectCategory = ProjectCategory.ALL,
                activeSkillTagFilter = null
            )
            updated.copy(filteredProjects = filterProjects(updated))
        }
    }

    fun selectJourneyFilter(filter: JourneyFilter) {
        updateSuccessState { state ->
            val filtered = when (filter) {
                JourneyFilter.ALL -> state.allMilestones
                JourneyFilter.EXPERIENCE -> state.allMilestones.filter { it.type == MilestoneType.EXPERIENCE }
                JourneyFilter.EDUCATION -> state.allMilestones.filter { it.type == MilestoneType.EDUCATION }
            }
            state.copy(
                journeyFilter = filter,
                filteredMilestones = filtered
            )
        }
    }

    fun toggleMilestoneExpanded(milestoneId: String) {
        updateSuccessState { state ->
            val updatedIds = if (milestoneId in state.expandedMilestoneIds) {
                state.expandedMilestoneIds - milestoneId
            } else {
                state.expandedMilestoneIds + milestoneId
            }
            state.copy(expandedMilestoneIds = updatedIds)
        }
    }

    fun updateSkillSearchQuery(query: String) {
        updateSuccessState { state ->
            val updated = state.copy(skillSearchQuery = query)
            updated.copy(filteredSkills = filterSkills(updated))
        }
    }

    fun selectSkillCategory(category: SkillCategory) {
        updateSuccessState { state ->
            val updated = state.copy(selectedSkillCategory = category)
            updated.copy(filteredSkills = filterSkills(updated))
        }
    }

    fun clearSkillFilters() {
        updateSuccessState { state ->
            val updated = state.copy(
                skillSearchQuery = "",
                selectedSkillCategory = SkillCategory.ALL
            )
            updated.copy(filteredSkills = filterSkills(updated))
        }
    }

    fun cycleThemePreference() {
        updateSuccessState { state ->
            val next = when (state.themePreference) {
                ThemePreference.SYSTEM -> ThemePreference.LIGHT
                ThemePreference.LIGHT -> ThemePreference.DARK
                ThemePreference.DARK -> ThemePreference.SYSTEM
            }
            state.copy(themePreference = next)
        }
    }

    fun setThemePreference(preference: ThemePreference) {
        updateSuccessState { state ->
            state.copy(themePreference = preference)
        }
    }

    fun setConnectSheetVisible(visible: Boolean) {
        updateSuccessState { state ->
            state.copy(isConnectSheetVisible = visible)
        }
    }

    private fun updateSuccessState(transform: (PortfolioUiState) -> PortfolioUiState) {
        _loadState.update { current ->
            if (current is PortfolioLoadState.Success) {
                PortfolioLoadState.Success(transform(current.data))
            } else {
                current
            }
        }
    }

    private fun filterProjects(state: PortfolioUiState): List<PortfolioProject> {
        val query = state.projectSearchQuery.trim().lowercase()
        val skillTag = state.activeSkillTagFilter?.trim()?.lowercase()
        return state.allProjects.filter { project ->
            val matchesCategory = state.selectedProjectCategory == ProjectCategory.ALL ||
                project.category == state.selectedProjectCategory
            val matchesSkill = skillTag == null ||
                project.techStack.any { it.lowercase().contains(skillTag) } ||
                project.title.lowercase().contains(skillTag) ||
                project.summary.lowercase().contains(skillTag)
            val matchesQuery = query.isEmpty() ||
                project.title.lowercase().contains(query) ||
                project.subtitle.lowercase().contains(query) ||
                project.summary.lowercase().contains(query) ||
                project.techStack.any { it.lowercase().contains(query) }
            matchesCategory && matchesSkill && matchesQuery
        }
    }

    private fun filterSkills(state: PortfolioUiState): List<SkillItem> {
        val query = state.skillSearchQuery.trim().lowercase()
        return state.allSkills.filter { skill ->
            val matchesCategory = state.selectedSkillCategory == SkillCategory.ALL ||
                skill.category == state.selectedSkillCategory
            val matchesQuery = query.isEmpty() ||
                skill.name.lowercase().contains(query) ||
                skill.summary.lowercase().contains(query) ||
                skill.category.label.lowercase().contains(query)
            matchesCategory && matchesQuery
        }
    }
}
