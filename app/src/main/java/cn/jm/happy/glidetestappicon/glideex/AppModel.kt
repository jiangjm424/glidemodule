package cn.jm.happy.glidetestappicon.glideex

import android.content.pm.PackageInfo
import com.bumptech.glide.load.Key
import java.security.MessageDigest

class AppModel(val packageInfo: PackageInfo) : Key {
    private val app_id = packageInfo.packageName + "" + packageInfo.versionName
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(app_id.toByte())
    }

    override fun equals(other: Any?): Boolean {
        if (other is AppModel) {
            return app_id == other.app_id
        }
        return false
    }

    override fun hashCode(): Int {
        return app_id.hashCode()
    }
}