package com.example.mapsapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    val shape = RoundedCornerShape(30.dp)
    val modifier = Modifier
        .background(color = Color.White, shape = shape)
        .fillMaxWidth()
    BasicTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        modifier = modifier
            .border(width = 1.dp, shape = shape, color = Color.Gray),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) {
                        Text(
                            text = "Search",
                            style = LocalTextStyle.current.copy(
                                color = Color.Gray,
                            )
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun GMap() {
    val singapore = LatLng(1.35, 103.87)
    val singaporeMarkerState = rememberMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = singaporeMarkerState,
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}
