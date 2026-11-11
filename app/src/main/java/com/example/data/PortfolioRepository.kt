package com.example.data

import com.example.R
import com.example.model.JourneyMilestone
import com.example.model.MilestoneType
import com.example.model.OwnerProfile
import com.example.model.PortfolioProject
import com.example.model.ProjectCategory
import com.example.model.SkillCategory
import com.example.model.SkillItem

class PortfolioRepository {

    fun getOwnerProfile(): OwnerProfile = OwnerProfile(
        name = "Ashish Bommali",
        handle = "@ashishbommali",
        email = "ashishbommali@gmail.com",
        role = "Software & Mobile Engineer",
        tagline = "This portfolio showcases my skills, experience, educational background, and projects. Explore to learn more about my journey and accomplishments.",
        aboutParagraphs = listOf(
            "Originated from the ashishbommali/My_Portfolio repository and crafted as a native Android experience using Kotlin and Jetpack Compose.",
            "Designed around four clear pillars—Projects, Experience, Educational Background, and Technical Skills—so reviewers and collaborators can navigate accomplishments without friction."
        ),
        statusBadge = "Open to Engineering Opportunities",
        repoUrl = "https://github.com/ashishbommali/My_Portfolio",
        githubUrl = "https://github.com/ashishbommali",
        focusPillars = listOf(
            "Kotlin & Jetpack Compose",
            "Clean MVVM Architecture",
            "Adaptive Material 3 UI",
            "Full-Stack Problem Solving"
        )
    )

    fun getProjects(): List<PortfolioProject> = listOf(
        PortfolioProject(
            id = "my_portfolio_android",
            title = "My Portfolio — Android Edition",
            subtitle = "Native Kotlin & Material 3 portfolio showcase",
            category = ProjectCategory.MOBILE,
            period = "2026 • Active",
            summary = "Cross-framework Android adaptation of the ashishbommali/My_Portfolio repository, bringing skills, timeline milestones, educational foundations, and project deep-dives into a responsive mobile and tablet showcase.",
            architectureNotes = listOf(
                "Structured around MVVM with immutable StateFlow streams and unidirectional data flow.",
                "Type-safe Navigation Compose graph using @Serializable route definitions and adaptive NavigationBar / NavigationRail switching.",
                "Bundled custom typography pairing Space Grotesk, Plus Jakarta Sans, and JetBrains Mono for editorial clarity."
            ),
            outcomes = listOf(
                "Zero-permission external intents for email, GitHub repository browsing, and portfolio sharing.",
                "Full light/dark editorial theming with responsive tablet and foldable layout support."
            ),
            techStack = listOf("Kotlin", "Jetpack Compose", "Material 3", "Navigation Compose", "StateFlow"),
            artworkResId = R.drawable.img_project_mobile,
            repoUrl = "https://github.com/ashishbommali/My_Portfolio",
            featured = true
        ),
        PortfolioProject(
            id = "modular_architecture_blueprint",
            title = "Clean MVVM Architecture Blueprint",
            subtitle = "Scalable state management & UI modularization",
            category = ProjectCategory.ARCHITECTURE,
            period = "Portfolio Showcase",
            summary = "Reference mobile architecture illustrating strict separation between domain models, repository contracts, lifecycle-aware ViewModels, and declarative Compose surfaces.",
            architectureNotes = listOf(
                "Uses sealed UI states and derived filtering pipelines to keep recompositions minimal.",
                "Supports offline-resilient state presentation with graceful empty and edge-case feedback.",
                "Enforces single-responsibility composables and accessible 48dp minimum touch targets."
            ),
            outcomes = listOf(
                "Predictable state transitions verifiable with fast JVM unit tests.",
                "Modular feature packages ready to scale from prototype to production."
            ),
            techStack = listOf("Kotlin", "Coroutines", "MVVM", "Gradle Kotlin DSL", "Clean Architecture"),
            artworkResId = R.drawable.img_project_architecture,
            repoUrl = "https://github.com/ashishbommali/My_Portfolio",
            featured = true
        ),
        PortfolioProject(
            id = "api_telemetry_connector",
            title = "Typed API & Data Pipeline Showcase",
            subtitle = "Structured serialization & reactive client state",
            category = ProjectCategory.SYSTEMS,
            period = "Portfolio Showcase",
            summary = "Demonstrates resilient client-server data modeling with Kotlinx Serialization, structured error envelopes, and non-blocking asynchronous data streams.",
            architectureNotes = listOf(
                "Type-safe JSON decoding models with explicit schema defaults and defensive mapping.",
                "Coroutine Flow operators for search debouncing, category filtering, and reactive updates.",
                "Clean separation between network transport DTOs and UI-facing domain entities."
            ),
            outcomes = listOf(
                "Eliminates fragile string-key parsing in navigation and API payloads.",
                "Delivers instant filter responsiveness across skills and project catalogs."
            ),
            techStack = listOf("Kotlin", "Serialization", "Coroutines", "REST APIs", "JSON"),
            artworkResId = R.drawable.img_project_architecture,
            repoUrl = "https://github.com/ashishbommali/My_Portfolio",
            featured = false
        ),
        PortfolioProject(
            id = "adaptive_design_system",
            title = "Adaptive Material 3 Component Kit",
            subtitle = "Multi-form-factor spacing, motion & edge-to-edge",
            category = ProjectCategory.MOBILE,
            period = "Portfolio Showcase",
            summary = "Reusable Jetpack Compose design system built to adapt seamlessly from compact phones to foldables and large tablets without layout distortion.",
            architectureNotes = listOf(
                "Implements edge-to-edge WindowInsets across status bars, camera cutouts, and gesture navigation bars.",
                "Uses BoxWithConstraints to transition cleanly between bottom bars and side navigation rails.",
                "Applies spring motion and staggered visibility cues under 300ms for tactile feedback."
            ),
            outcomes = listOf(
                "Consistent 8dp spacing grid and accessible contrast in both Light and Dark themes.",
                "Reusable card, chip, and timeline primitives across multiple screens."
            ),
            techStack = listOf("Jetpack Compose", "Material 3", "Adaptive UI", "Kotlin", "Git & GitHub"),
            artworkResId = R.drawable.img_hero_banner,
            repoUrl = "https://github.com/ashishbommali/My_Portfolio",
            featured = false
        )
    )

