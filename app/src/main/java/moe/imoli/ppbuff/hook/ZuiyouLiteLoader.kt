package moe.imoli.ppbuff.hook

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.hook.zylite.ZyliteNoAdHooker

object ZuiyouLiteLoader : YukiBaseHooker() {


    override fun onHook() {
        if (processName == packageName){
            ZyliteNoAdHooker.load(this)
        }else{
            YLog.debug("Not target process $processName, skipping...")
        }

    }
}