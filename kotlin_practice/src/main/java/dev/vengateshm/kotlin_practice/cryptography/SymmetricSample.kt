package dev.vengateshm.kotlin_practice.cryptography

import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

val secureKey by lazy {
    generateKey()
}

fun generateKey(): String {
    val random = SecureRandom()
    val keyBytes = ByteArray(16)
    random.nextBytes(keyBytes)
    return Base64.getEncoder().encodeToString(keyBytes)
}

fun main() {
    val data = "Hello World 🥭"
    val encryptedData = encrypt(data, secureKey)
    println("Encrypted data : $encryptedData")
    val decryptedData = decrypt(encryptedData, secureKey)
    println("Decrypted data : $decryptedData")
}

fun encrypt(data: String, key: String): String {
    val secretKey = SecretKeySpec(key.toByteArray(), "AES")
    println("secretKey encryption : $secretKey")
    val cipher = Cipher.getInstance("AES/CBC/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    val encryptedData = cipher.doFinal(data.toByteArray())
    return Base64.getEncoder().encodeToString(encryptedData)
}

fun decrypt(encryptedData: String, key: String): String {
    val secretKey = SecretKeySpec(key.toByteArray(), "AES")
    println("secretKey decryption : $secretKey")
    val cipher = Cipher.getInstance("AES/CBC/NoPadding")
    cipher.init(Cipher.DECRYPT_MODE, secretKey)
    val decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
    return String(decryptedData)
}