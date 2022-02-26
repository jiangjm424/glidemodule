package cn.jm.happy.glidetestappicon.ui.home

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager.MATCH_UNINSTALLED_PACKAGES
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.jm.happy.glidetestappicon.glideex.AppModel

class HomeViewModel : ViewModel() {

    private val _appList = MutableLiveData<List<AppModel>>().apply {
        value = emptyList()
    }
    val appList: LiveData<List<AppModel>> = _appList
    fun loadApp(context: Context) {
        val p = context.packageManager.getInstalledPackages(0).map {
            AppModel(it)
        }
        _appList.value = p
    }
}