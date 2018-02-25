package com.igorwojda.traktclient.data.repository.trakt.enum

/**
 * Different extended levels of information.
 */
enum class Extended(val value: String) {

    DEFAULT_MIN("min"),
    FULL("full"),
    NO_SEASONS("noseasons"),
    EPISODES("episodes"),
    FULL_EPISODES("full,episodes");

    override fun toString(): String {
        return value
    }
}