package moe.imoli.ppbuff.cache.zyl

import moe.imoli.ppbuff.cache.BaseCache
import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.ClassDataList

object ZyliteNoWaterMarkCache : BaseCache("no_water_mark") {
    val VIDEO_DOWNLOADER = "cn.xiaochuankeji.zuiyouLite.download.VideoDownloader"

    override fun cacheClass(dexkit: DexKitBridge): MutableMap<String, ClassDataList> {
        return mutableMapOf<String, ClassDataList>().apply {
            putAll(dexkit.batchFindClassUsingStrings {
                addSearchGroup(VIDEO_DOWNLOADER) {
                    add("download end failed null media url")
                }
            })
        }
    }
}