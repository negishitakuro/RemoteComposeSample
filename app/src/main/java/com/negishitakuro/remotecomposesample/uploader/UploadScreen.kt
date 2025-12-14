package com.negishitakuro.remotecomposesample.uploader

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.remote.creation.compose.capture.captureRemoteDocument
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun UploadScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf("準備完了") }
    var uploadedFiles by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "RemoteCompose アップローダー",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("ステータス: $status")

        // 一括アップロード
        Button(
            onClick = {
                scope.launch {
                    status = "全画面をアップロード中..."
                    try {
                        val uploader = RemoteUIUploader(context)
                        val files = uploader.uploadAllScreens()
                        uploadedFiles = files
                        status = "✅ 全アップロード完了 (${files.size}ファイル)"
                    } catch (e: Exception) {
                        status = "❌ エラー: ${e.message}"
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("全画面を一括アップロード")
        }

        Text("アップロード済み:", style = MaterialTheme.typography.titleMedium)
        uploadedFiles.forEach { file ->
            Text("  ✓ $file", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
