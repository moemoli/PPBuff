package moe.imoli.ppbuff.app.data

import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge

data class ValidAppSetting<T>(
    val type: ValidAppSettingType,
    val name: String,
    val label: String,
    var value: T,
    val prefs: YukiHookPrefsBridge,
    val visible: Boolean = true,
    val range: ClosedFloatingPointRange<Float> = 0f..1f,
    val clickable: () -> Unit = {},
) {

    init {
        update()
    }

    fun save() {
        val edit = prefs.edit()
        when (value) {
            is Int -> edit.putInt(label, value as Int).apply()
            is Float -> edit.putFloat(label, value as Float).apply()
            is String -> edit.putString(label, value.toString()).apply()
            is Boolean -> edit.putBoolean(label, value as Boolean).apply()
        }
    }

    fun update() {
        value = when (value) {
            is Int -> prefs.getInt(label, value as Int)
            is Float -> prefs.getFloat(label, value as Float)
            is String -> prefs.getString(label, value.toString())
            is Boolean -> prefs.getBoolean(label, value as Boolean)
            else -> value
        } as T
    }

    fun save(value: Any) {
        this.value = value as T
        save()
    }
}
