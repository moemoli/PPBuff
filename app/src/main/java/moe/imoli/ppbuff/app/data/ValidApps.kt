package moe.imoli.ppbuff.app.data

import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge

object ValidApps {

    val apps = mutableListOf<ValidApp>()


    fun validData(packageName: String): ValidApp? {
        return apps.find { it.packageName == packageName }
    }

    fun validApp(packageName: String): Boolean {
        return validData(packageName) != null
    }

    fun init(prefs: YukiHookPrefsBridge) {
        apps.add(

            ValidApp(
                packageName = "cn.xiaochuankeji.zuiyouLite",
                minVersionName = "2.99.168",
                maxVersionName = "2.99.168",
                minVersion = 29916800L,
                maxVersion = 29916800L,
                settings = mutableListOf(
                    ValidAppSetting(
                        type = ValidAppSettingType.SWITCH,
                        name = "去广告",
                        label = "no_ad",
                        prefs = prefs,
                        value = false,

                        ),
                    ValidAppSetting(
                        type = ValidAppSettingType.SWITCH,
                        name = "去水印",
                        label = "no_water_mark",
                        value = false,
                        prefs = prefs,

                    ),
                    ValidAppSetting(
                        type = ValidAppSettingType.SELECT,
                        name = "视频保存方式",
                        label = "video_method",
                        value = "",
                        prefs = prefs,
                    ),
                    ValidAppSetting(
                        type = ValidAppSettingType.INPUT_NUM,
                        name = "语音时长",
                        label = "voice_time",
                        value = 0f,
                        range = 0f..Float.MAX_VALUE,
                        prefs = prefs,
                        visible = false
                    ),
                    ValidAppSetting(
                        type = ValidAppSettingType.SELECT,
                        name = "语音路径",
                        label = "voice_path",
                        value = "",
                        prefs = prefs,
                        visible = false
                    ),
                    ValidAppSetting(
                        type = ValidAppSettingType.SWITCH,
                        name = "详细ip",
                        label = "loc_details",
                        value = false,
                        prefs = prefs,
                        visible = false
                    ),
                    ValidAppSetting(
                        type = ValidAppSettingType.INPUT_STR,
                        name = "地址格式",
                        label = "loc_format",
                        value = "",
                        prefs = prefs,
                        visible = false
                    ),
                    )
            )
        )
    }


}