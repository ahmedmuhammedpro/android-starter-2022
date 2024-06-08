package eu.krzdabrowski.starter.basicfeature.presentation.composable.rockets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import eu.krzdabrowski.starter.core.navigation.NavigationDestination
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import javax.inject.Inject

class RocketsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.Rockets.route) {
            RocketsRoute()
        }
    }
}