import java.io.FileInputStream
import java.util.Properties

val propertiesFile = file("../gradle.properties")

fun getAppVersionCode(): Int {
    val properties = Properties()
    FileInputStream(propertiesFile).use { properties.load(it) }

    val currentVersionCode = properties.getProperty("versionCode")?.toInt()
        ?: throw IllegalStateException("versionCode not found in gradle.properties")
    val newVersionCode = currentVersionCode + 1

    // Overwrite
    /*properties.setProperty("versionCode", newVersionCode.toString())
    FileOutputStream(propertiesFile).use { properties.store(it, null) }*/

    return newVersionCode
}

fun getAppVersionName(): String {
    val properties = Properties()
    FileInputStream(propertiesFile).use { properties.load(it) }

    val currentVersionName = properties.getProperty("versionName")
        ?: throw IllegalStateException("versionName not found in gradle.properties")

    val versionParts = currentVersionName.split('.').toMutableList()
    val lastPartIndex = versionParts.lastIndex
    versionParts[lastPartIndex] = (versionParts[lastPartIndex].toInt() + 1).toString()

    val newVersionName = versionParts.joinToString(".")

    // Overwrite
    /*properties.setProperty("versionName", newVersionName)
    FileOutputStream(propertiesFile).use { properties.store(it, null) }*/

    return newVersionName
}

extra["appVersionCode"] = getAppVersionCode()
extra["appVersionName"] = getAppVersionName()