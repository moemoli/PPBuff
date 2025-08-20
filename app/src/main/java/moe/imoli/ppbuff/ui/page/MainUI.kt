package moe.imoli.ppbuff.ui.page

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.highcapable.yukihookapi.YukiHookAPI
import moe.imoli.ppbuff.R
import moe.imoli.ppbuff.utils.openUrl

object MainUI {

    private val size = 13.dp
    private val imgSize = 24.dp
    private val paddingHorizontal = 20.dp
    private val paddingVertically = 12.dp
    private val imgPadding = 25.dp
    private val textPaddingImg = 20.dp
    private val handler = Handler(Looper.getMainLooper())


    @Composable
    fun view(modifier: Modifier = Modifier, backStack: MutableList<Any> = mutableListOf()) {
        Column(
            modifier = modifier
                .fillMaxSize()// 填充屏幕
        ) {
            // 跳转选择
            statusBoard(
                status = YukiHookAPI.Status.isModuleActive,
                name = YukiHookAPI.Status.Executor.name
            )


            clickBoard(
                text = "支持的应用",
                modifier = Modifier.padding(top = 10.dp),
                bottom = false
            ) {


                if (backStack.find { it is AppsUI } != null) {
                    while (backStack.last() !is AppsUI) {
                        backStack.removeLastOrNull()
                    }
                } else
                    backStack.add(AppsUI)
            }
            clickBoard(
                text = "设置",
                top = false,
                bottom = false
            ) {
                //backStack.add(AboutUI)
            }
            clickBoard(
                text = "关于",
                top = false
            ) {
                if (backStack.find { it is AboutUI } != null) {
                    while (backStack.last() !is AboutUI) {
                        backStack.removeLastOrNull()
                    }
                } else
                    backStack.add(AboutUI)
            }

            sponsor(modifier = Modifier.padding(top = 20.dp))
        }
    }

    @Composable
    fun tablet(
        modifier: Modifier = Modifier,
        backStack: MutableList<Any> = mutableListOf(),
        compose: @Composable (modifier: Modifier) -> Unit
    ) {
        // 左边状态，右边应用选择
        Row(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = 35.dp,
                        start = 30.dp,
                        end = 30.dp
                    )
                    .fillMaxHeight()// 填充屏幕
                    .width(400.dp)

            ) {
                view(modifier = Modifier.padding(10.dp), backStack = backStack)
            }

            Column(
                modifier = Modifier
                    .padding(
                        bottom = 20.dp,
                        top = 20.dp,
                        end = 30.dp
                    )
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(size),
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(size)
                    )
                    .fillMaxSize(),// 填充屏幕
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedVisibility(
                    visible = backStack.size > 1
                ) {
                    // 返回导航
                    Row(
                        modifier = Modifier.padding(top = 15.dp)
                            .padding(horizontal = 15.dp)
                            .height(24.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow),
                            contentDescription = null,
                            modifier = Modifier.size(imgSize)
                                .rotate(180f)
                                .clickable(
                                    indication = null,
                                    onClick = {
                                        backStack.removeLastOrNull()
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                )
                        )
                    }
                }


