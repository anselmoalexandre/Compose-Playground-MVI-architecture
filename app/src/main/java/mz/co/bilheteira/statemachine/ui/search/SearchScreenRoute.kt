package mz.co.bilheteira.statemachine.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchViewState

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onLocationSelected: (LocationModel) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchContent(
        modifier = modifier,
        viewState = uiState,
        onLocationSelected = onLocationSelected
    )
}

@Composable
internal fun SearchContent(
    modifier: Modifier = Modifier,
    onLocationSelected: (LocationModel) -> Unit,
    viewState: SearchViewState,
) {
    when (viewState) {
        is SearchViewState.Loading -> CircularProgressBar(
            isLoading = true,
            modifier = modifier,
        )

        is SearchViewState.Success -> CircularProgressBar(
            isLoading = false,
            modifier = modifier,
        )

        is SearchViewState.Locations -> SearchListScreen(
            locations = viewState.locations,
            onLocationSelected = onLocationSelected,
            modifier = modifier,
        )

        is SearchViewState.Error -> ErrorScreen(
            modifier = modifier,
            message = viewState.message,
        )

        else -> {}
    }
}

@Composable
internal fun SearchListScreen(
    locations: List<LocationModel>,
    onLocationSelected: (LocationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        locations.forEach { locationModel ->
            val locationId = locationModel.id
            item(key = locationId) {
                LocationItem(location = locationModel,
                    onClick = { selected ->
                        onLocationSelected(selected)
                    }
                )
            }
        }
    }
}

@Composable
internal fun LocationItem(
    location: LocationModel,
    onClick: (LocationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick(location) }
            .border(BorderStroke(1.dp, color = Color.Black))
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

@Composable
internal fun CircularProgressBar(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
        )
    }
}

@Composable
internal fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
) {
    Surface(modifier = modifier) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}