package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import br.com.marcelossilva.receitafacil.R

@Composable
fun rememberCategories(): List<Int?> {
    val categories = stringArrayResource(id = R.array.filter_categories)

    return List(categories.size) { index ->
        if (index == 0) null else index - 1
    }
}