package com.nminin.bindingbuilder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nminin.bindingbuilder.recycler.RecyclerAdapterBindingBuilder
import com.nminin.bindingbuilder.recycler.SelectableViewHolder
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.nminin.bindingbuilder.recycler.ViewHolderFactory
import java.util.function.BiPredicate

fun <V : View> V.bind(lifecycleOwner: LifecycleOwner) = BindingBuilder(this, lifecycleOwner)

fun <V : RecyclerView, T> V.bind(
    lifecycleOwner: LifecycleOwner,
    viewHolder: (parent: ViewGroup) -> ViewHolder<T>
): RecyclerAdapterBindingBuilder<T, ViewHolder<T>, ViewHolderFactory<T, ViewHolder<T>>> =
    RecyclerAdapterBindingBuilder(
        this, lifecycleOwner, object : ViewHolderFactory<T, ViewHolder<T>>() {
            override fun getViewType(item: T): Int = 1

            override fun getViewHolder(viewType: Int, viewGroup: ViewGroup): ViewHolder<T> {
                return viewHolder.invoke(viewGroup)
            }
        }
    )

fun <V : RecyclerView, T,VH:ViewHolder<T>,  VHF : ViewHolderFactory<T, VH>> V.bind(
    lifecycleOwner: LifecycleOwner,
    viewHolderFactory: VHF
) = RecyclerAdapterBindingBuilder(
    this, lifecycleOwner, viewHolderFactory
)

fun <V : RecyclerView, T> V.bindSelectable(
    lifecycleOwner: LifecycleOwner,
    viewHolder: (parent: ViewGroup) -> SelectableViewHolder<T>
) = SelectedRecyclerBindingBuilder(
    this, lifecycleOwner, object : ViewHolderFactory<T, SelectableViewHolder<T>>() {
        override fun getViewType(item: T): Int = 1

        override fun getViewHolder(viewType: Int, viewGroup: ViewGroup): SelectableViewHolder<T> {
            return viewHolder.invoke(viewGroup)
        }
    }
)

fun BindingBuilder<EditText>.onTextChanged(textListener: (String) -> Unit) = this.apply {
    this.view.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //ignore
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //ignore
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textListener.invoke(s?.toString() ?: "")
        }

    })
}

fun <V : CompoundButton> BindingBuilder<V>.onChecked(predicate: (isChecked: Boolean) -> Unit) =
    this.apply {
        view.setOnCheckedChangeListener { buttonView, isChecked ->
            predicate.invoke(isChecked)
        }
    }

fun BindingBuilder<SwipeRefreshLayout>.onRefresh(action: () -> Unit) {
    view.setOnRefreshListener {
        action.invoke()
    }
}