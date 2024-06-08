package eu.krzdabrowski.starter.basicfeature.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketArgument(
    val id: String = "",
    val name: String = "",
    val country: String,
    val costPerLaunchInMillions: Int = 0,
    val firstFlightDate: String = "",
    val heightInMeters: Int = 0,
    val weightInTonnes: Int = 0,
    val wikiUrl: String = "",
    val imageUrl: String = "",
) : Parcelable
