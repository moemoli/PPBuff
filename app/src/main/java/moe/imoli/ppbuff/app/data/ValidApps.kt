package moe.imoli.ppbuff.app.data

object ValidApps {

    val apps = mutableListOf<ValidApp>().apply {
        add(
            ValidApp(
                "cn.xiaochuankeji.zuiyouLite",
                "",
                "",
                mutableListOf(
                    ValidAppSetting(
                        ValidAppSettingType.SWITCH,
                        "去广告",
                        "no_ad",
                        false,
                    ),
                    ValidAppSetting(
                        ValidAppSettingType.SWITCH,
                        "去水印",
                        "no_water_mark",
                        false,
                        visible = false
                    ),
                    ValidAppSetting(
                        ValidAppSettingType.SELECT,
                        "视频保存方式",
                        "video_method",
                        "",
                        visible = false
                    ),
                    ValidAppSetting(
                        ValidAppSettingType.INPUT_NUM,
                        "语音时长",
                        "voice_time",
                        0f,
                        range = 0f..Float.MAX_VALUE,
                        visible = false
                    ),
                    ValidAppSetting(
                        ValidAppSettingType.SELECT,
                        "语音路径",
                        "voice_path",
                        "",
                        visible = false
                    ),
                    ValidAppSetting(
                        ValidAppSettingType.SWITCH,
                        "详细ip",
                        "loc_details",
                        false,
                        visible = false
                    ),
                    ValidAppSetting(
                        ValidAppSettingType.INPUT_STR,
                        "地址格式",
                        "loc_format",
                        "",
                        visible = false
                    ),

                    )
            )
        )
    }


    fun validData(packageName: String): ValidApp? {
        return apps.find { it.packageName == packageName }
    }

}