package moe.imoli.ppbuff.hook

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import com.highcapable.kavaref.KavaRef.Companion.resolve
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.HookEntry
import moe.imoli.ppbuff.app.data.ValidApps

class ConfigLoader : YukiBaseHooker() {
    override fun onHook() {
        YLog.debug("Try load config for $packageName")
        // 判断是否为内置
        val url = HookEntry::class.java.classLoader.getResource("assets/xposed_init")
        YLog.debug("Loading $packageName from $url")
        if ("$url".contains("lspatch")) {
            // 远程请求模块配置
            Application::class.java
                .resolve()
                .method { name = "attach" }
                .first()
                .hook {
                    after {
                        val ctx = instance as Application
                        val uri = "content://moe.imoli.ppbuff.provider.config/".toUri()
                        val cursor = ctx.contentResolver
                            .query(uri, null, null, null, null)
                        if (cursor == null) {
                            Toast.makeText(ctx, "没有检测到已安装的模块，无法读取配置...", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            val data = cursor.respond(Bundle().apply {
                                putString("packageName", packageName)
                            })
                            if (data == null) {
                                Toast.makeText(ctx, "模块可能未在运行，配置读取失败...", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                ValidApps.apps.find { it.packageName == packageName }?.update(data)
                                initHookers()
                            }
                        }


                    }
                }
        } else {
            initHookers()
        }
    }

    fun initHookers(){
        loadApp("cn.xiaochuankeji.zuiyouLite", ZuiyouLiteLoader)
    }
}