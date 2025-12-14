package com.negishitakuro.remotecomposesample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.initialize
import com.negishitakuro.remotecomposesample.remotehome.RemoteHomeActivity
import com.negishitakuro.remotecomposesample.remotehome.RemoteHomeScreen
import com.negishitakuro.remotecomposesample.ui.theme.RemoteComposeSampleTheme
import com.negishitakuro.remotecomposesample.uploader.UploadActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            RemoteComposeSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Button(
                            onClick = { startUploadActivity() },
                        ) {
                            Text("アップローダーへ移動")
                        }
                        Button(
                            onClick = { startRemoteHomeActivity() },
                        ) {
                            Text("アプリをホームへ移動")
                        }
                    }
                }
            }
        }
    }

    fun startUploadActivity() {
        val intent = Intent(this, UploadActivity::class.java)
        startActivity(intent)
    }

    fun startRemoteHomeActivity() {
        val intent = Intent(this, RemoteHomeActivity::class.java)
        startActivity(intent)
    }
}
