package moe.imoli.ppbuff.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import moe.imoli.ppbuff.ui.page.AboutUI
import moe.imoli.ppbuff.ui.page.MainUI
import moe.imoli.ppbuff.ui.theme.PPBuffTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PPBuffTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                ) { padding ->
                    BoxWithConstraints {
                        if (maxWidth < maxHeight) {
                            // 手机
                            val backStack = remember { mutableStateListOf<Any>(MainUI) }
                            NavDisplay(
                                modifier = Modifier.padding(padding).fillMaxSize(),
                                backStack = backStack,
                                onBack = { backStack.removeLastOrNull() },
                                entryProvider = { key ->
                                    when (key) {
                                        is MainUI -> NavEntry(key) {
                                            MainUI.view(backStack = backStack)
                                        }

                                        is AboutUI -> NavEntry(key) {
                                            AboutUI.view(backStack = backStack)
                                        }

                                        else -> error("Not implemented yet route $key")
                                    }
                                }
                            )
                        } else {
                            // 平板
                            val backStack = remember { mutableStateListOf<Any>(AboutUI) }
                            MainUI.tablet(modifier = Modifier.padding(padding)) {
                                NavDisplay(
                                    modifier = it.fillMaxSize(),
                                    backStack = backStack,
                                    onBack = { backStack.removeLastOrNull() },
                                    entryProvider = { key ->
                                        when (key) {
                                            is AboutUI -> NavEntry(key) {
                                                AboutUI.view(backStack = backStack)
                                            }

                                            else -> error("Not implemented yet route $key")
                                        }
                                    }
                                )
                            }
                        }
                    }


                }
            }
        }
    }
}
