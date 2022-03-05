package com.inflames.bloom.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.insets.statusBarsHeight
import com.inflames.bloom.R
import com.inflames.bloom.components.BloomOutlinedTextField
import com.inflames.bloom.data.Checkable
import com.inflames.bloom.data.Plant
import com.inflames.bloom.data.Theme

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {


    Box(
        modifier = Modifier
            .padding(bottom = 56.dp)
            .fillMaxSize()
    ) {

        val listState = rememberLazyListState()

        if (listState.firstVisibleItemIndex > 0) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsHeight(),
                color = MaterialTheme.colors.primary.copy(alpha = 1f)
            ) {
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, end = 16.dp, start = 16.dp),
            state = listState
        ) {


            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
            item {
                BloomOutlinedTextField(
                    value = homeViewModel.homeViewState.value.search,
                    onValueChange = homeViewModel::onChangeSearch,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .fillMaxWidth(),
                    placeholder = { Text(text = stringResource(R.string.home_search)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search, contentDescription = stringResource(
                                id = R.string.home_search
                            ),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    textStyle = MaterialTheme.typography.body1
                )
            }
            item {
                Text(
                    text = stringResource(R.string.home_browse_themes),
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .paddingFromBaseline(32.dp, bottom = 16.dp)
                )
            }
            item {
                ThemesRow(
                    themes = homeViewModel.homeViewState.value.themes,
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.home_design_your_home_garden),
                        modifier = Modifier.paddingFromBaseline(40.dp),
                        style = MaterialTheme.typography.h1
                    )

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.List, contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }


                }
            }

            items(homeViewModel.homeViewState.value.plants) { checkablePlant ->
                PlantItem(
                    plant = checkablePlant.data,
                    checked = checkablePlant.checked,
                    onCheckedChange = { homeViewModel.onCheckedChange(checkablePlant.data, it) }
                )
            }


        }
    }

}

@Composable
private fun ThemesRow(
    themes: List<Theme>,
    modifier: Modifier = Modifier
) {

    LazyRow(
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(themes) {
            ThemeItemRow(theme = it)
        }

    }
}

@Composable
fun ThemeItemRow(theme: Theme) {

    Card(
        modifier = Modifier
            .size(136.dp),
        elevation = 8.dp
    ) {

        Column {

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(theme.image)
                        .crossfade(true)
                        .crossfade(500)
                        .build()
                ),
                contentDescription = theme.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = theme.name,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun PlantLazyColumn(
    plantList: List<Checkable<Plant>>,
    modifier: Modifier = Modifier,
) {

    LazyColumn(contentPadding = PaddingValues(end = 16.dp)) {

        items(plantList) { plant ->

            PlantItem(plant = plant.data, checked = plant.checked, onCheckedChange = {})
        }
    }
}

@Composable
fun PlantItemRow(plant: Plant) {

    Row(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(plant.image)
                    .crossfade(true)
                    .crossfade(500)
                    .build()
            ),
            contentDescription = plant.name,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .fillMaxHeight()
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = plant.name,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.paddingFromBaseline(24.dp)
            )

            Text(
                text = plant.description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.paddingFromBaseline(bottom = 24.dp)
            )
        }

        Checkbox(checked = true, onCheckedChange = {})

    }

}

@Composable
fun PlantItem(
    plant: Plant,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?
) {

    ConstraintLayout(modifier = Modifier.padding(top = 16.dp)) {
        val (image, title, description, checkbox, divider) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(plant.image)
                    .crossfade(true)
                    .crossfade(500)
                    .build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small)
                .constrainAs(image) {
                    bottom.linkTo(divider.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = plant.name,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(24.dp)
                .constrainAs(title) {
                    start.linkTo(image.end)
                    top.linkTo(parent.top)
                }
                .padding(start = 16.dp)
        )

        Text(
            text = plant.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .paddingFromBaseline(bottom = 24.dp)
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                    start.linkTo(image.end)
                    bottom.linkTo(divider.top)
                }
                .padding(start = 16.dp)
        )

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.constrainAs(checkbox) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(title.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(checkbox.end)
                }

        )


    }


}

