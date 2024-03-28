package com.oliverspryn.gradle

object CentralRepositoryConfig {
    const val LIBRARY_NAME = "MultimodalSpanner"

    object Artifact {
        const val GROUP_ID = "com.oliverspryn.android"
        const val ID = "multimodal-spanner"
        const val VERSION = "1.2.0"
    }

    object Developer {
        const val ID = "oliverspryn"
        const val NAME = "Oliver Spryn"
        const val URL = "https://oliverspryn.com/"
    }

    object Project {
        const val DESCRIPTION = "Smooths over the rough edges to help you extract information about your Android device's screen and foldable state."
        const val NAME = "Multimodal Spanner"
        const val URL = "https://oliverspryn.com/"
    }

    object License {
        const val NAME = "MIT License"
        const val URL = "https://mit-license.org/"
    }

    object SCM {
        const val URL = "github.com/oliverspryn/multimodal-spanner" // Omit the `protocol://` and trailing slash
    }
}
