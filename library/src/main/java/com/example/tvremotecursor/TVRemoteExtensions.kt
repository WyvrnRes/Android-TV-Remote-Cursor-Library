package com.example.tvremotecursor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned

/**
 * Extension function to make any Composable TV remote clickable
 * This is useful for retrofitting existing UI components
 */
@Composable
fun Modifier.tvRemoteClickable(
    onClick: () -> Unit
): Modifier {
    val controller = LocalTVRemoteCursorController.current
    var bounds by remember { mutableStateOf(Rect.Zero) }
    
    val modifier = this
        .onGloballyPositioned { layoutCoordinates ->
            bounds = layoutCoordinates.boundsInRoot()
        }
        .clickable { onClick() } // Preserve normal click behavior
    
    DisposableEffect(bounds, controller) {
        if (controller != null && bounds != Rect.Zero) {
            val clickableArea = ClickableArea(bounds, onClick)
            controller.registerClickableArea(clickableArea)
            
            onDispose {
                controller.unregisterClickableArea(clickableArea)
            }
        } else {
            onDispose { }
        }
    }
    
    return modifier
}

/**
 * Higher-order composable that wraps content with TV remote click functionality
 * Alternative to TVRemoteClickable for inline usage
 */
@Composable
fun withTVRemoteClick(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.tvRemoteClickable(onClick)
    ) {
        content()
    }
}