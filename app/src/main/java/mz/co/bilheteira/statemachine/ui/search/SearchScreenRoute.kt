package mz.co.bilheteira.statemachine.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mz.co.bilheteira.domain.data.LocationModel
import mz.co.bilheteira.statemachine.ui.search.statemanager.SearchViewState

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onLocationSelected: (LocationModel) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Toolbar(modifier = modifier)
        },
        bottomBar = {},
        floatingActionButton = {}
    ) { contentPadding ->
        SearchScreenContent(
            modifier = Modifier.padding(contentPadding),
            onLocationSelected = onLocationSelected
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(
    modifier: Modifier,
    title: String = "MVI Playground"
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
//            Icon(
//                imageVector = Icons.Rounded.Build,
//                contentDescription = "Icon",
//                tint = MaterialTheme.colorScheme.onPrimary,
//                modifier = modifier.padding(start = 10.dp)
//            )
        }
    )
}

@Composable
internal fun SearchScreenContent(
    modifier: Modifier = Modifier,
    onLocationSelected: (LocationModel) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
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
    viewState: SearchViewState
) {
    when (viewState) {
        is SearchViewState.Initial -> {}
        is SearchViewState.Loading -> CircularProgressBar(
            isLoading = true,
            modifier = modifier
        )

        is SearchViewState.Success -> CircularProgressBar(
            isLoading = false,
            modifier = modifier
        )

        is SearchViewState.Locations -> SearchListScreen(
            locations = viewState.locations,
            onLocationSelected = onLocationSelected,
            modifier = modifier
        )

        is SearchViewState.Error -> ErrorScreen(
            modifier = modifier,
            message = viewState.message
        )

        else -> {}
    }
}

@Composable
internal fun SearchListScreen(
    locations: List<LocationModel>,
    onLocationSelected: (LocationModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 5.dp)
    ) {
        locations.forEach { locationModel ->
            val locationId = locationModel.id
            item(key = locationId) {
                LocationItem(
                    location = locationModel,
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
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .padding(top = 5.dp)
            .fillMaxWidth()
            .clickable { onClick(location) },
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        shape = CardDefaults.outlinedShape
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 5.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier
                    .size(width = 32.dp, height = 32.dp)
                    .padding(start = 5.dp),
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = "Icon"
            )

            Column(
                modifier = modifier.padding(start = 5.dp)
            ) {
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontStyle = FontStyle.Normal
                )
                Text(
                    text = location.country,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
internal fun CircularProgressBar(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth
        )
    }
}

@Composable
internal fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String
) {
    Surface(modifier = modifier) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
