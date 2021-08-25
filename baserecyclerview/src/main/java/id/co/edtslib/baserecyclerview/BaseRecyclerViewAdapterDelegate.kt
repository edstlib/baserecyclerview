package id.co.edtslib.baserecyclerview

interface BaseRecyclerViewAdapterDelegate<T> {
    fun onClick(t: T, position: Int, holder: BaseViewHolder<T>?)
    fun onDraw(t: T, position: Int)
}