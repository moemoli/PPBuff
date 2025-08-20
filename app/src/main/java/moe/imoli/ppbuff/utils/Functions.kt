package moe.imoli.ppbuff.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class Functions {
}


fun Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
}
