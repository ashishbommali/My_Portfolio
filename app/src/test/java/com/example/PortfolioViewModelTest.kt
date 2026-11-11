package com.example

import com.example.model.JourneyFilter
import com.example.model.MilestoneType
import com.example.model.ProjectCategory
import com.example.model.SkillCategory
import com.example.model.ThemePreference
import com.example.ui.PortfolioLoadState
import com.example.ui.PortfolioViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PortfolioViewModelTest {

    @Test
    fun initialPortfolioStateLoadsAllSections() {
        val viewModel = PortfolioViewModel()
        val state = (viewModel.loadState.value as PortfolioLoadState.Success).data

        assertEquals("Ashish Bommali", state.profile.name)
        assertEquals("ashishbommali@gmail.com", state.profile.email)
        assertTrue(state.allProjects.isNotEmpty())
        assertTrue(state.allMilestones.isNotEmpty())
        assertTrue(state.allSkills.isNotEmpty())
    }

    @Test
    fun filteringProjectsByCategoryAndSkillTagUpdatesResults() {
        val viewModel = PortfolioViewModel()

        viewModel.selectProjectCategory(ProjectCategory.ARCHITECTURE)
        var state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertTrue(state.filteredProjects.all { it.category == ProjectCategory.ARCHITECTURE })

        viewModel.applySkillFilterToProjects("Serialization")
        state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertEquals("Serialization", state.activeSkillTagFilter)
        assertTrue(state.filteredProjects.isNotEmpty())

        viewModel.clearProjectFilters()
        state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertEquals(state.allProjects.size, state.filteredProjects.size)
    }

    @Test
    fun journeySegmentedFilterAndExpandToggleWork() {
        val viewModel = PortfolioViewModel()

        viewModel.selectJourneyFilter(JourneyFilter.EDUCATION)
        var state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertTrue(state.filteredMilestones.all { it.type == MilestoneType.EDUCATION })

        val targetId = state.filteredMilestones.first().id
        viewModel.toggleMilestoneExpanded(targetId)
        state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertTrue(targetId in state.expandedMilestoneIds)
    }

    @Test
    fun skillCategorySearchAndThemeCycleUpdateState() {
        val viewModel = PortfolioViewModel()

        viewModel.selectSkillCategory(SkillCategory.LANGUAGES)
        var state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertTrue(state.filteredSkills.all { it.category == SkillCategory.LANGUAGES })

        viewModel.updateSkillSearchQuery("Kotlin")
        state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertTrue(state.filteredSkills.any { it.name.contains("Kotlin", ignoreCase = true) })

        viewModel.cycleThemePreference()
        state = (viewModel.loadState.value as PortfolioLoadState.Success).data
        assertEquals(ThemePreference.LIGHT, state.themePreference)

        assertNotNull(viewModel.getProjectById("my_portfolio_android"))
    }
}
