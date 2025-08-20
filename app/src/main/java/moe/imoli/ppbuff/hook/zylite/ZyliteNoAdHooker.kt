package moe.imoli.ppbuff.hook.zylite

import com.highcapable.kavaref.KavaRef.Companion.resolve
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import java.lang.reflect.Array

object ZyliteNoAdHooker : YukiBaseHooker() {
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