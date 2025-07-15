package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.marcelossilva.receitafacil.R
import br.com.marcelossilva.receitafacil.ui.presentation.components.state.EmptyState

@Composable
fun RecipesEmptySate(
    modifier: Modifier = Modifier
) {
    EmptyState(
        modifier = modifier,
        image = painterResource(R.drawable.ic_empty_state_recipes),
        title = stringResource(R.string.empty_recipe_text),
    )
}