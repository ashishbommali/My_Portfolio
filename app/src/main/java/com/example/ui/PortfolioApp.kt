package com.example.ui

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.R
import com.example.model.JourneyFilter
import com.example.ui.components.ConnectBottomSheet
import com.example.ui.navigation.JourneyRoute
import com.example.ui.navigation.OverviewRoute
import com.example.ui.navigation.ProjectDetailRoute
import com.example.ui.navigation.ProjectsRoute
import com.example.ui.navigation.SkillsRoute
import com.example.ui.screens.JourneyScreen
import com.example.ui.screens.OverviewScreen
import com.example.ui.screens.ProjectDetailScreen
import com.example.ui.screens.ProjectsScreen
import com.example.ui.screens.SkillsScreen
import kotlinx.coroutines.launch

private data class TopLevelDestination(
    val route: Any,
    @param:StringRes val labelRes: Int,
    @param:DrawableRes val iconRes: Int,
    val testTag: String
)

private val topLevelDestinations = listOf(
    TopLevelDestination(
        route = OverviewRoute,
        labelRes = R.string.nav_overview,
        iconRes = R.drawable.ic_overview,
        testTag = "nav_item_overview"
    ),
    TopLevelDestination(
        route = ProjectsRoute,
        labelRes = R.string.nav_projects,
        iconRes = R.drawable.ic_folder,
        testTag = "nav_item_projects"
    ),
    TopLevelDestination(
        route = JourneyRoute,
        labelRes = R.string.nav_journey,
        iconRes = R.drawable.ic_work,
        testTag = "nav_item_journey"
    ),
    TopLevelDestination(
        route = SkillsRoute,
        labelRes = R.string.nav_skills,
        iconRes = R.drawable.ic_skills,
        testTag = "nav_item_skills"
    )
)

