package dev.vengateshm.compose_material3.clean_error_handling

import java.net.ConnectException
import java.net.SocketException

class AuthRepositoryImpl : AuthRepository {
    override suspend fun register(password: String): Result<AuthUser, DataError.Network> {
        return try {
            Result.Success(AuthUser("", "", ""))
        } catch (e: SocketException) {
            Result.Error(DataError.Network.NO_INTERNET)
        } catch (e: ConnectException) {
            Result.Error(DataError.Network.NO_INTERNET)
        }
    }
}