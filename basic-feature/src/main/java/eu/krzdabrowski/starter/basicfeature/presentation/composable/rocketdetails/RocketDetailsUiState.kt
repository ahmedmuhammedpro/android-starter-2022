package eu.krzdabrowski.starter.basicfeature.presentation.composable.rocketdetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import eu.krzdabrowski.starter.basicfeature.presentation.model.RocketDisplayable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class RocketDetailsUiState(
    val isLoading: Boolean = false,
    val rocket: RocketDisplayable? = null,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState() // for simplicity: initial loading & refreshing

        data class Fetched(val rocket: RocketDisplayable?) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()
    }
}