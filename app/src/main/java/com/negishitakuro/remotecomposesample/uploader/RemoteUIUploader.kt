package com.negishitakuro.remotecomposesample.uploader

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.remote.creation.compose.action.Action
import androidx.compose.remote.creation.compose.capture.captureRemoteDocument
import androidx.compose.remote.creation.compose.layout.RemoteBox
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteText
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.background
import androidx.compose.remote.creation.compose.modifier.clickable
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class RemoteUIUploader(private val context: Context) {

    private val storage = FirebaseStorage.getInstance()

    suspend fun uploadAllScreens(): List<String> {
        val files = mutableListOf<String>()

        // 各画面を生成してアップロード
        uploadScreen("home", createHomeUI()).also { files.add("home.bin") }
        uploadScreen("product_list", createProductListUI()).also { files.add("product_list.bin") }
        uploadScreen("checkout", createCheckoutUI()).also { files.add("checkout.bin") }

        return files
    }

    private suspend fun uploadScreen(name: String, bytes: ByteArray) {
        val ref = storage.reference.child("ui/$name.bin")
        ref.putBytes(bytes).await()
        println("✅ Uploaded: $name.bin (${bytes.size} bytes)")
    }

    // ホーム画面UI生成
    private suspend fun createHomeUI(): ByteArray {
        return captureRemoteDocument(
            context = context
        ) {
            // Remote* APIを使用
            RemoteColumn(
                modifier = RemoteModifier
                .fillMaxSize()
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                RemoteText(
                    text = "🎉 リモートUI成功！",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                RemoteText(
                    text = "このUIは動的に生成されました1",
                    style = TextStyle(
                        fontSize = 12.sp,
                    ),
                )
            }
        }
    }

    // 商品一覧UI生成
    private suspend fun createProductListUI(): ByteArray {
        return captureRemoteDocument(
            context = context
        ) {
            // Remote* APIを使用
            RemoteColumn(
                modifier = RemoteModifier
                .fillMaxSize()
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                RemoteText(
                    text = "🎉 リモートUI成功！",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )

                RemoteText(
                    text = "このUIは動的に生成されました2",
                    style = TextStyle(
                        fontSize = 12.sp,
                    ),
                )
            }
        }
    }

    // チェックアウトUI生成
    private suspend fun createCheckoutUI(): ByteArray {
        return captureRemoteDocument(
            context = context
        ) {
            // Remote* APIを使用
            RemoteColumn(
                modifier = RemoteModifier
                .fillMaxSize()
                    .background(Color.Red)
                    .padding(16.dp)
            ) {
                RemoteText(
                    text = "🎉 リモートUI成功！",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )

                RemoteText(
                    text = "このUIは動的に生成されました3",
                    style = TextStyle(
                        fontSize = 12.sp,
                    ),
                )
            }
        }
    }
}