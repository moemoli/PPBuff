package moe.imoli.ppbuff

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import moe.imoli.ppbuff.app.data.ValidApps
import moe.imoli.ppbuff.hook.ConfigLoader


@InjectYukiHookWithXposed
object HookEntry : IYukiHookXposedInit {

    override fun onHook() = YukiHookAPI.encase {
        ValidApps.init(prefs = prefs)
        loadHooker(ConfigLoader())
    }


    override fun onInit() = configs {
        isDebug = BuildConfig.DEBUG
    }
}