package br.com.marcelossilva.receitafacil.ui.presentation.components.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marcelossilva.receitafacil.ui.theme.ReceitaFacilAppTheme
import br.com.marcelossilva.receitafacil.ui.theme.poppinsFontFamily

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    message: String = ""
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 5.dp,
            strokeCap = StrokeCap.Round
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Justify,
            fontFamily = poppinsFontFamily
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingIndicatorPreview() {
    ReceitaFacilAppTheme {
        LoadingIndicator(
            message = "Carregando..."
        )
    }
}