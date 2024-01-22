package dev.vengateshm.android_kotlin_compose_practice.localization_app

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.Locale

object LocaleHelper {
    fun setLocale(
        context: Context,
        locale: Locale,
    ) {
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }
}

class LocaleContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(
            context: Context,
            newLocale: Locale,
        ): ContextWrapper {
            var updatedContext = context

            val configuration = updatedContext.resources.configuration
            val newConfig = Configuration(configuration)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                newConfig.setLocale(newLocale)
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                newConfig.setLocales(localeList)
            } else {
                newConfig.setLocale(newLocale)
            }

            updatedContext = updatedContext.createConfigurationContext(newConfig)

            return LocaleContextWrapper(updatedContext)
        }
    }
}
