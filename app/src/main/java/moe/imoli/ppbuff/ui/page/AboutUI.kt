package moe.imoli.ppbuff.ui.page

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

object AboutUI {


    @Composable
    fun view(modifier: Modifier = Modifier, backStack: MutableList<Any> = mutableStateListOf()) {
        Text(text = "Hello World!")
    }


}

@Preview
@Composable
fun Preview_AboutUI() {
    AboutUI.view()
}
