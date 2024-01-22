package dev.vengateshm.android_kotlin_compose_practice.crypto

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@RequiresApi(Build.VERSION_CODES.M)
class CryptoActivity : ComponentActivity() {
    private val cryptoManager by lazy {
        CryptoManager()
    }

    private val Context.dataStore by dataStore(
        fileName = "user-settings.json",
        serializer = UserSettingsSerializer(cryptoManager),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                EncryptedDatastoreComposable(dataStore)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun CryptoComposable(
    filesDir: File,
    cryptoManager: CryptoManager,
) {
    var messageToEncrypt by remember {
        mutableStateOf("")
    }

    var messageToDecrypt by remember {
        mutableStateOf("")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = messageToEncrypt,
            onValueChange = {
                messageToEncrypt = it
            },
            placeholder = {
                Text(text = "Enter text to encrypt!")
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = {
                val bytes = messageToEncrypt.encodeToByteArray()
                val file = File(filesDir, "secret.txt")
                if (file.exists().not()) {
                    file.createNewFile()
                }
                val fos = FileOutputStream(file)
                messageToDecrypt =
                    cryptoManager.encrypt(
                        bytes = bytes,
                        outputStream = fos,
                    ).decodeToString()
            }) {
                Text(text = "Encrypt")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                val file = File(filesDir, "secret.txt")
                messageToEncrypt =
                    cryptoManager.decrypt(
                        inputStream = FileInputStream(file),
                    ).decodeToString()
            }) {
                Text(text = "Decrypt")
            }
        }

        Text(text = messageToDecrypt)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun EncryptedDatastoreComposable(dataStore: DataStore<UserSettings>) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var settings by remember {
        mutableStateOf(UserSettings())
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
            },
            placeholder = {
                Text(text = "Username")
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = "Password")
            },
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = {
                scope.launch {
                    dataStore.updateData {
                        UserSettings(
                            username = username,
                            password = password,
                        )
                    }
                }
            }) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                scope.launch {
                    settings = dataStore.data.first()
                }
            }) {
                Text(text = "Load")
            }
        }
        Text(text = settings.toString())
    }
}
