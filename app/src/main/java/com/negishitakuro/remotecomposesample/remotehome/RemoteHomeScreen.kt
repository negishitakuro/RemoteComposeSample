package com.negishitakuro.remotecomposesample.remotehome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.remote.core.CoreDocument
import androidx.compose.remote.player.compose.RemoteDocumentPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo

@Composable
fun RemoteHomeScreen(modifier: Modifier) {
    val windowInfo = LocalWindowInfo.current
    val repository = remember { RemoteUIRepository() }
    var document by remember { mutableStateOf<CoreDocument?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            document = repository.loadUI("home")
            isLoading = false
        } catch (e: Exception) {
            error = e.message
            isLoading = false
            e.printStackTrace()
        }
    }

    Box(Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            error != null -> Text("エラー: $error", Modifier.align(Alignment.Center))
            document != null -> RemoteDocumentPlayer(
                document = document!!,
                documentWidth = windowInfo.containerSize.width,
                documentHeight = windowInfo.containerSize.height,
                onAction = { actionId, value ->
                    println("Action: $actionId $value")
                }
            )
        }
    }
}
