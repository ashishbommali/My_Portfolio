# My Portfolio (Android)

An Android application built with **Kotlin** and **Jetpack Compose (Material Design 3)**, adapted from the `ashishbommali/My_Portfolio` repository.

## Features

- **Overview**: Hero profile card for **Ashish Bommali**, portfolio tagline, quick-jump section metrics, featured project carousel, core capabilities preview, and recent journey highlights.
- **Projects & Project Details**: Searchable and category-filterable project showcase (`All`, `Mobile & UI`, `Systems & API`, `Architecture`) with skill tag cross-filtering and dedicated detail screens covering architecture notes, key outcomes, technology tags, and GitHub links.
- **Journey (Experience & Education)**: Interactive vertical timeline combining professional experience and educational background with segmented filtering and expandable milestone details.
- **Skills**: Categorized technical skill explorer (`Languages`, `Mobile & UI`, `Architecture`, `Cloud & Tools`) with search, animated proficiency bars, and one-tap deep filtering into related projects.
- **Connect & Adaptive Theming**: Modal bottom sheet with direct email composer (`mailto:`), clipboard copy, GitHub repository launcher, Android Share sheet, and Light / Dark / System theme switching, plus responsive `NavigationBar` / `NavigationRail` layouts across phones and tablets.

## Tech Stack

- Kotlin 2.2 + AGP 9.1.1 (built-in Kotlin) + Gradle 9.3.1
- Jetpack Compose & Material Design 3
- Type-safe Navigation Compose (`@Serializable` routes)
- Lifecycle ViewModel + `StateFlow` (`collectAsStateWithLifecycle`)
- Bundled Google Fonts (`Space Grotesk`, `Plus Jakarta Sans`, `JetBrains Mono`)
