plugins {
    id("java-platform")
}

dependencies {
    constraints {
        // region Application

        api("${Libraries.ACTIVITY_COMPOSE}:${Versions.ACTIVITY_COMPOSE}")
        api("${Libraries.COMPOSE_MANIFEST_TEST}:${Versions.COMPOSE}")
        api("${Libraries.COMPOSE_UI}:${Versions.COMPOSE}")
        api("${Libraries.COMPOSE_UI_UNIT}:${Versions.COMPOSE}")
        api("${Libraries.COMPOSE_UI_TOOLING}:${Versions.COMPOSE}")
        api("${Libraries.COMPOSE_UI_TOOLING_PREVIEW}:${Versions.COMPOSE}")
        api("${Libraries.CORE_KTX}:${Versions.CORE_KTX}")
        api("${Libraries.DAGGER_ANDROID}:${Versions.DAGGER}")
        api("${Libraries.DAGGER_COMPILER}:${Versions.DAGGER}")
        api("${Libraries.MATERIAL}:${Versions.MATERIAL}")
        api("${Libraries.MATERIAL_3}:${Versions.MATERIAL_3}")
        api("${Libraries.NAVIGATION_COMPOSE}:${Versions.NAVIGATION_COMPOSE}")
        api("${Libraries.WINDOW_MANAGER}:${Versions.WINDOW_MANAGER}")

        // endregion
    }
}
