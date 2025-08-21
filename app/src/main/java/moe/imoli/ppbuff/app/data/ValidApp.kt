package moe.imoli.ppbuff.app.data

import android.os.Bundle

data class ValidApp(
    val packageName: String,
    val minVersionName: String,
    val maxVersionName: String,
    val minVersion: Long,
    val maxVersion: Long,
    val settings: MutableList<out ValidAppSetting<out Any>>,
) {
    fun provideSettings(bundle: Bundle) {
        settings.forEach { it.provide(bundle) }
    }

    fun update(data: Bundle) {
        settings.forEach { it.update(data) }
    }
}
