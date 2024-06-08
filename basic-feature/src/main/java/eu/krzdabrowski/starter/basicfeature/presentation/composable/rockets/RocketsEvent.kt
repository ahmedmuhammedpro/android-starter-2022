package eu.krzdabrowski.starter.basicfeature.presentation.composable.rockets

sealed class RocketsEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : RocketsEvent()
}