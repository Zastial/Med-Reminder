package com.example.frontend_android.feature_prescription.presentation.util

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.media.Image
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.core.content.ContextCompat
import com.example.frontend_android.R


sealed class Screen(val route: String, val title: String) {

    object cameraScreen: Screen(
        route = "camera_screen",
        title = R.string.screen_title_camera.toString()
    )
    object prescriptionsScreen: Screen(
        route ="prescriptions_screen",
        title = R.string.screen_title_prescriptions.toString()
    )
    object alarmsScreen: Screen(
        route = "alarms_screen",
        title = R.string.screen_title_alarms.toString()
    )

    object otherScreen: Screen(
        route = "otherScreen",
        title = R.string.default_name.toString()
    )

}
