package moe.imoli.ppbuff.hook.zylite

import com.highcapable.kavaref.KavaRef.Companion.resolve
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.hook.BaseHooker
import java.lang.reflect.Array

object ZyliteNoAdHooker : BaseHooker("no_ad") {
    override fun onHook() {
        "cn.xiaochuankeji.hermes.core.Hermes".toClass()
            .resolve()
            .method { name = "init" }
            .first()
            .hook {
                before {
                    YLog.debug("Try drop all providers")
                    args(3).set(
                        Array.newInstance(
                            "cn.xiaochuankeji.hermes.core.provider.ADProvider".toClass(),
                            0
                        )
                    )
                }
            }
    }
}