package com.oliverspryn.android.multimodal.sample.ui.screeninfo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oliverspryn.android.multimodal.model.Dimension
import com.oliverspryn.android.multimodal.model.ScreenClassifier
import com.oliverspryn.android.multimodal.model.WindowSizeClass
import com.oliverspryn.android.multimodal.sample.R
import com.oliverspryn.android.multimodal.sample.ui.theme.MultimodalSpannerTheme

@Composable
fun ScreenInfoScreen(screenClassifier: ScreenClassifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = screenClassifier::class.java.simpleName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = screenClassifier.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (screenClassifier is ScreenClassifier.HalfOpened) {
                Text(
                    text = stringResource(id = R.string.hinge_position),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(
                        R.string.hinge_position_coordinates,
                        screenClassifier.hingePosition.top,
                        screenClassifier.hingePosition.bottom,
                        screenClassifier.hingePosition.left,
                        screenClassifier.hingePosition.right
                    ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(
                        R.string.hinge_position_size,
                        screenClassifier.hingePosition.width(),
                        screenClassifier.hingePosition.height()
                    ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewBookMode() {
    MultimodalSpannerTheme {
        ScreenInfoScreen(
            screenClassifier = ScreenClassifier.FullyOpened(
                height = Dimension(
                    dp = 1080.dp,
                    sizeClass = WindowSizeClass.EXPANDED
                ),
                width = Dimension(
                    dp = 1920.dp,
                    sizeClass = WindowSizeClass.EXPANDED
                )
            )
        )
    }
}
