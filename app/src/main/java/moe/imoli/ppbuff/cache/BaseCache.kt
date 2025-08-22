package moe.imoli.ppbuff.cache

import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.ClassDataList
import org.luckypray.dexkit.result.FieldDataList
import org.luckypray.dexkit.result.MethodDataList

abstract class BaseCache(val label: String) {
    open fun cacheClass(dexkit: DexKitBridge): MutableMap<String, ClassDataList> {
        return mutableMapOf()
    }

    open fun cacheMethod(dexkit: DexKitBridge): MutableMap<String, MethodDataList> {
        return mutableMapOf()
    }

    open fun cacheField(dexkit: DexKitBridge): MutableMap<String, FieldDataList> {
        return mutableMapOf()
    }
}