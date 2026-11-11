package com.example.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object OverviewRoute

@Serializable
data object ProjectsRoute

@Serializable
data object JourneyRoute

@Serializable
data object SkillsRoute

@Serializable
data class ProjectDetailRoute(val projectId: String)
