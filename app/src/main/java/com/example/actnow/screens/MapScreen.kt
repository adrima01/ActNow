package com.example.actnow.screens

import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.layout.Box
import androidx.preference.PreferenceManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.actnow.missionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@Composable
fun MapScreen() {
    val context = LocalContext.current

    val geoPoints by produceState(initialValue = emptyList<GeoPoint>(), key1 = missionData.missions) {
        value = geocodeAddresses(
            context,
            missionData.missions.map {
                "${it.adresse.rue} ${it.adresse.numero}, ${it.adresse.codePostal} ${it.adresse.ville}"
            }
        )
    }

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(9.5)
            controller.setCenter(GeoPoint(48.8583, 2.2944)) // Startpunkt: Eiffelturm

            overlays.add(RotationGestureOverlay(this).apply { isEnabled = true })

            overlays.add(CompassOverlay(context, InternalCompassOrientationProvider(context), this).apply { enableCompass() })

            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this).apply {
                enableMyLocation()
                runOnFirstFix {
                    myLocation?.let { controller.setCenter(GeoPoint(it.latitude, it.longitude)) }
                    controller.setZoom(16.0)
                }
            }
            overlays.add(locationOverlay)
        }
    }

    LaunchedEffect(geoPoints) {
        if (geoPoints.isNotEmpty()) {
            val overlay = ItemizedIconOverlay(
                geoPoints.map { OverlayItem("Mission", "", it) },
                object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                    override fun onItemSingleTapUp(index: Int, item: OverlayItem) = true
                    override fun onItemLongPress(index: Int, item: OverlayItem) = false
                },
                context
            )
            mapView.overlays.add(overlay)

            val north = geoPoints.maxOf { it.latitude }
            val south = geoPoints.minOf { it.latitude }
            val east = geoPoints.maxOf { it.longitude }
            val west = geoPoints.minOf { it.longitude }
            mapView.zoomToBoundingBox(org.osmdroid.util.BoundingBox(north, east, south, west), true)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView.onPause()
            mapView.onDetach()
        }
    }

    Box(Modifier.fillMaxSize()) {
        AndroidView(factory = { mapView }, modifier = Modifier.fillMaxSize())

        Button(
            onClick = {
                val locationOverlay = mapView.overlays.filterIsInstance<MyLocationNewOverlay>().firstOrNull()
                locationOverlay?.myLocation?.let {
                    mapView.controller.setCenter(GeoPoint(it.latitude, it.longitude))
                    mapView.controller.setZoom(16.0)
                    mapView.mapOrientation = 0f
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.MyLocation, contentDescription = "Location")
        }
    }
}

suspend fun geocodeAddresses(context: Context, addresses: List<String>): List<GeoPoint> {
    return withContext(Dispatchers.IO) {
        addresses.mapNotNull { address ->
            try {
                val geocoder = Geocoder(context)
                geocoder.getFromLocationName(address, 1)?.firstOrNull()?.let {
                    GeoPoint(it.latitude, it.longitude)
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
