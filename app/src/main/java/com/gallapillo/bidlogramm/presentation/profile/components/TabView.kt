package com.gallapillo.bidlogramm.presentation.profile.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gallapillo.bidlogramm.domain.model.TabModel
import com.gallapillo.bidlogramm.presentation.theme.MainColor
import com.gallapillo.bidlogramm.presentation.theme.MainColorDark

@Composable
fun TabView(
    modifier : Modifier = Modifier,
    tabModels : List<TabModel>,
    onTabSelected : (selectedIndex: Int) -> Unit
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    TabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onSecondary,
        modifier = modifier
    ) {
        tabModels.forEachIndexed { index, t ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onTabSelected(index)
                },
                selectedContentColor = MainColorDark,
                unselectedContentColor = MainColor
            )
        }
    }
}