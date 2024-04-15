package id.co.edtslib.baserecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<viewBinding: ViewBinding, T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var delegate: BaseRecyclerViewAdapterDelegate<T>? = null
    var list = mutableListOf<T>()

    open var isHeaderView: Boolean = false
    open fun clickable() = true

    protected open fun isCircular() = false

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> viewBinding

    @Suppress("UNCHECKED_CAST")
    protected val binding: viewBinding
        get() = _binding as viewBinding

    abstract fun createHolder(): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        _binding = bindingInflater.invoke(inflater, parent, false)

        return createHolder()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val newPosition = if (isCircular() && list.size > 1) {
            position % list.size
        }
        else {
            position
        }

        if (newPosition in 0 until list.size) {
            if (clickable()) {
                holder.itemView.setOnClickListener {
                    if (newPosition in 0 until list.size) {
                        delegate?.onClick(list[newPosition], newPosition, holder)
                    }
                    else {
                        notifyDataSetChanged()
                    }
                }
            }
            holder.setData(list, newPosition, delegate)
            delegate?.onDraw(list[newPosition], newPosition)
        }
    }

    override fun getItemCount() =
        if (isCircular() && list.size > 1) {
            Int.MAX_VALUE
        }
        else
        if (isHeaderView) {
            1
        } else {
            list.size
        }

    fun getInitialPosition(): Int {
        return if (isCircular() && list.size > 1) {
            itemCount / 2 - (itemCount/2) % list.size
        } else {
            0
        }
    }
}