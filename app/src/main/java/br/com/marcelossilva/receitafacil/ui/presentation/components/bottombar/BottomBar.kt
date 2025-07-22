package br.com.marcelossilva.receitafacil.ui.presentation.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.marcelossilva.receitafacil.R
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        actions = {
            IconButton(
                onClick = onNavigationToUsersConnectionScreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.PeopleOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onNavigationToSearchScreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onNavigationToProfileScreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigationToAddRecipeScreen,
                contentColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.new_recipe_text),
                        color = Color.White
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    ReceitaFacilAppTheme {
        BottomBar(
            modifier = Modifier,
            onNavigationToProfileScreen = {},
            onNavigationToSearchScreen = {},
            onNavigationToAddRecipeScreen = {},
            onNavigationToUsersConnectionScreen = {}
        )
    }
}