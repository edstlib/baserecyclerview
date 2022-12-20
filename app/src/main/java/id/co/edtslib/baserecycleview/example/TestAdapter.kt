package id.co.edtslib.baserecycleview.example

import android.view.LayoutInflater
import android.view.ViewGroup
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter
import id.co.edtslib.baserecycleview.example.databinding.AdapterTestBinding

class TestAdapter: BaseRecyclerViewAdapter<AdapterTestBinding, String>() {
    override fun isCircular() = true

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterTestBinding
        get() = AdapterTestBinding::inflate

    override fun createHolder() = TestHolder(binding)
}