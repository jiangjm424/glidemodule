package cn.jm.happy.glidetestappicon.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import cn.jm.happy.glidetestappicon.databinding.AppItemBinding
import cn.jm.happy.glidetestappicon.databinding.FragmentHomeBinding
import cn.jm.happy.glidetestappicon.glideex.AppModel
import cn.jm.happy.glidetestappicon.glideex.GlideApp
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: MAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        adapter = MAdapter()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rv = binding.list
        rv.adapter = adapter
        homeViewModel.loadApp(requireContext())
        homeViewModel.appList.observe(viewLifecycleOwner) {
            adapter.sump(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class VH(val binding: AppItemBinding) : RecyclerView.ViewHolder(binding.root)
class MAdapter : RecyclerView.Adapter<VH>() {
    private val appList = mutableListOf<AppModel>()
    fun sump(data: List<AppModel>) {
        appList.clear()
        appList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AppItemBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        GlideApp.with(holder.binding.icon.context).load(appList[position]).centerCrop().into(holder.binding.icon)

        holder.binding.name.text = appList[position].packageInfo.packageName
        val r = if (position % 2 == 0) 15f else -15f
        val c = if (position % 2 == 0) Color.MAGENTA else Color.BLUE
        holder.binding.root.animate().rotation(r).setDuration(300).start()
        holder.binding.root.setBackgroundColor(c)
    }

    override fun getItemCount(): Int {
        return appList.size
    }
}