package moe.imoli.ppbuff

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit


@InjectYukiHookWithXposed
object HookEntry : IYukiHookXposedInit {

    override fun onHook() = YukiHookAPI.encase {

    }

    override fun onInit() = configs {
        isDebug = BuildConfig.DEBUG
    }
}