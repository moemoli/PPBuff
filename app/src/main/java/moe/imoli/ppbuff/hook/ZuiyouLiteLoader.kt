package moe.imoli.ppbuff.hook

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.app.data.ValidApps
import moe.imoli.ppbuff.hook.zylite.ZyliteNoAdHooker

object ZuiyouLiteLoader : YukiBaseHooker() {


    override fun onHook() {
        YLog.debug("Try load config for $packageName")
        val settings = ValidApps.validData(packageName)!!
            .settings

        val no_ad = settings.find { it.label == "no_ad" }?.value as Boolean
        YLog.debug("Hook NoAd status is :${no_ad}")
        if (no_ad) {
            loadHooker(ZyliteNoAdHooker)
        }

    }
}