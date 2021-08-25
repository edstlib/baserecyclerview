package id.co.edtslib.baserecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<viewBinding: ViewBinding, T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var delegate: BaseRecyclerViewAdapterDelegate<T>? = null
    var list = mutableListOf<T>()

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

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.itemView.setOnClickListener {
            delegate?.onClick(list[position], position, holder)
        }
        holder.setData(list, position, delegate)
        delegate?.onDraw(list[position], position)
    }

    override fun getItemCount() = list.size
}