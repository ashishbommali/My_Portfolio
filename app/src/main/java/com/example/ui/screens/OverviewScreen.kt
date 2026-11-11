package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.JourneyFilter
import com.example.model.JourneyMilestone
import com.example.model.MilestoneType
import com.example.model.PortfolioProject
import com.example.ui.PortfolioUiState
import com.example.ui.components.EditorialSectionHeader
import com.example.ui.components.MetricSummaryCard
import com.example.ui.components.TechTagChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OverviewScreen(
    uiState: PortfolioUiState,
    onNavigateToProjects: () -> Unit,
    onNavigateToProjectDetail: (String) -> Unit,
    onNavigateToJourney: (JourneyFilter) -> Unit,
    onNavigateToSkills: () -> Unit,
    onEmailClick: () -> Unit,
    onGitHubClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profile = uiState.profile
    val featuredProjects = uiState.allProjects.filter { it.featured }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 860.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HeroProfileSection(
                    name = profile.name,
                    handle = profile.handle,
                    role = profile.role,
                    statusBadge = profile.statusBadge,
                    tagline = profile.tagline,
                    onEmailClick = onEmailClick,
                    onGitHubClick = onGitHubClick,
                    onShareClick = onShareClick
                )
            }

            item {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val isWide = maxWidth >= 560.dp
                    if (isWide) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            MetricSummaryCard(
                                value = "${uiState.allProjects.size}",
                                label = stringResource(R.string.nav_projects),
                                iconRes = R.drawable.ic_folder,
                                onClick = onNavigateToProjects,
                                testTag = "metric_projects_card",
                                modifier = Modifier.weight(1f)
                            )
                            MetricSummaryCard(
                                value = "${uiState.experienceCount}",
                                label = stringResource(R.string.filter_experience),
                                iconRes = R.drawable.ic_work,
                                onClick = { onNavigateToJourney(JourneyFilter.EXPERIENCE) },
                                testTag = "metric_experience_card",
                                modifier = Modifier.weight(1f),
                                useSecondaryAccent = true
                            )
                            MetricSummaryCard(
                                value = "${uiState.educationCount}",
                                label = stringResource(R.string.filter_education),
                                iconRes = R.drawable.ic_school,
                                onClick = { onNavigateToJourney(JourneyFilter.EDUCATION) },
                                testTag = "metric_education_card",
                                modifier = Modifier.weight(1f)
                            )
                            MetricSummaryCard(
                                value = "${uiState.allSkills.size}",
                                label = stringResource(R.string.nav_skills),
                                iconRes = R.drawable.ic_skills,
                                onClick = onNavigateToSkills,
                                testTag = "metric_skills_card",
                                modifier = Modifier.weight(1f),
                                useSecondaryAccent = true
                            )
                        }
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                MetricSummaryCard(
                                    value = "${uiState.allProjects.size}",
                                    label = stringResource(R.string.nav_projects),
                                    iconRes = R.drawable.ic_folder,
                                    onClick = onNavigateToProjects,
                                    testTag = "metric_projects_card",
                                    modifier = Modifier.weight(1f)
                                )
                                MetricSummaryCard(
                                    value = "${uiState.experienceCount}",
                                    label = stringResource(R.string.filter_experience),
                                    iconRes = R.drawable.ic_work,
                                    onClick = { onNavigateToJourney(JourneyFilter.EXPERIENCE) },
                                    testTag = "metric_experience_card",
                                    modifier = Modifier.weight(1f),
                                    useSecondaryAccent = true
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                MetricSummaryCard(
                                    value = "${uiState.educationCount}",
                                    label = stringResource(R.string.filter_education),
                                    iconRes = R.drawable.ic_school,
                                    onClick = { onNavigateToJourney(JourneyFilter.EDUCATION) },
                                    testTag = "metric_education_card",
                                    modifier = Modifier.weight(1f)
                                )
                                MetricSummaryCard(
                                    value = "${uiState.allSkills.size}",
                                    label = stringResource(R.string.nav_skills),
                                    iconRes = R.drawable.ic_skills,
                                    onClick = onNavigateToSkills,
                                    testTag = "metric_skills_card",
                                    modifier = Modifier.weight(1f),
                                    useSecondaryAccent = true
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    EditorialSectionHeader(
                        title = stringResource(R.string.section_featured_projects),
                        actionLabel = stringResource(R.string.action_view_all),
                        onActionClick = onNavigateToProjects,
                        actionTestTag = "overview_view_all_projects"
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(end = 4.dp)
                    ) {
                        items(featuredProjects, key = { it.id }) { project ->
                            FeaturedProjectCard(
                                project = project,
                                onClick = { onNavigateToProjectDetail(project.id) }
                            )
                        }
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    EditorialSectionHeader(
                        title = stringResource(R.string.section_core_capabilities),
                        actionLabel = stringResource(R.string.action_view_all),
                        onActionClick = onNavigateToSkills,
                        actionTestTag = "overview_view_all_skills"
                    )
                    Surface(
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                profile.focusPillars.forEachIndexed { idx, pillar ->
                                    TechTagChip(
                                        text = pillar,
                                        highlighted = idx % 2 == 1
                                    )
                                }
                            }
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                uiState.allSkills.take(6).forEach { skill ->
                                    Surface(
                                        onClick = onNavigateToSkills,
                                        shape = RoundedCornerShape(20.dp),
                                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                                        border = BorderStroke(
                                            1.dp,
                                            MaterialTheme.colorScheme.outlineVariant
                                        ),
                                        modifier = Modifier.minimumInteractiveComponentSize()
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(
                                                horizontal = 12.dp,
                                                vertical = 8.dp
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(
                                                text = skill.name,
                                                style = MaterialTheme.typography.titleSmall,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "${skill.proficiencyPercent}%",
                                                style = MaterialTheme.typography.labelMedium,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    EditorialSectionHeader(
                        title = stringResource(R.string.section_recent_milestones),
                        actionLabel = stringResource(R.string.action_view_all),
                        onActionClick = { onNavigateToJourney(JourneyFilter.ALL) },
                        actionTestTag = "overview_view_all_journey"
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        uiState.allMilestones.take(2).forEach { milestone ->
                            MilestonePreviewCard(
                                milestone = milestone,
                                onClick = {
                                    val filter = if (milestone.type == MilestoneType.EXPERIENCE) {
                                        JourneyFilter.EXPERIENCE
                                    } else {
                                        JourneyFilter.EDUCATION
                                    }
                                    onNavigateToJourney(filter)
                                }
                            )
                        }
                    }
                }
            }

            item {
                AboutPortfolioCard(
                    paragraphs = profile.aboutParagraphs,
                    onGitHubClick = onGitHubClick
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun HeroProfileSection(
    name: String,
    handle: String,
    role: String,
    statusBadge: String,
    tagline: String,
    onEmailClick: () -> Unit,
    onGitHubClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        tonalElevation = 1.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(152.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_banner),
                    contentDescription = stringResource(R.string.cd_hero_banner),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.scrim.copy(alpha = 0.08f),
                                    MaterialTheme.colorScheme.scrim.copy(alpha = 0.48f)
                                )
                            )
                        )
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_verified),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = statusBadge,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_profile_avatar),
                        contentDescription = stringResource(R.string.cd_profile_avatar),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .offset(y = (-34).dp)
                            .size(84.dp)
                            .clip(CircleShape)
                            .border(4.dp, MaterialTheme.colorScheme.surface, CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = role,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = handle,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Text(
                    text = tagline,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.offset(y = (-12).dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onEmailClick,
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("cta_email")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(R.string.action_email_me),
                            maxLines = 1
                        )
                    }
                    FilledTonalButton(
                        onClick = onGitHubClick,
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("cta_github")
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_github),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(R.string.action_github),
                            maxLines = 1
                        )
                    }
                    OutlinedButton(
                        onClick = onShareClick,
                        modifier = Modifier
                            .heightIn(min = 48.dp)
                            .testTag("cta_share"),
                        contentPadding = PaddingValues(horizontal = 14.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = stringResource(R.string.action_share),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FeaturedProjectCard(
    project: PortfolioProject,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .width(296.dp)
            .minimumInteractiveComponentSize()
            .testTag("featured_project_${project.id}"),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Image(
                    painter = painterResource(id = project.artworkResId),
                    contentDescription = project.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = project.category.label,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = project.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    project.techStack.take(3).forEach { tag ->
                        TechTagChip(text = tag)
                    }
                }
            }
        }
    }
}

@Composable
private fun MilestonePreviewCard(
    milestone: JourneyMilestone,
    onClick: () -> Unit
) {
    val isExp = milestone.type == MilestoneType.EXPERIENCE
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .minimumInteractiveComponentSize()
            .testTag("overview_milestone_${milestone.id}"),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isExp) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                },
                contentColor = if (isExp) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSecondaryContainer
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isExp) R.drawable.ic_work else R.drawable.ic_school
                    ),
                    contentDescription = milestone.type.label,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(22.dp)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = milestone.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${milestone.contextLabel} • ${milestone.period}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun AboutPortfolioCard(
    paragraphs: List<String>,
    onGitHubClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(R.string.section_about_summary),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            paragraphs.forEach { body ->
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            OutlinedButton(
                onClick = onGitHubClick,
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .testTag("about_open_repo_button")
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_open_in_new),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.action_open_repository))
            }
        }
    }
}
