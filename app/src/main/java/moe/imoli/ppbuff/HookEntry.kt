package moe.imoli.ppbuff

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import moe.imoli.ppbuff.app.data.ValidApps
import moe.imoli.ppbuff.hook.ZuiyouLiteLoader


@InjectYukiHookWithXposed
object HookEntry : IYukiHookXposedInit {

    override fun onHook() = YukiHookAPI.encase {
        ValidApps.init(prefs = prefs)
        loadApp("cn.xiaochuankeji.zuiyouLite", ZuiyouLiteLoader)
    }

    override fun onInit() = configs {
        isDebug = BuildConfig.DEBUG
    }
}