    fun getProjectById(id: String): PortfolioProject? =
        getProjects().firstOrNull { it.id == id }

    fun getMilestones(): List<JourneyMilestone> = listOf(
        JourneyMilestone(
            id = "exp_mobile_builder",
            type = MilestoneType.EXPERIENCE,
            title = "Mobile & Software Application Development",
            contextLabel = "Independent & Portfolio Engineering",
            period = "Ongoing",
            modeLabel = "Kotlin • Compose • Full-Stack",
            summary = "Designing and building end-to-end Android and cross-platform software experiences focused on clean architecture, responsive interfaces, and maintainable code.",
            highlights = listOf(
                "Translated repository documentation and portfolio goals into modular Kotlin + Jetpack Compose applications.",
                "Applied MVVM, unidirectional data flow, and type-safe navigation patterns for reliable state management.",
                "Prioritized accessibility, edge-to-edge layouts, and zero-permission system integrations."
            ),
            tags = listOf("Kotlin", "Jetpack Compose", "MVVM", "Material 3")
        ),
        JourneyMilestone(
            id = "exp_systems_prototyping",
            type = MilestoneType.EXPERIENCE,
            title = "Full-Stack Prototyping & API Integration",
            contextLabel = "Open-Source & Project Builds",
            period = "Project Track",
            modeLabel = "APIs • Data Modeling • Git",
            summary = "Constructed structured client-server workflows, version-controlled Git repositories, and clean UI prototypes designed for clarity and rapid iteration.",
            highlights = listOf(
                "Maintained structured repositories on GitHub with clear documentation and modular source layouts.",
                "Modeled typed JSON contracts and asynchronous data streams using Coroutines and Flow.",
                "Tested responsive component behavior across varied viewport sizes and theme modes."
            ),
            tags = listOf("REST APIs", "JSON", "Git & GitHub", "Coroutines")
        ),
        JourneyMilestone(
            id = "edu_cs_foundation",
            type = MilestoneType.EDUCATION,
            title = "Computer Science & Engineering Foundations",
            contextLabel = "Core Academic Background",
            period = "Academic Track",
            modeLabel = "Algorithms • Systems • Software Design",
            summary = "Built strong theoretical and practical engineering fundamentals across software architecture, data structures, databases, and object-oriented programming.",
            highlights = listOf(
                "Core focus in Data Structures & Algorithms, Object-Oriented Design, and Database Management Systems.",
                "Applied Operating Systems, Computer Networks, and Software Engineering lifecycle principles to hands-on builds.",
                "Completed project-based coursework emphasizing clean problem decomposition and documentation."
            ),
            tags = listOf("Data Structures", "OOP", "Databases", "Software Engineering")
        ),
        JourneyMilestone(
            id = "edu_modern_android",
            type = MilestoneType.EDUCATION,
            title = "Modern Android & Declarative UI Mastery",
            contextLabel = "Continuous Technical Specialization",
            period = "Continuous Learning",
            modeLabel = "Jetpack • Adaptive Design • Testing",
            summary = "Ongoing hands-on specialization in modern Android tooling, Gradle Kotlin DSL, Material Design 3 guidelines, and adaptive multi-screen engineering.",
            highlights = listOf(
                "Adopted modern declarative UI paradigms with Jetpack Compose and Material 3 design tokens.",
                "Practiced Gradle Version Catalogs, AGP 9 built-in Kotlin workflows, and automated compilation checks.",
                "Focused on user-centric mobile UX, accessible semantics, and performance-aware recomposition."
            ),
            tags = listOf("Gradle Kotlin DSL", "Adaptive UI", "Material 3", "Serialization")
        )
    )

