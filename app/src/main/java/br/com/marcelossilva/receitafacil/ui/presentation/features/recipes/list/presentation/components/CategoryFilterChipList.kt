package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun CategoryFilterChipList(
    modifier: Modifier = Modifier,
    categories: List<Int?>,
    selectedCategory: Int?,
    onSelectedCategory: (Int?) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = categories) { category ->
            CategoryFilterChip(
                category = category,
                isSelected = selectedCategory == category,
                onClick = { newIsSelected ->
                    if (newIsSelected) {
                        onSelectedCategory(category)
                    } else {
                        onSelectedCategory(null)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun CategoryFilterChipListPreview() {
    ReceitaFacilAppTheme {
        CategoryFilterChipList(
            categories = listOf(null, 1, 2, 3, 4, 5),
            selectedCategory = null,
            onSelectedCategory = {}
        )
    }
}