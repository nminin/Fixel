package com.nminin.bindingbuilder

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.recycler.SelectableRecyclerAdapter
import com.nminin.bindingbuilder.recycler.SelectableViewHolder
import com.nminin.bindingbuilder.recycler.ViewHolderFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

class SelectedRecyclerBindingBuilder<T, V : SelectableViewHolder<T>>(
    private val view: RecyclerView,
    lifecycleOwner: LifecycleOwner,
    private val viewHolder: ViewHolderFactory<T, V>
) : BaseRecyclerBindingBuilder<T, V, SelectableRecyclerAdapter<T, V>>(
    view,
    lifecycleOwner
) {

    override var adapter: SelectableRecyclerAdapter<T, V> = SelectableRecyclerAdapter(viewHolder)

    fun observeSelectedItems(data: Observable<List<T>>) = this.apply {
        data.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                safeAdapter().setSelectedItems(it)
            }, {
                //ignore
            })

    }


}