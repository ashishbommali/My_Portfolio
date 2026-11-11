package com.example.model

import androidx.annotation.DrawableRes

enum class ThemePreference(val label: String) {
    SYSTEM("System"),
    LIGHT("Light"),
    DARK("Dark")
}

enum class ProjectCategory(val label: String) {
    ALL("All"),
    MOBILE("Mobile & UI"),
    SYSTEMS("Systems & API"),
    ARCHITECTURE("Architecture")
}

enum class MilestoneType(val label: String) {
    EXPERIENCE("Experience"),
    EDUCATION("Education")
}

enum class JourneyFilter(val label: String) {
    ALL("All"),
    EXPERIENCE("Experience"),
    EDUCATION("Education")
}

enum class SkillCategory(val label: String) {
    ALL("All"),
    LANGUAGES("Languages"),
    MOBILE_UI("Mobile & UI"),
    ARCHITECTURE_DATA("Architecture"),
    TOOLS("Cloud & Tools")
}

data class OwnerProfile(
    val name: String,
    val handle: String,
    val email: String,
    val role: String,
    val tagline: String,
    val aboutParagraphs: List<String>,
    val statusBadge: String,
    val repoUrl: String,
    val githubUrl: String,
    val focusPillars: List<String>
)

data class PortfolioProject(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: ProjectCategory,
    val period: String,
    val summary: String,
    val architectureNotes: List<String>,
    val outcomes: List<String>,
    val techStack: List<String>,
    @param:DrawableRes val artworkResId: Int,
    val repoUrl: String,
    val featured: Boolean
)

data class JourneyMilestone(
    val id: String,
    val type: MilestoneType,
    val title: String,
    val contextLabel: String,
    val period: String,
    val modeLabel: String,
    val summary: String,
    val highlights: List<String>,
    val tags: List<String>
)

data class SkillItem(
    val id: String,
    val name: String,
    val category: SkillCategory,
    val proficiencyPercent: Int,
    val levelLabel: String,
    val summary: String,
    val filterTag: String
)
