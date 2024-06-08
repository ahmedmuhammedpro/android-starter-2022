package eu.krzdabrowski.starter.basicfeature.presentation.composable.rocketdetails

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import eu.krzdabrowski.starter.basicfeature.domain.model.RocketArgument
import eu.krzdabrowski.starter.basicfeature.presentation.model.RocketDisplayable
import eu.krzdabrowski.starter.core.navigation.NavigationDestination.RocketDetails
import eu.krzdabrowski.starter.core.navigation.NavigationFactory
import javax.inject.Inject

class RocketDetailsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable(
            route = "${RocketDetails.route}/{rocketId}",
            arguments = listOf(navArgument("rocketId") {
                type = NavType.StringType
            })
        ) {
            RocketDetailsRoute()
        }
    }
}