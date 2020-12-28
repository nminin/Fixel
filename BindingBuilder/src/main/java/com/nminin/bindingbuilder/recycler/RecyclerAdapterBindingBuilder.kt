package com.nminin.bindingbuilder.recycler

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.BaseRecyclerBindingBuilder

class RecyclerAdapterBindingBuilder<T,V: ViewHolder<T>>(
    private val view: RecyclerView,
    lifecycleOwner: LifecycleOwner,
    private val viewHolder: ViewHolderFactory<T,V>
): BaseRecyclerBindingBuilder<T,V, RecyclerAdapter<T,V>>(view, lifecycleOwner) {
    override var adapter: RecyclerAdapter<T, V> = RecyclerAdapter(viewHolder)
}