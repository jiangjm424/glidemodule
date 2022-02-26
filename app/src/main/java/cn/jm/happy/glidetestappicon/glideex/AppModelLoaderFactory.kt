package cn.jm.happy.glidetestappicon.glideex

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class AppModelLoaderFactory(private val context: Context):ModelLoaderFactory<AppModel, Drawable> {
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<AppModel, Drawable> {
        return AppModelLoader(context)
    }

    override fun teardown() {

    }
}