package dev.vengateshm.compose_material3.api_android.credential_manager

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialOption
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.vengateshm.compose_material3.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleSignInUtils {
    companion object {
        fun doGoogleSignIn(
            context: Context,
            scope: CoroutineScope,
            launcher: ManagedActivityResultLauncher<Intent, ActivityResult>?,
            login: () -> Unit,
        ) {
            val credentialsManager = CredentialManager.create(context)
            val credentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(getCredentialOption(context))
                .build()

            scope.launch {
                try {
                    val result = credentialsManager.getCredential(context, credentialRequest)
                    when (result.credential) {
                        is CustomCredential -> {
                            if (result.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                                val googleIdTokenCredential =
                                    GoogleIdTokenCredential.createFrom(result.credential.data)
                                val idToken = googleIdTokenCredential.idToken
                                val firebaseCredential =
                                    GoogleAuthProvider.getCredential(idToken, null)
                                val user = FirebaseAuth.getInstance()
                                    .signInWithCredential(firebaseCredential).await().user
                                if (user != null && user.isAnonymous.not()) {
                                    login()
                                }
                            }
                        }

                        else -> Unit
                    }
                } catch (e: NoCredentialException) {
                    launcher?.launch(getIntent())
                } catch (e: GetCredentialException) {
                    e.printStackTrace()
                }
            }
        }

        private fun getIntent(): Intent {
            return Intent(Settings.ACTION_ADD_ACCOUNT).apply {
                putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
            }
        }

        private fun getCredentialOption(context: Context): CredentialOption {
            return GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setAutoSelectEnabled(false)
                .setServerClientId(context.getString(R.string.web_client_id))
                .build()
        }
    }
}