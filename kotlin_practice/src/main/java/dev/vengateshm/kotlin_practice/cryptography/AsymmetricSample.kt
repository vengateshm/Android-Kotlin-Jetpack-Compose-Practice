package dev.vengateshm.kotlin_practice.cryptography

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

fun generateKeyPair(): KeyPair {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(2048) // You can adjust the key size as needed
    return keyPairGenerator.generateKeyPair()
}

fun encryptData(data: ByteArray, publicKey: PublicKey): ByteArray {
    val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
    cipher.init(Cipher.ENCRYPT_MODE, publicKey)
    return cipher.doFinal(data)
}

fun decryptData(encryptedData: ByteArray, privateKey: PrivateKey): ByteArray {
    val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
    cipher.init(Cipher.DECRYPT_MODE, privateKey)
    return cipher.doFinal(encryptedData)
}

fun main() {
    val dataToEncrypt = "Eating 🥭🍏🍌!".toByteArray()

    // Generate key pair
    val keyPair = generateKeyPair()
    val publicKey = keyPair.public
    val privateKey = keyPair.private

    // Encrypt data with the public key
    val encryptedData = encryptData(dataToEncrypt, publicKey)

    // Decrypt data with the private key
    val decryptedData = decryptData(encryptedData, privateKey)

    println("Original data: ${String(dataToEncrypt)}")
    println("Encrypted data: ${String(encryptedData)}")
    println("Decrypted data: ${String(decryptedData)}")
}