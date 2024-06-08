package eu.krzdabrowski.starter.basicfeature.presentation

sealed class RocketsIntent {
    data object RefreshRockets : RocketsIntent()
    data class RocketClicked(val index: Int) : RocketsIntent()
}
