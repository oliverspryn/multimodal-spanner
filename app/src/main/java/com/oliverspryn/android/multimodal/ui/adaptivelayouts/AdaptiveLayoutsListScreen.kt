package com.oliverspryn.android.multimodal.ui.adaptivelayouts

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oliverspryn.android.multimodal.ui.theme.MultimodalSpannerTheme

@Composable
fun AdaptiveLayoutsListScreen(
    numbers: List<Int> = (1..50).toList()
) {
    LazyColumn {
        items(numbers) { number ->
            RowWithNumber(
                number = number
            )
        }
    }
}

@Composable
private fun RowWithNumber(number: Int) {
    Box(
        modifier = Modifier
            .clickable(
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { }
            )
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = number.toString(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun PreviewAdaptiveLayoutsListScreen() {
    MultimodalSpannerTheme {
        AdaptiveLayoutsListScreen()
    }
}

@Preview(name = "Row Light Mode", showBackground = true)
@Preview(name = "Row Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewRowWithNumber() {
    MultimodalSpannerTheme {
        RowWithNumber(number = 42)
    }
}
