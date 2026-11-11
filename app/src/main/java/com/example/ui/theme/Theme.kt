package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.model.ThemePreference

private val LightColorScheme = lightColorScheme(
    primary = CobaltPrimaryLight,
    onPrimary = OnCobaltPrimaryLight,
    primaryContainer = CobaltContainerLight,
    onPrimaryContainer = OnCobaltContainerLight,
    secondary = CopperSecondaryLight,
    onSecondary = OnCopperSecondaryLight,
    secondaryContainer = CopperContainerLight,
    onSecondaryContainer = OnCopperContainerLight,
    tertiary = TealTertiaryLight,
    onTertiary = OnTealTertiaryLight,
    tertiaryContainer = TealContainerLight,
    onTertiaryContainer = OnTealTertiaryContainerLight,
    background = IvoryBackgroundLight,
    onBackground = MidnightInkOnLight,
    surface = PaperSurfaceLight,
    onSurface = MidnightInkOnLight,
    surfaceVariant = WarmSlateVariantLight,
    onSurfaceVariant = MutedSlateOnVariantLight,
    surfaceContainerLowest = PaperSurfaceLight,
    surfaceContainerLow = SurfaceContainerLowLight,
    surfaceContainer = SurfaceContainerLight,
    surfaceContainerHigh = SurfaceContainerHighLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight
)

private val DarkColorScheme = darkColorScheme(
    primary = SkyCobaltPrimaryDark,
    onPrimary = OnSkyCobaltPrimaryDark,
    primaryContainer = CobaltContainerDark,
    onPrimaryContainer = OnCobaltContainerDark,
    secondary = AmberCopperSecondaryDark,
    onSecondary = OnAmberCopperSecondaryDark,
    secondaryContainer = CopperContainerDark,
    onSecondaryContainer = OnCopperContainerDark,
    tertiary = MintTealTertiaryDark,
    onTertiary = OnMintTealTertiaryDark,
    tertiaryContainer = TealContainerDark,
    onTertiaryContainer = OnTealContainerDark,
    background = ObsidianBackgroundDark,
    onBackground = CrispIvoryOnDark,
    surface = DeepSlateSurfaceDark,
    onSurface = CrispIvoryOnDark,
    surfaceVariant = SlateVariantDark,
    onSurfaceVariant = MutedCoolOnVariantDark,
    surfaceContainerLowest = ObsidianBackgroundDark,
    surfaceContainerLow = SurfaceContainerLowDark,
    surfaceContainer = SurfaceContainerDark,
    surfaceContainerHigh = SurfaceContainerHighDark,
    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark
)

val PortfolioShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun MyPortfolioTheme(
    themePreference: ThemePreference = ThemePreference.SYSTEM,
    content: @Composable () -> Unit
) {
    val isDark = when (themePreference) {
        ThemePreference.SYSTEM -> isSystemInDarkTheme()
        ThemePreference.LIGHT -> false
        ThemePreference.DARK -> true
    }

    val colorScheme = if (isDark) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PortfolioTypography,
        shapes = PortfolioShapes,
        content = content
    )
}
