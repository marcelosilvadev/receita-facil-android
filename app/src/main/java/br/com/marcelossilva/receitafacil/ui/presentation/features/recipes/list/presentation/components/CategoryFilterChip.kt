package br.com.marcelossilva.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.R
import br.com.marcelossilva.receitafacil.core.domain.model.CategoryEnum
import br.com.marcelossilva.receitafacil.ui.theme.LightChip
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun CategoryFilterChip(
    modifier: Modifier = Modifier,
    category: Int?,
    isSelected: Boolean,
    onClick: (isSelected: Boolean) -> Unit,
) {
    val categoryEnum = category?.let { CategoryEnum.fromInt(category) }

    val labelText: String = when (categoryEnum) {
        null -> "Todas"
        else -> categoryEnum.description
    }

    val iconPainter = if (category == null) {
        painterResource(R.drawable.outline_filter_list)
    } else {
        categoryEnum?.iconRes?.let {
            painterResource(it)
        }
    }

    FilterChip(
        modifier = modifier
            .padding(2.dp)
            .heightIn(min = 32.dp),
        elevation = FilterChipDefaults.filterChipElevation(
            elevation = 8.dp
        ),
        leadingIcon = {
            iconPainter?.let { painter ->
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painter,
                    contentDescription = "Icone de categoria",
                    tint = if (isSelected) Color.White else LightChip
                )
            }
        },
        border = FilterChipDefaults.filterChipBorder(
            enabled = false,
            selected = isSelected,
            disabledBorderColor = Color.Gray,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp,
            selectedBorderColor = Color.Transparent
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = LightChip
        ),
        selected = isSelected,
        onClick = { onClick(!isSelected) },
        label = {
            Text(
                text = labelText,
                color = if (isSelected) Color.White else Color.Black,
            )
        }
    )
}

@Preview(showBackground = false)
@Composable
private fun CategoryFilterChipPreview() {
    ReceitaFacilAppTheme {
        CategoryFilterChip(
            category = CategoryEnum.Breakfast.value,
            isSelected = false,
            onClick = {}
        )
    }

}