@Composable
fun PortfolioApp(
    loadState: PortfolioLoadState,
    viewModel: PortfolioViewModel,
    modifier: Modifier = Modifier
) {
    when (loadState) {
        is PortfolioLoadState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PortfolioLoadState.Error -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = loadState.message,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Button(onClick = { viewModel.loadPortfolio() }) {
                        Text("Retry")
                    }
                }
            }
        }

        is PortfolioLoadState.Success -> {
            PortfolioShell(
                uiState = loadState.data,
                viewModel = viewModel,
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PortfolioShell(
    uiState: PortfolioUiState,
    viewModel: PortfolioViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = backStackEntry?.destination
    val isDetailRoute = currentDestination?.hasRoute(ProjectDetailRoute::class) == true

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val showFeedback: (String) -> Unit = { msg ->
        coroutineScope.launch {
            snackbarHostState.showSnackbar(msg)
        }
    }

    val openUrl: (String) -> Unit = { url ->
        launchExternalUrl(
            context = context,
            url = url,
            onError = { showFeedback(context.getString(R.string.connect_no_browser_app)) }
        )
    }

    val sendEmail: () -> Unit = {
        launchEmailComposer(
            context = context,
            email = uiState.profile.email,
            subject = "Reaching out via ${uiState.profile.name}'s Portfolio",
            onError = { showFeedback(context.getString(R.string.connect_no_email_app)) }
        )
    }

    val sharePortfolio: () -> Unit = {
        launchShareSheet(
            context = context,
            shareText = "${uiState.profile.name} — ${uiState.profile.role}\n" +
                "${uiState.profile.tagline}\n${uiState.profile.repoUrl}"
        )
    }

    val copyEmail: () -> Unit = {
        val clipboard = context.getSystemService(ClipboardManager::class.java)
        clipboard?.setPrimaryClip(ClipData.newPlainText("Portfolio Email", uiState.profile.email))
        showFeedback(context.getString(R.string.connect_email_copied))
    }

    val activeTitle = when {
        currentDestination?.hasRoute(ProjectsRoute::class) == true -> stringResource(R.string.nav_projects)
        currentDestination?.hasRoute(JourneyRoute::class) == true -> stringResource(R.string.nav_journey)
        currentDestination?.hasRoute(SkillsRoute::class) == true -> stringResource(R.string.nav_skills)
        else -> stringResource(R.string.app_name)
    }

    if (uiState.isConnectSheetVisible) {
        ConnectBottomSheet(
            profile = uiState.profile,
            themePreference = uiState.themePreference,
            onDismiss = { viewModel.setConnectSheetVisible(false) },
            onEmailClick = {
                viewModel.setConnectSheetVisible(false)
                sendEmail()
            },
            onCopyEmailClick = copyEmail,
            onOpenRepoClick = {
                viewModel.setConnectSheetVisible(false)
                openUrl(uiState.profile.repoUrl)
            },
            onShareClick = {
                viewModel.setConnectSheetVisible(false)
                sharePortfolio()
            },
            onThemeSelected = viewModel::setThemePreference
        )
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val useNavigationRail = maxWidth >= 600.dp

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                if (!isDetailRoute) {
                    TopAppBar(
                        title = {
                            Column {
                                Text(
                                    text = activeTitle,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = { viewModel.cycleThemePreference() },
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("theme_toggle_button")
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_palette),
                                    contentDescription = stringResource(R.string.action_theme_toggle)
                                )
                            }
                            IconButton(
                                onClick = { viewModel.setConnectSheetVisible(true) },
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("connect_sheet_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = stringResource(R.string.action_connect)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            },
            bottomBar = {
                if (!isDetailRoute && !useNavigationRail) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        windowInsets = WindowInsets.navigationBars
                    ) {
                        topLevelDestinations.forEach { destination ->
                            val selected = currentDestination?.hierarchy?.any {
                                it.hasRoute(destination.route::class)
                            } == true
                            val label = stringResource(destination.labelRes)
                            NavigationBarItem(
                                selected = selected,
                                onClick = { navigateToTopLevel(navController, destination.route) },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = destination.iconRes),
                                        contentDescription = label,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = label,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                modifier = Modifier.testTag(destination.testTag)
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (!isDetailRoute && useNavigationRail) {
                    NavigationRail(
                        modifier = Modifier
                            .fillMaxHeight()
                            .windowInsetsPadding(WindowInsets.navigationBars),
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        Spacer(modifier = Modifier.weight(0.2f))
                        topLevelDestinations.forEach { destination ->
                            val selected = currentDestination?.hierarchy?.any {
                                it.hasRoute(destination.route::class)
                            } == true
                            val label = stringResource(destination.labelRes)
                            NavigationRailItem(
                                selected = selected,
                                onClick = { navigateToTopLevel(navController, destination.route) },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = destination.iconRes),
                                        contentDescription = label,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                label = { Text(label) },
                                modifier = Modifier.testTag(destination.testTag)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = OverviewRoute,
                    modifier = Modifier.weight(1f)
                ) {
                    composable<OverviewRoute> {
                        OverviewScreen(
                            uiState = uiState,
                            onNavigateToProjects = {
                                navigateToTopLevel(navController, ProjectsRoute)
                            },
                            onNavigateToProjectDetail = { projectId ->
                                navController.navigate(ProjectDetailRoute(projectId))
                            },
                            onNavigateToJourney = { filter: JourneyFilter ->
                                viewModel.selectJourneyFilter(filter)
                                navigateToTopLevel(navController, JourneyRoute)
                            },
                            onNavigateToSkills = {
                                navigateToTopLevel(navController, SkillsRoute)
                            },
                            onEmailClick = sendEmail,
                            onGitHubClick = { openUrl(uiState.profile.repoUrl) },
                            onShareClick = sharePortfolio
                        )
                    }

                    composable<ProjectsRoute> {
                        ProjectsScreen(
                            uiState = uiState,
                            onSearchQueryChange = viewModel::updateProjectSearchQuery,
                            onCategorySelected = viewModel::selectProjectCategory,
                            onClearFilters = viewModel::clearProjectFilters,
                            onProjectClick = { projectId ->
                                navController.navigate(ProjectDetailRoute(projectId))
                            }
                        )
                    }

                    composable<JourneyRoute> {
                        JourneyScreen(
                            uiState = uiState,
                            onFilterSelected = viewModel::selectJourneyFilter,
                            onToggleMilestone = viewModel::toggleMilestoneExpanded
                        )
                    }

                    composable<SkillsRoute> {
                        SkillsScreen(
                            uiState = uiState,
                            onSearchQueryChange = viewModel::updateSkillSearchQuery,
                            onCategorySelected = viewModel::selectSkillCategory,
                            onClearFilters = viewModel::clearSkillFilters,
                            onExploreSkillProjects = { skillTag ->
                                viewModel.applySkillFilterToProjects(skillTag)
                                navigateToTopLevel(navController, ProjectsRoute)
                            }
                        )
                    }

                    composable<ProjectDetailRoute> { entry ->
                        val route = entry.toRoute<ProjectDetailRoute>()
                        val project = viewModel.getProjectById(route.projectId)
                        if (project != null) {
                            ProjectDetailScreen(
                                project = project,
                                onBackClick = { navController.popBackStack() },
                                onOpenRepoClick = openUrl,
                                onTechTagClick = { techTag ->
                                    viewModel.applySkillFilterToProjects(techTag)
                                    navController.popBackStack()
                                    navigateToTopLevel(navController, ProjectsRoute)
                                }
                            )
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Project not found")
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun navigateToTopLevel(navController: NavHostController, route: Any) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun launchExternalUrl(
    context: Context,
    url: String,
    onError: () -> Unit
) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        onError()
    }
}

private fun launchEmailComposer(
    context: Context,
    email: String,
    subject: String,
    onError: () -> Unit
) {
    val mailUri = Uri.Builder()
        .scheme("mailto")
        .opaquePart(email)
        .build()
    val intent = Intent(Intent.ACTION_SENDTO, mailUri).apply {
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    try {
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        onError()
    }
}

private fun launchShareSheet(
    context: Context,
    shareText: String
) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
    }
    val chooser = Intent.createChooser(sendIntent, context.getString(R.string.action_share))
    try {
        context.startActivity(chooser)
    } catch (_: ActivityNotFoundException) {
        // Ignore if no share target exists
    }
}
