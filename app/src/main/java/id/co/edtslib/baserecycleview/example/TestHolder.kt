package id.co.edtslib.baserecycleview.example

import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder
import id.co.edtslib.baserecycleview.example.databinding.AdapterTestBinding

class TestHolder(private val binding: AdapterTestBinding): BaseViewHolder<String>(binding) {
    override fun setData(
        list: MutableList<String>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<String>?
    ) {
        binding.textView.text = list[position]
    }
}