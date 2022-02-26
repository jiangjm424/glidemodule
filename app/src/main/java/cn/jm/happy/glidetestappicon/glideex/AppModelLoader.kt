package cn.jm.happy.glidetestappicon.glideex

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream

class AppModelLoader(private val context: Context):ModelLoader<AppModel, Drawable> {
    override fun buildLoadData(
        model: AppModel,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<Drawable>? {
        return ModelLoader.LoadData(ObjectKey(model), AppModelFetcher(context, model))
    }

    override fun handles(model: AppModel): Boolean {
        return true
    }
}