                compose(
                    Modifier.padding(vertical = 15.dp)
                        .fillMaxSize()
                )
            }
        }

    }

    @Composable
    fun sponsor(modifier: Modifier = Modifier) {
        val ctx = LocalContext.current
        Column(
            modifier = modifier.padding(horizontal = paddingHorizontal)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(size),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(
                        vertical = paddingVertically,
                        horizontal = paddingHorizontal
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_sponsor),
                    contentDescription = null,
                    modifier = Modifier.size(imgSize),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                )

                Text(
                    text = "赞助 - 开发不易，您的赞助就是我的动力",
                    modifier = Modifier.padding(start = textPaddingImg),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        vertical = paddingVertically,
                        horizontal = paddingHorizontal
                    )
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()

            ) {
                val modifier = Modifier.weight(1f)
                // alipay
                Image(
                    painter = painterResource(R.drawable.ic_alipay),
                    modifier = modifier.size(40.dp).clickable {
                        ctx.openUrl("https://qr.alipay.com/fkx14993unapme7maimi245")
                    },
                    contentDescription = null
                )
                // wechat
                Image(
                    painter = painterResource(R.drawable.ic_wechat),
                    modifier = modifier.size(40.dp)
                        .clickable {
                            ctx.openUrl("wxp://f2f0SJzwy0tIWH-Dt0rmuMkpRqDpFxt9D_hrlsKW7yF-z-h-JtzNRaFjv1g_1BLaQYe4")
                        },
                    contentDescription = null
                )
                // paypal
                Image(
                    painter = painterResource(R.drawable.ic_paypal),
                    modifier = modifier.size(40.dp).clickable {
                        Toast.makeText(ctx,"暂不支持",Toast.LENGTH_LONG).show()
                    },
                    contentDescription = null
                )
            }
        }
    }


    @Composable
    fun clickBoard(
        text: String,
        modifier: Modifier = Modifier,
        top: Boolean = true,
        bottom: Boolean = true,
        onClick: () -> Unit
    ) {
        var modifier = modifier
        if (!top) {
            modifier = modifier.padding(top = 1.dp)
        }
        Row(
            modifier = modifier
                .padding(horizontal = paddingHorizontal)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 20.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape =
                        (if (top && bottom) RoundedCornerShape(size = size)
                        else if (top) RoundedCornerShape(
                            topStart = size, topEnd = size,
                            bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                        else if (bottom) RoundedCornerShape(
                            topStart = 0.dp, topEnd = 0.dp,
                            bottomStart = size, bottomEnd = size
                        )
                        else RoundedCornerShape(size = 0.dp))
                )
                .clickable(onClick = onClick),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp, vertical = paddingVertically)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = text,
                    modifier = Modifier.padding(start = 25.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.padding(end = imgPadding)
                            .size(imgSize),
                        painter = painterResource(R.drawable.ic_arrow),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                    )
                }
            }

        }
    }

    @Composable
    fun statusBoard(status: Boolean = false, name: String = "Xposed") {
        Column(
            modifier = Modifier
                .fillMaxWidth()// 填充屏幕
                .defaultMinSize(minHeight = 40.dp, minWidth = 200.dp)
                .padding(top = 20.dp)
                .height(100.dp),
            verticalArrangement = Arrangement.Center,// 居中
            horizontalAlignment = Alignment.CenterHorizontally,// 居中
        ) {
            // 内部框
            Row(
                verticalAlignment = Alignment.CenterVertically,// 居中
                horizontalArrangement = Arrangement.Center,// 居中
                modifier = Modifier
                    .padding(horizontal = paddingHorizontal, vertical = 10.dp)
                    .fillMaxSize()
                    .background(
                        shape = RoundedCornerShape(size = size),
                        color = MaterialTheme.colorScheme.primary
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,// 居中
                    horizontalArrangement = Arrangement.Start,// 居中
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .fillMaxSize()

                ) {
                    Image(
                        painter = if (status) painterResource(caseIcon(name))
                        else painterResource(R.drawable.ic_close),
                        modifier = Modifier.padding(start = 25.dp)
                            .size(imgSize),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                    )
                    Column(modifier = Modifier.padding(start = textPaddingImg)) {
                        Text(
                            text = if (status) stringResource(R.string.activated)
                            else stringResource(R.string.noactive),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        if (status) {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                }

            }
        }
    }

    @DrawableRes
    fun caseIcon(name: String): Int {
        return when (name.lowercase()) {
            "lsposed" -> R.drawable.ic_lsposed
            else -> R.drawable.ic_unknown
        }
    }


}

@Preview(showBackground = true)
@Composable
fun Preview_MainUI() {

}
