package com.example.actnow.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.actnow.viewmodels.MapViewModel
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

//sur le simulateur, ça prend un peu du temps pour charger la position qui est en plus aux Etats-Unis
//marche meiux sur la tablette/un vrai portable
@Composable
fun MapScreen(navController: NavController, mapViewModel: MapViewModel) {
    val context = LocalContext.current

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            val rotationOverlay = RotationGestureOverlay(this)
            rotationOverlay.isEnabled = true
            overlays.add(rotationOverlay)

            val compassOverlay =
                CompassOverlay(context, InternalCompassOrientationProvider(context), this)
            compassOverlay.enableCompass()
            overlays.add(compassOverlay)
        }
    }
    var locationOverlay by remember { mutableStateOf<MyLocationNewOverlay?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        ) { mapView ->

            if (locationOverlay == null) {
                val locOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)
                locOverlay.enableMyLocation()
                mapView.overlays.add(locOverlay)
                locationOverlay = locOverlay

                locOverlay.runOnFirstFix {
                    mapView.post {
                        if (mapViewModel.lastId.isBlank()) {
                            mapView.controller.setZoom(17.0)
                            mapView.controller.setCenter(locOverlay.myLocation)
                        } else {
                            mapViewModel.missionGeoPoints[mapViewModel.lastId]?.let { geo ->
                                mapView.controller.setZoom(17.0)
                                mapView.controller.setCenter(geo)
                            }
                        }
                    }
                }
            }

            if (mapView.overlays.none { it is Marker }) {
                mapViewModel.missionGeoPoints.forEach { (id, geoPoint) ->
                    val marker = Marker(mapView).apply {
                        position = geoPoint
                        relatedObject = id
                        title = mapViewModel.missions.find { it.id == id }?.titre ?: "Mission $id"
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    }
                    marker.setOnMarkerClickListener { marker, _ ->
                        val missionId = marker.relatedObject as String
                        navController.navigate("details/$missionId")
                        mapViewModel.lastId = missionId
                        true
                    }
                    mapView.overlays.add(marker)
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                val userLocation = locationOverlay?.myLocation
                if (userLocation != null) {
                    mapView.controller?.setZoom(15.0)
                    mapView.controller?.animateTo(userLocation)
                }
            }
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = "Ma position")
        }
    }
}