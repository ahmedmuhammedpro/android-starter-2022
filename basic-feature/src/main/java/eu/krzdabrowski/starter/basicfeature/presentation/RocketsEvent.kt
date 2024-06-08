package eu.krzdabrowski.starter.basicfeature.presentation

import eu.krzdabrowski.starter.basicfeature.domain.model.Rocket

sealed class RocketsEvent {
    data class OpenRocketDetails(val rocket: Rocket) : RocketsEvent()
    data class OpenWebBrowserWithDetails(val uri: String) : RocketsEvent()
}
