package moe.imoli.ppbuff.app.data

data class ValidApp(
    val packageName: String,
    val minVersionName: String,
    val maxVersionName: String,
    val minVersion: Long,
    val maxVersion: Long,
    val settings: MutableList<out ValidAppSetting<out Any>>,
)
