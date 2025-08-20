package moe.imoli.ppbuff.ui.page

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import moe.imoli.ppbuff.R
import moe.imoli.ppbuff.app.data.ValidApp
import moe.imoli.ppbuff.app.data.ValidAppSettingType.*
import moe.imoli.ppbuff.app.data.ValidApps

class AppSettingUI(val data: ValidApp) {


    @Composable
    fun view(modifier: Modifier = Modifier, backStack: MutableList<Any> = mutableStateListOf()) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .height(50.dp),
        ) {
            items(data.settings) { setting ->
                if (setting.visible)
                    when (setting.type) {
                        SWITCH -> {
                            switch(setting.name, setting.value as Boolean) { value ->
                                setting.save(value)
                            }
                        }

                        SELECT -> {
                            select(setting.name, setting.value, mutableListOf("A", "B", "C")) {
                                setting.clickable()
                            }
                        }

                        INPUT_NUM -> {
                            silde(setting.name, setting.value as Float, setting.range) { value ->
                                setting.save(value)
                            }
                        }

                        INPUT_STR -> {
                            input(setting.name, "abc" as String) { value ->
                                setting.save(value)
                            }
                        }
                    }
            }
        }

    }

    @Composable
    fun switch(name: String, value: Boolean, onChange: (Boolean) -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val modifier = Modifier.weight(1f)
            Text(
                text = name,
                modifier = modifier,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(value, onChange)
            }

        }
    }

    @Composable
    fun select(name: String, value: Any, select: MutableList<String>, onClick: (select: String) -> Unit) {
        var open by remember { mutableStateOf(false) }
        val animatedRotate by animateFloatAsState(
            targetValue = if (open) 0f else 90f,
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val modifier = Modifier.weight(1f)
            Text(
                text = name,
                modifier = modifier,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                modifier = modifier.fillMaxWidth()
                    .clickable {
                        if (select.isNotEmpty()) {
                            open = !open
                        } else {
                            onClick("")
                        }
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$value",
                    modifier = Modifier.padding(end = 5.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                if (select.isNotEmpty()) {
                    DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                        select.forEach {
                            DropdownMenuItem(text = {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }, onClick = {
                                onClick(it)
                            })
                        }
                    }
                }

                Image(
                    modifier = Modifier.size(24.dp)
                        .rotate(animatedRotate),
                    painter = painterResource(R.drawable.ic_arrow),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                )
            }

        }
    }

    @Composable
    fun silde(name: String, value: Float, range: ClosedFloatingPointRange<Float>, onChange: (Float) -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var target by remember { mutableFloatStateOf(value) }
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = "$target",
                    modifier = Modifier.padding(start = 30.dp)
                        .width(40.dp)
                        .height(20.dp),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = { value ->
                        if (value.isDigitsOnly() && value.toFloat() in range) {
                            target = value.toFloat()
                            onChange(target)
                        }
                    })
                Slider(
                    value = target,
                    modifier = Modifier.height(30.dp),
                    valueRange = range,
                    onValueChange = { value ->
                        target = value
                        onChange(target)
                    })
            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun input(name: String, value: String, onChange: (String) -> Unit) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var text by remember { mutableStateOf(value) }
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = text,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 30.dp)
                        .height(30.dp),
                    decorationBox = {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                    shape = RoundedCornerShape(3.dp)
                                )
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 5.dp)
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                it()
                            }
                        }

                    },
                    onValueChange = { value ->
                        text = value
                        onChange(text)
                    }
                )
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun Preview_AppSettingUI() {
    AppSettingUI(ValidApps.apps[0]).view()
}
