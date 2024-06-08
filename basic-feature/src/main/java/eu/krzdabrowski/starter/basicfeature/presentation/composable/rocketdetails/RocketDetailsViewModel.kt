package eu.krzdabrowski.starter.basicfeature.presentation.composable.rocketdetails

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.krzdabrowski.starter.basicfeature.domain.usecase.GetRocketsUseCase
import eu.krzdabrowski.starter.basicfeature.presentation.composable.rocketdetails.RocketDetailsUiState.PartialState
import eu.krzdabrowski.starter.basicfeature.presentation.mapper.toPresentationModel
import eu.krzdabrowski.starter.core.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase,
    savedStateHandle: SavedStateHandle,
    rocketsInitialState: RocketDetailsUiState,
) : BaseViewModel<RocketDetailsUiState, PartialState, RocketDetailsEvent, RocketDetailsIntent>(
    savedStateHandle,
    rocketsInitialState,
) {
    init {
        val rocketId = checkNotNull(savedStateHandle["rocketId"]) as String
        fetchRocketDetails(rocketId)
    }

    override fun reduceUiState(
        previousState: RocketDetailsUiState,
        partialState: PartialState
    ): RocketDetailsUiState =
        when (partialState) {
            is PartialState.Fetched -> previousState.copy(rocket = partialState.rocket)
            is PartialState.Loading -> previousState.copy()
            is PartialState.Error -> previousState.copy()
        }

    private fun fetchRocketDetails(rocketId: String) = acceptChanges(
        getRocketsUseCase().map { result ->
            result.fold(
                onSuccess = { rocketList ->
                    val rocket = rocketList.find { it.id == rocketId }
                    PartialState.Fetched(rocket?.toPresentationModel())

                },
                onFailure = {
                    PartialState.Error(it)
                }
            )
        }
    )

    override fun mapIntents(intent: RocketDetailsIntent): Flow<PartialState> {
        return flow { }
    }
}