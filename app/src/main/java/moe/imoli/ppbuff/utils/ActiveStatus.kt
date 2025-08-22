package moe.imoli.ppbuff.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo

object ActiveStatus {

    private val intents = mutableListOf(
        Intent().apply { setClassName("org.lsposed.npatch", "org.lsposed.lspatch.manager.ModuleService") },
        Intent().apply { setClassName("org.lsposed.lspatch", "org.lsposed.lspatch.manager.ModuleService") },
        )

    fun queryPatchers(ctx: Context): MutableList<ResolveInfo> {
        val list = mutableListOf<ResolveInfo>()
        intents.forEach {
            list.addAll(
                ctx.packageManager
                    .queryIntentServices(it, PackageManager.MATCH_ALL)
            )
        }
        return list
    }
}