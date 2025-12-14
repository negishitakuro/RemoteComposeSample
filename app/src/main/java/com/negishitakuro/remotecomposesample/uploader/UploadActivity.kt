package com.negishitakuro.remotecomposesample.uploader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.auth.FirebaseAuth

class UploadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 匿名認証（アップロード権限のため）
        FirebaseAuth.getInstance().signInAnonymously()

        setContent {
            UploadScreen()
        }
    }
}
