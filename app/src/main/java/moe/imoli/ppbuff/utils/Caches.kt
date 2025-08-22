package moe.imoli.ppbuff.utils

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.edit
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.cache.zyl.ZyliteNoWaterMarkCache
import org.luckypray.dexkit.DexKitBridge

object Caches {

    private val caches = mutableListOf(
        ZyliteNoWaterMarkCache,// 无水印缓存
    )

    fun update(ctx: Application) {
        val prefs = ctx.getSharedPreferences("caches", Context.MODE_PRIVATE)
        val cacheVersion = prefs.getLong("cache_version", 0)
        val appVersion = ctx.packageManager.getPackageInfo(ctx.packageName, PackageManager.MATCH_ALL).longVersionCode
        if (cacheVersion == appVersion) return

        YLog.debug("not a valid cache version $cacheVersion ($appVersion)")
        // 版本不匹配
        prefs.edit {
            clear()
            putLong("cache_version", appVersion)
        }
        val path = ctx.classLoader.getResource("AndroidManifest.xml").toString()
            .substringBefore("!")
            .replace("!", "")
            .replace("jar:file:", "")
        YLog.debug("try init dexkit")
        val dexkit = DexKitBridge.create(path)

        caches.forEach { cache ->
            YLog.debug("try cache $cache")
            val map = mutableMapOf<String, MutableMap<String, MutableSet<String>>>()
            YLog.debug("try cache class $cache")
            cache.cacheClass(dexkit).forEach { value ->
                val set = mutableSetOf<String>()
                value.value.forEach { clazz ->
                    set.add(clazz.toDexType().serialize())
                }
                map.put("class", mutableMapOf<String, MutableSet<String>>().apply {
                    put(value.key, set)
                })
            }
            YLog.debug("try cache field $caches")
            cache.cacheField(dexkit).forEach { value ->
                val set = mutableSetOf<String>()
                value.value.forEach { clazz ->
                    set.add(clazz.toDexField().serialize())
                }
                map.put("field", mutableMapOf<String, MutableSet<String>>().apply {
                    put(value.key, set)
                })
            }
            YLog.debug("try cache method $cache")
            cache.cacheMethod(dexkit).forEach { value ->
                val set = mutableSetOf<String>()
                value.value.forEach { clazz ->
                    set.add(clazz.toDexMethod().serialize())
                }
                map.put("method", mutableMapOf<String, MutableSet<String>>().apply {
                    put(value.key, set)
                })
            }
            YLog.debug("try save cache $cache, results: $map")
            map.forEach { entry ->
                prefs.edit {
                    entry.value.forEach { value ->
                        putStringSet("${cache.label}_${entry.key}_${value.key}", value.value)
                    }
                }
            }
        }
        YLog.debug("try close dexkit")
        dexkit.close()
    }

    fun read(label: String, type: String, key: String, ctx: Context): MutableSet<String> {
        val prefs = ctx.getSharedPreferences("caches", Context.MODE_PRIVATE)
        val set = prefs.getStringSet("${label}_${type}_${key}", mutableSetOf())
        return set!!.toMutableSet()
    }
}