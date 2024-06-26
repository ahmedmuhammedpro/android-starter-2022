package eu.krzdabrowski.starter.basicfeature.presentation.composable.rockets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.krzdabrowski.starter.basicfeature.R
import eu.krzdabrowski.starter.basicfeature.presentation.composable.rockets.RocketsIntent.RefreshRockets
import eu.krzdabrowski.starter.basicfeature.presentation.composable.rockets.RocketsIntent.RocketClicked

@Composable
fun RocketsRoute(
    viewModel: RocketsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RocketsScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
    )
}

@Composable
internal fun RocketsScreen(
    uiState: RocketsUiState,
    onIntent: (RocketsIntent) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pullToRefreshState = rememberPullToRefreshState()

    HandlePullToRefresh(
        pullState = pullToRefreshState,
        uiState = uiState,
        onIntent = onIntent,
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(pullToRefreshState.nestedScrollConnection),
        ) {
            if (uiState.rockets.isNotEmpty()) {
                RocketsAvailableContent(
                    snackbarHostState = snackbarHostState,
                    uiState = uiState,
                    onRocketClick = { onIntent(RocketClicked(it)) },
                )
            } else {
                RocketsNotAvailableContent(
                    uiState = uiState,
                )
            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter),
            )
        }
    }
}

@Composable
private fun HandlePullToRefresh(
    pullState: PullToRefreshState,
    uiState: RocketsUiState,
    onIntent: (RocketsIntent) -> Unit,
) {
    if (pullState.isRefreshing) {
        LaunchedEffect(onIntent) {
            onIntent(RefreshRockets)
        }
    }

    if (uiState.isLoading.not()) {
        LaunchedEffect(true) {
            pullState.endRefresh()
        }
    }
}


@Composable
private fun RocketsAvailableContent(
    snackbarHostState: SnackbarHostState,
    uiState: RocketsUiState,
    onRocketClick: (Int) -> Unit,
) {
    if (uiState.isError) {
        val errorMessage = stringResource(R.string.rockets_error_refreshing)

        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
            )
        }
    }

    RocketsListContent(
        rocketList = uiState.rockets,
        onRocketClick = onRocketClick,
    )
}

@Composable
private fun RocketsNotAvailableContent(uiState: RocketsUiState) {
    when {
        uiState.isLoading -> RocketsLoadingPlaceholder()
        uiState.isError -> RocketsErrorContent()
    }
}
