package moe.imoli.ppbuff.app.data

data class ValidAppSetting<T>(
    val type: ValidAppSettingType,
    val name: String,
    val label: String,
    var value: T,
    val visible: Boolean = true,
    val range: ClosedFloatingPointRange<Float> = 0f..1f,
    val clickable: () -> Unit = {},
) {
    init {
        update()
    }


    fun save() {

    }

    fun update() {

    }

    fun save(value: Any) {
        this.value = value as T
        save()
    }
}
