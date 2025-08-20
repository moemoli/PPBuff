package moe.imoli.ppbuff.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import moe.imoli.ppbuff.R
import moe.imoli.ppbuff.utils.openUrl

object AboutUI {


    @Composable
    fun view(modifier: Modifier = Modifier, backStack: MutableList<Any> = mutableStateListOf()) {
        val ctx = LocalContext.current
        Column(
            modifier = modifier.fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.mipmap.ic_launcher),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Row(
                modifier = Modifier.padding(vertical = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "© 2025 PPBuff",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

            }

            Row(
                modifier = Modifier.padding(bottom = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val source = remember { MutableInteractionSource() }

                Image(
                    painter = painterResource(R.drawable.ic_github),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 5.dp)
                        .size(26.dp)
                        .clickable(
                            indication = null,
                            interactionSource = source,
                            onClick = {
                                ctx.openUrl("https://github.com/moemoli/PPBuff")
                            }),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                )

                Image(
                    painter = painterResource(R.drawable.ic_qq),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 5.dp)
                        .size(26.dp)
                        .clickable(
                            indication = null,
                            interactionSource = source,
                            onClick = {
                                ctx.openUrl("mqqapi://card/show_pslcard?src_type=internal&version=1&card_type=group&uin=1057214685")
                            }),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                )

                Image(
                    painter = painterResource(R.drawable.ic_telegram),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 5.dp)
                        .size(26.dp)
                        .clickable(
                            indication = null,
                            interactionSource = source,
                            onClick = {
                                //ctx.openUrl("https://github.com/moemoli/PPBuff")
                            }),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                )
            }

            Text(
                text = "青山不改，绿水长流",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

        }

    }


}

@Preview(showBackground = true)
@Composable
fun Preview_AboutUI() {
    AboutUI.view()
}
