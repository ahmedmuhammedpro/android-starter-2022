package eu.krzdabrowski.starter.basicfeature.presentation.mapper

import eu.krzdabrowski.starter.basicfeature.domain.model.Rocket
import eu.krzdabrowski.starter.basicfeature.presentation.model.RocketDisplayable
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val TONNE = 1_000
private const val MILLION = 1_000_000

fun Rocket.toPresentationModel() = RocketDisplayable(
    id = id,
    name = name,
    country = country,
    costPerLaunchInMillions = costPerLaunch / MILLION,
    firstFlightDate = firstFlight.format(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)),
    heightInMeters = height,
    weightInTonnes = weight / TONNE,
    wikiUrl = wikiUrl,
    imageUrl = imageUrl,
)
