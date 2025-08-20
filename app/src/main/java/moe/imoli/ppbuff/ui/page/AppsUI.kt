package moe.imoli.ppbuff.ui.page

import android.content.pm.ApplicationInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import moe.imoli.ppbuff.R

object AppsUI {


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun view(modifier: Modifier = Modifier, backStack: MutableList<Any> = mutableStateListOf()) {
        val apps = mutableListOf<ApplicationInfo>()
        var state by remember { mutableStateOf(0) }// 改变就更新一次
        var refresh by remember { mutableStateOf(false) }// 刷新状态
        val ctx = LocalContext.current
        // 更新应用列表
        LaunchedEffect(state) {
            apps.clear()
            apps.addAll(ctx.packageManager.getInstalledApplications(0))
            delay(2000)
            refresh = false
        }

        PullToRefreshBox(
            isRefreshing = refresh,
            modifier = modifier.fillMaxSize(),
            onRefresh = {
                state++
                refresh = true
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),

                ) {
                // 空白页面
                item {
                }

                if (apps.isNotEmpty()) {
                    items(apps) { app ->

                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_notfound),
                                contentDescription = null,
                                modifier = Modifier.padding(10.dp)
                                    .size(70.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                            Text(
                                text = "诶? 应用都跑哪去了, 一个都不见了...",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                } else {

                }
            }
        }


    }


}

@Preview(showBackground = true)
@Composable
fun Preview_AppsUI() {
    AppsUI.view()
}
