package dev.vengateshm.compose_material3.api_android.credential_manager

import android.app.Activity
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException

class UserCredentialsManager(
    private val activity: Activity,
) {
    private val credentialsManager = CredentialManager.create(activity)

    suspend fun signUp(username: String, password: String): SignUpResult {
        return try {
            credentialsManager.createCredential(
                context = activity,
                request = CreatePasswordRequest(
                    id = username,
                    password = password,
                ),
            )
            SignUpResult.Success(username)
        } catch (e: CreateCredentialCancellationException) {
            e.printStackTrace()
            SignUpResult.Cancelled
        } catch (e: CreateCredentialException) {
            e.printStackTrace()
            SignUpResult.Failure
        }
    }

    suspend fun signIn(): SignInResult {
        return try {
            val credentialResponse = credentialsManager.getCredential(
                context = activity,
                request = GetCredentialRequest(
                    credentialOptions = listOf(
                        GetPasswordOption(),
                    ),
                ),
            )
            val credential =
                credentialResponse.credential as? PasswordCredential ?: return SignInResult.Failure
            //Make your login API call here
            SignInResult.Success(credential.id)
        } catch (e: GetCredentialCancellationException) {
            e.printStackTrace()
            SignInResult.Cancelled
        } catch (e: NoCredentialException) {
            e.printStackTrace()
            SignInResult.NoCredentials
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            SignInResult.Failure
        }
    }
}