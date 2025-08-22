package moe.imoli.ppbuff.hook.zylite

import com.highcapable.kavaref.KavaRef.Companion.resolve
import com.highcapable.kavaref.extension.classOf
import com.highcapable.yukihookapi.hook.log.YLog
import moe.imoli.ppbuff.cache.zyl.ZyliteNoWaterMarkCache
import moe.imoli.ppbuff.hook.BaseHooker
import moe.imoli.ppbuff.hook.ConfigLoader
import moe.imoli.ppbuff.utils.Caches
import org.luckypray.dexkit.wrap.DexClass

object ZyliteNoWaterMarkHooker : BaseHooker("no_water_mark") {


    override fun onHook() {
        val target =
            Caches.read(label, "class", ZyliteNoWaterMarkCache.VIDEO_DOWNLOADER, appContext ?: ConfigLoader.APP)
                .first()
        val clazz = DexClass(target).getInstance(appClassLoader!!)
        clazz.resolve()
            .method {
                parameters { it.size == 2 && it[0] != classOf<String>() }
            }
            .first()
            .hook {
                before {
                    val serverImageBean = args[0]!!
                    // 判断是否是视频
                    val video = serverImageBean.javaClass
                        .resolve()
                        .firstMethod {
                            name = "imageIsVideo"
                        }
                        .of(serverImageBean)
                        .invoke<Boolean>()!!
                    if (video) {
                        // 寻找videoBean
                        val videoBean = serverImageBean.javaClass
                            .resolve()
                            .firstField {
                                name = "videoBean"
                            }
                            .of(serverImageBean)
                            .get()
                        // 寻找h265Sources
                        val h265Sources = videoBean?.javaClass!!
                            .resolve()
                            .firstField {
                                name = "h265Sources"
                            }
                            .of(videoBean)
                            .get() as List<Any>

                        // 寻找sources
                        val sources = videoBean.javaClass
                            .resolve()
                            .firstField {
                                name = "sources"
                            }
                            .of(videoBean)
                            .get() as List<Any>

                        // 合并
                        val playSources = arrayListOf<Any>()
                        playSources.addAll(sources)
                        playSources.addAll(h265Sources)

                        // 获取第一个url
                        val url = if (playSources.isNotEmpty()) {
                            val videoSource = playSources.first()
                            val urls = (videoSource
                                .javaClass
                                .resolve()
                                .firstField {
                                    name = "urls"
                                }
                                .of(videoSource).get() as List<Any>)
                                .first()
                            urls.javaClass
                                .resolve()
                                .firstField {
                                    name = "url"
                                }
                                .of(urls).get() as String
                        } else {
                            videoBean.javaClass
                                .resolve()
                                .firstField {
                                    name = "urlsrc"
                                }
                                .of(videoBean).get() as String
                        }
                        YLog.debug("find video url:$url")
                        // 重设url
                        videoBean.javaClass
                            .resolve()
                            .firstField {
                                name = "urlsrc"
                            }
                            .of(videoBean)
                            .set(url)
                        videoBean.javaClass
                            .resolve()
                            .firstField {
                                name = "urlWithWM"
                            }
                            .of(videoBean)
                            .set(url)

                        videoBean.javaClass
                            .resolve()
                            .firstField {
                                name = "urlWithReview"
                            }
                            .of(videoBean)
                            .set(url)

                    }
                }

            }

    }
}