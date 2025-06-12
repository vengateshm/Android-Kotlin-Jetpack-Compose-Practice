package dev.vengateshm.appcore.utility

import android.util.Log
import dev.vengateshm.samples_common.BuildConfig

object LogUtil {
    enum class LogType { DEBUG, INFO, WARN, ERROR }

    fun log(keyName: String, value: Any?, logType: LogType = LogType.DEBUG) {
        if (!BuildConfig.DEBUG) return
        val stackTrace = Throwable().stackTrace[1]
        val tag = stackTrace.className.substringAfterLast(".")
        val fileName = stackTrace.fileName
        val methodName = stackTrace.methodName
        val lineNumber = stackTrace.lineNumber

        val dataType = value?.let { it::class.simpleName } ?: "null"
        val logMessage = "($fileName:$lineNumber) -> $keyName ($dataType) = $value"

        when (logType) {
            LogType.DEBUG -> Log.d(tag, logMessage)
            LogType.INFO -> Log.i(tag, logMessage)
            LogType.WARN -> Log.w(tag, logMessage)
            LogType.ERROR -> Log.e(tag, logMessage)
        }
    }
}