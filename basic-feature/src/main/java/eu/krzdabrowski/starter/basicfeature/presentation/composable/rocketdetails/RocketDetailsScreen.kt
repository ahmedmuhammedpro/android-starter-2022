package eu.krzdabrowski.starter.basicfeature.presentation.composable.rocketdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun RocketDetailsRoute(
    viewModel: RocketDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RocketDetailsScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun RocketDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: RocketDetailsUiState,
    onIntent: (RocketDetailsIntent) -> Unit,
) {
    uiState.rocket?.let { rocket ->
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = rocket.name,
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )

            AsyncImage(
                model = rocket.imageUrl,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
        }
    }
}