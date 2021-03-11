package com.ronasit.core.extension

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.nminin.bindingbuilder.BindingBuilder
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.recycler.RecyclerAdapterBindingBuilder
import com.nminin.bindingbuilder.recycler.ViewHolder
import com.nminin.bindingbuilder.recycler.ViewHolderFactory
import com.ronasit.core.model.HighlightText
import com.ronasit.core.model.Style
import com.ronasit.core.ui.Fragment
import io.reactivex.rxjava3.core.Observable

fun BindingBuilder<TextView>.highlightsBind(
    data: Observable<HighlightText>,
    style: Observable<Style>,
    customMainColor: String? = null
) = this.apply {

    style.flatMap { style ->
        data.map { highlightText ->
            var text = highlightText.text ?: ""
            highlightText.highlights.forEach {
                text = text.replaceFirst(
                    "%@",
                    "</font><font color=${style.hilightColor}>${it}</font><font color=${customMainColor ?: style.textColor}>"
                )
            }
            "<font color=${customMainColor ?: style.textColor}>${text}</font>"
        }
    }
        .subscribe({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                this.view.setText(
                    Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT),
                    TextView.BufferType.SPANNABLE
                )
            } else {
                this.view.setText(Html.fromHtml(it), TextView.BufferType.SPANNABLE)
            }
        }, {
            it.printStackTrace()
        })
        .dispose(disposable)

}

fun TextView.setHighlightText(
    highlightText: HighlightText,
    style: Style,
    customMainColor: String? = null
) {
    var text = highlightText.text ?: ""
    highlightText.highlights.forEach {
        text = text.replaceFirst(
            "%@",
            "</font><font color=${style.hilightColor}>${it}</font><font color=${customMainColor ?: style.textColor}>"
        )
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.setText(
            Html.fromHtml(
                "<font color=${customMainColor ?: style.textColor}>${text}</font>",
                Html.FROM_HTML_MODE_COMPACT
            ),
            TextView.BufferType.SPANNABLE
        )
    } else {
        this.setText(
            Html.fromHtml("<font color=${customMainColor ?: style.textColor}>${text}</font>"),
            TextView.BufferType.SPANNABLE
        )
    }

}

fun BindingBuilder<TextView>.highlightsBind(
    highlightText: HighlightText,
    style: Observable<Style>,
    customMainColor: String? = null
) = this.apply {
    style.map { style ->
        var text = highlightText.text ?: ""
        highlightText.highlights.forEach {
            text = text.replaceFirst(
                "%@",
                "</font><font color=${style.hilightColor}>${it}</font><font color=${customMainColor ?: style.textColor}>"
            )
        }
        "<font color=${customMainColor ?: style.textColor}>${text}</font>"

    }
        .subscribe({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                this.view.setText(
                    Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT),
                    TextView.BufferType.SPANNABLE
                )
            } else {
                this.view.setText(Html.fromHtml(it), TextView.BufferType.SPANNABLE)
            }
        }, {
            //ignore
        })
        .dispose(disposable)
}

@SuppressLint("RestrictedApi")
fun BindingBuilder<AppCompatButton>.highlightsBind(
    style: Observable<Style>
) = this.apply {

    style.subscribe({
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.view.compoundDrawableTintList = ContextCompat.getColorStateList(
                this.view.context,
                it.buttonTextColor
            )
        }
        this.view.supportBackgroundTintList = ContextCompat.getColorStateList(
            this.view.context,
            it.buttonTintColor
        )
        this.view.setTextColor(
            ContextCompat.getColorStateList(
                this.view.context,
                it.buttonTextColor
            )
        )
        this.view.backgroundTintList
    }, {
        //ignore
    })
        .dispose(disposable)

}

fun <V : TextView> BindingBuilder<V>.highlightsEditTextBind(
    style: Observable<Style>
) = this.apply {
    style.subscribe({
        this.view.setCursorDrawableColor(it.buttonTintColor)
    }, {
        //ignore
    })
        .dispose(disposable)
}

fun <V : TextInputLayout> BindingBuilder<V>.highlightsInputLayoutBind(
    style: Observable<Style>
) = this.apply {
    style.subscribe({
        this.view.hintTextColor = ContextCompat.getColorStateList(
            this.view.context,
            it.buttonTintColor
        )
    }, {
        //ignore
    })
        .dispose(disposable)
}

fun <V : Switch> BindingBuilder<V>.highlightsSwitch(
    style: Observable<Style>
) = this.apply {
    style.subscribe({
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.view.thumbTintList = ContextCompat.getColorStateList(
                this.view.context,
                it.switchTint
            )
            this.view.trackTintList = ContextCompat.getColorStateList(
                this.view.context,
                it.switchTint
            )
        }
    }, {
        //ignore
    })
        .dispose(disposable)
}

fun <T : View> Fragment.bindView(@IdRes id: Int) = this.view!!.findViewById<T>(id)
    .bind(this)

fun <V : RecyclerView,T, VH:ViewHolder<T>, VHF: ViewHolderFactory<T, VH>> Fragment.bindView(
    @IdRes id: Int,
    viewHolderFactory: VHF
) = this.view!!.findViewById<V>(id)
    .bind(this, viewHolderFactory)

fun <T : RecyclerView, R> Fragment.bindView(
    @IdRes id: Int,
    viewHolder: (parent: ViewGroup) -> ViewHolder<R>
): RecyclerAdapterBindingBuilder<R, ViewHolder<R>, ViewHolderFactory<R, ViewHolder<R>>> = this.view!!.findViewById<T>(id)
    .bind(this, viewHolder)

