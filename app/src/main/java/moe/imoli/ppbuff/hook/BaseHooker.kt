package moe.imoli.ppbuff.hook

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.app.data.ValidApps

abstract class BaseHooker(val label: String) : YukiBaseHooker() {
    val settings = ValidApps.validData(packageName)!!
        .settings

    fun isEnable(): Boolean {
        return settings.find { it.label == label }?.value as Boolean
    }

    fun load(hooker: YukiBaseHooker) {
        YLog.debug("Hook $label status is :${isEnable()}")
        if (isEnable()) {
            hooker.loadHooker(this)
        }
    }

}