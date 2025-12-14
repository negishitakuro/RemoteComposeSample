package com.negishitakuro.remotecomposesample.remotehome

import androidx.compose.remote.core.CoreDocument
import androidx.compose.remote.core.RemoteComposeBuffer
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayInputStream
import java.io.File

class RemoteUIRepository {

    private val storage = FirebaseStorage.getInstance()

    suspend fun loadUI(
        screenName: String,
    ): CoreDocument {

        // Firebase Storageから取得
        println("☁️ Firebase Storageから取得: $screenName")
        val bytes = downloadFromFirebase("ui/$screenName.bin")

        return bytesToCoreDocument(bytes)
    }

    private suspend fun downloadFromFirebase(path: String): ByteArray {
        val ref = storage.reference.child(path)
        return ref.getBytes(10 * 1024 * 1024).await() // 10MB上限
    }

    private fun bytesToCoreDocument(bytes: ByteArray): CoreDocument {
        val inputStream = ByteArrayInputStream(bytes)
        val buffer = RemoteComposeBuffer.fromInputStream(inputStream)
        val document = CoreDocument()
        document.initFromBuffer(buffer)
        return document
    }
}