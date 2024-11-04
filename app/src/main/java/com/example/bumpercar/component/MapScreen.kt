package com.example.bumpercar.component

import android.content.Context
import android.location.Geocoder
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Locale

@Composable
fun MapScreen(context: Context, address: String) {
    val latLng = remember {
        getLatLngFromAddress(context, address)
    }

    if (latLng != null) {
        Box(modifier = Modifier
            .size(600.dp)
            .clip(RoundedCornerShape(20.dp))) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = rememberCameraPositionState {
                    position =
                        CameraPosition.fromLatLngZoom(LatLng(latLng.first, latLng.second), 15f)
                }
            ) {
                Marker(
                    state = MarkerState(LatLng(latLng.first, latLng.second)),
                    title = "위치",
                    snippet = address
                )
            }
        }
    } else {
        Text("주소를 변환할 수 없습니다.")
    }
}

// 주어진 주소를 위도와 경도로 변환하는 함수
fun getLatLngFromAddress(context: Context, address: String): Pair<Double, Double>? {
    // Geocoder 객체를 생성하여 주소를 위도/경도로 변환할 수 있도록 함
    val geocoder = Geocoder(context, Locale.getDefault())

    // 입력된 주소를 이용해 해당 위치의 정보를 가져옴 (최대 1개의 결과 반환)
    val addresses = geocoder.getFromLocationName(address, 1)

    // 주소 목록이 null이 아니고 비어있지 않은 경우, 첫 번째 주소의 위도와 경도를 반환
    return if (addresses != null && addresses.isNotEmpty()) {
        val location = addresses[0]  // 첫 번째 주소 객체를 가져옴
        Pair(location.latitude, location.longitude)  // 위도와 경도를 Pair 객체로 반환
    } else {
        null
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleMapTestPreview() {
    val context = LocalContext.current
    MapScreen(context = context, address = "충남 천안시 서북구 불당23로 73-27 4층")
}