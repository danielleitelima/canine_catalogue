package com.danielleitelima.canine_catalogue.presentation.photo_view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.danielleitelima.canine_catalogue.R
import kotlinx.coroutines.launch

@Composable
fun PhotoViewScreen(
    url: String,
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ZoomableImage(
            url,
            "Detail",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary, Color.Transparent
                        ), startY = 0f
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Detail",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun ZoomableImage(
    imageUrl: String, contentDescription: String, modifier: Modifier = Modifier.fillMaxSize()
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
            .build(),
            placeholder = painterResource(R.drawable.image_placeholder),
            contentDescription = contentDescription,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        scale = scale.coerceAtLeast(1f)

                        val newOffsetX = offsetX + pan.x * scale
                        val newOffsetY = offsetY + pan.y * scale

                        offsetX = newOffsetX
                        offsetY = newOffsetY
                    }

                    detectTapGestures(onDoubleTap = {
                        coroutineScope.launch {
                            if (scale != 1f) {
                                scale = 1f
                                offsetX = 0f
                                offsetY = 0f
                            } else {
                                scale = 2f
                            }
                        }
                    })
                }
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ))
    }
}

