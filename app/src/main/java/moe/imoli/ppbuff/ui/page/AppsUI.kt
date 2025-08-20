package moe.imoli.ppbuff.ui.page

import android.content.pm.ApplicationInfo
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import moe.imoli.ppbuff.R
import androidx.core.graphics.createBitmap
import moe.imoli.ppbuff.app.data.ValidApps

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
                if (apps.isNotEmpty()) {
                    items(apps) { app ->
                        val icon = app.loadIcon(ctx.packageManager)
                        val data = ValidApps.validData(app.packageName)
                        Row(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(start = 25.dp)
                                .clickable {
                                    if (data != null) {
                                        backStack.add(AppSettingUI(data))
                                    }
                                }
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Image(
                                bitmap = createBitmap(icon.intrinsicWidth, icon.intrinsicHeight)
                                    .apply {
                                        val canvas = Canvas(this)
                                        icon.setBounds(0, 0, canvas.width, canvas.height)
                                        icon.draw(canvas)
                                    }
                                    .asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                                    .clip(shape = RoundedCornerShape(5.dp)),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(),
                            ) {

                                Text(
                                    text = app.name,

                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = if (data != null) "${data.maxVersion} - ${data.maxVersion}" else "暂未适配",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }

                        }
                    }

                } else {
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
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
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
