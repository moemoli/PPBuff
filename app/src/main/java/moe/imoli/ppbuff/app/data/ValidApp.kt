package moe.imoli.ppbuff.app.data

data class ValidApp(
    val packageName: String,
    val minVersion: String,
    val maxVersion: String,
    val settings: MutableList<out ValidAppSetting<out Any>>,
)