    fun getSkills(): List<SkillItem> = listOf(
        SkillItem(
            id = "skill_kotlin",
            name = "Kotlin",
            category = SkillCategory.LANGUAGES,
            proficiencyPercent = 92,
            levelLabel = "Core",
            summary = "Idiomatic Kotlin, sealed state modeling, coroutines, extension functions, and type-safe DSLs.",
            filterTag = "Kotlin"
        ),
        SkillItem(
            id = "skill_compose",
            name = "Jetpack Compose",
            category = SkillCategory.MOBILE_UI,
            proficiencyPercent = 90,
            levelLabel = "Core",
            summary = "Declarative UI composition, custom Canvas drawing, state hoisting, and smooth motion.",
            filterTag = "Jetpack Compose"
        ),
        SkillItem(
            id = "skill_material3",
            name = "Material Design 3",
            category = SkillCategory.MOBILE_UI,
            proficiencyPercent = 88,
            levelLabel = "Advanced",
            summary = "Semantic color tokens, custom typography scales, adaptive rails/bars, and edge-to-edge insets.",
            filterTag = "Material 3"
        ),
        SkillItem(
            id = "skill_mvvm",
            name = "MVVM & Clean Architecture",
            category = SkillCategory.ARCHITECTURE_DATA,
            proficiencyPercent = 88,
            levelLabel = "Advanced",
            summary = "Unidirectional data flow, ViewModel StateFlow management, and modular repository boundaries.",
            filterTag = "MVVM"
        ),
        SkillItem(
            id = "skill_coroutines",
            name = "Coroutines & Flow",
            category = SkillCategory.ARCHITECTURE_DATA,
            proficiencyPercent = 85,
            levelLabel = "Advanced",
            summary = "Structured concurrency, reactive streams, search/filter pipelines, and lifecycle collection.",
            filterTag = "Coroutines"
        ),
        SkillItem(
            id = "skill_navigation",
            name = "Navigation Compose",
            category = SkillCategory.MOBILE_UI,
            proficiencyPercent = 86,
            levelLabel = "Advanced",
            summary = "Type-safe @Serializable routes, predictive back compatibility, and multi-pane flows.",
            filterTag = "Navigation Compose"
        ),
        SkillItem(
            id = "skill_serialization",
            name = "Kotlinx Serialization & JSON",
            category = SkillCategory.ARCHITECTURE_DATA,
            proficiencyPercent = 84,
            levelLabel = "Proficient",
            summary = "Compile-time type-safe data encoding/decoding and structured REST API contracts.",
            filterTag = "Serialization"
        ),
        SkillItem(
            id = "skill_rest",
            name = "REST APIs & Systems Integration",
            category = SkillCategory.TOOLS,
            proficiencyPercent = 82,
            levelLabel = "Proficient",
            summary = "Designing resilient network boundaries, DTO mapping, and clean service integration.",
            filterTag = "REST APIs"
        ),
        SkillItem(
            id = "skill_gradle",
            name = "Gradle Kotlin DSL",
            category = SkillCategory.TOOLS,
            proficiencyPercent = 84,
            levelLabel = "Proficient",
            summary = "Version catalogs, AGP 9 built-in Kotlin builds, dependency management, and signing setup.",
            filterTag = "Gradle Kotlin DSL"
        ),
        SkillItem(
            id = "skill_git",
            name = "Git & GitHub",
            category = SkillCategory.TOOLS,
            proficiencyPercent = 88,
            levelLabel = "Core",
            summary = "Clean repository organization, collaborative workflows, documentation, and version hygiene.",
            filterTag = "Git & GitHub"
        ),
        SkillItem(
            id = "skill_adaptive",
            name = "Adaptive Multi-Screen UI",
            category = SkillCategory.MOBILE_UI,
            proficiencyPercent = 85,
            levelLabel = "Advanced",
            summary = "Responsive phone, foldable, and tablet layouts with constrained containers and rails.",
            filterTag = "Adaptive UI"
        ),
        SkillItem(
            id = "skill_java_cs",
            name = "Java & CS Fundamentals",
            category = SkillCategory.LANGUAGES,
            proficiencyPercent = 85,
            levelLabel = "Advanced",
            summary = "Object-oriented principles, data structures, algorithms, and JVM ecosystem fluency.",
            filterTag = "Kotlin"
        )
    )
}
