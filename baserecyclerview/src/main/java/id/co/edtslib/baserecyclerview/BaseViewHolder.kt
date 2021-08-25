package id.co.edtslib.baserecyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(viewBinding : ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    abstract fun setData(list: MutableList<T>, position: Int, delegate: BaseRecyclerViewAdapterDelegate<T>?)
}