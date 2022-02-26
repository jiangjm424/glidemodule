package cn.jm.happy.glidetestappicon.glideex

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher

class AppModelFetcher(private val context: Context, private val source: AppModel) :
    DataFetcher<Drawable> {
    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in Drawable>) {
        val d = context.packageManager.getApplicationIcon(source.packageInfo.applicationInfo)
        Log.i("jianggg", "app:${source.packageInfo.packageName} icon:$d")
        callback.onDataReady(d)
    }

    override fun cleanup() {
    }

    override fun cancel() {
    }

    override fun getDataClass(): Class<Drawable> {
        return Drawable::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }
}