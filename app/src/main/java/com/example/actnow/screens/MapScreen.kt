package com.example.actnow.screens

import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavHostController
import com.example.actnow.SingleMissionDto
import com.example.actnow.missionData
import com.example.actnow.viewmodels.MissionViewModel
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
fun MapScreenWrapper(navController: NavHostController, viewModel: MissionViewModel) {
    var selectedMission by remember { mutableStateOf<SingleMissionDto?>(null) }
    var lastSelectedMission by remember { mutableStateOf<SingleMissionDto?>(null) }

    if (selectedMission == null) {
        MapScreen(
            lastSelectedMission = lastSelectedMission,
            onSelectMission = { mission ->
                selectedMission = mission
                lastSelectedMission = mission
            }
        )
    } else {
        navController.navigate("details/${selectedMission?.id}")
    }
}

@Composable
fun MapScreen(
    lastSelectedMission: SingleMissionDto?,
    onSelectMission: (SingleMissionDto) -> Unit
) {
    val context = LocalContext.current

    Configuration.getInstance().apply {
        osmdroidBasePath = context.cacheDir
        osmdroidTileCache = context.cacheDir
        userAgentValue = context.packageName
    }

    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(9.5)
            controller.setCenter(GeoPoint(48.8583, 2.2944))

            overlays.add(RotationGestureOverlay(this).apply { isEnabled = true })
            overlays.add(CompassOverlay(context, InternalCompassOrientationProvider(context), this).apply { enableCompass() })
            overlays.add(MyLocationNewOverlay(GpsMyLocationProvider(context), this).apply {
                enableMyLocation()
                runOnFirstFix {
                    myLocation?.let { controller.setCenter(GeoPoint(it.latitude, it.longitude)) }
                    controller.setZoom(16.0)
                }
            })
        }
    }

    val missionGeoPoints by produceState(initialValue = emptyList<Pair<SingleMissionDto, GeoPoint>>(), key1 = missionData.missions) {
        val result = mutableListOf<Pair<SingleMissionDto, GeoPoint>>()
        for (mission in missionData.missions) {
            val address = "${mission.adresse.rue} ${mission.adresse.numero}, ${mission.adresse.codePostal} ${mission.adresse.ville}"
            val geo = geocodeAddresses(context, listOf(address)).firstOrNull()
            if (geo != null) result.add(mission to geo)
        }
        value = result
    }

    LaunchedEffect(missionGeoPoints, lastSelectedMission) {
        if (missionGeoPoints.isNotEmpty()) {

            val defaultMarker = context.getDrawable(org.osmdroid.library.R.drawable.marker_default)?.apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            }

            val selectedMarker = context.getDrawable(org.osmdroid.library.R.drawable.marker_default)?.apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                setColorFilter(android.graphics.Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN)
            }

            val overlayItems = missionGeoPoints.map { (mission, geo) ->
                OverlayItem(mission.titre, "", geo).apply {
                    setMarker(if (mission == lastSelectedMission) selectedMarker else defaultMarker)
                }
            }

            val overlay = ItemizedIconOverlay<OverlayItem>(
                overlayItems,
                object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                    override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
                        val (mission, _) = missionGeoPoints[index]
                        onSelectMission(mission)
                        return true
                    }

                    override fun onItemLongPress(index: Int, item: OverlayItem) = false
                },
                context
            )

            val preservedOverlays = mapView.overlays.filter { it !is ItemizedIconOverlay<*> }
            mapView.overlays.clear()
            mapView.overlays.addAll(preservedOverlays)
            mapView.overlays.add(overlay)

            lastSelectedMission?.let { mission ->
                val geo = missionGeoPoints.find { it.first == mission }?.second
                geo?.let {
                    mapView.controller.setCenter(it)
                    mapView.controller.setZoom(16.0)
                }
            } ?: run {
                val north = missionGeoPoints.maxOf { it.second.latitude }
                val south = missionGeoPoints.minOf { it.second.latitude }
                val east = missionGeoPoints.maxOf { it.second.longitude }
                val west = missionGeoPoints.minOf { it.second.longitude }
                mapView.zoomToBoundingBox(org.osmdroid.util.BoundingBox(north, east, south, west), true)
            }
        }
    }


    DisposableEffect(mapView) {
        mapView.onResume()

        onDispose {
            mapView.onPause()
            mapView.onDetach()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
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