package moe.imoli.ppbuff.app

import android.app.Application
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import moe.imoli.ppbuff.app.data.ValidApps

class Buff : ModuleApplication() {

    companion object {
        lateinit var APP: Application
    }

    init {
        APP = this
    }

    override fun onCreate() {
        ValidApps.init(prefs = prefs())
        super.onCreate()
    }
}