package com.ronasit.core.extension

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.nminin.bindingbuilder.BindingBuilder
import com.nminin.bindingbuilder.bind
import com.ronasit.core.BuildConfig
import com.ronasit.core.model.HighlightText
import com.ronasit.core.model.Style
import com.ronasit.core.ui.Fragment
import io.reactivex.rxjava3.core.Observable
import java.time.format.TextStyle

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

fun BindingBuilder<Button>.highlightsBind(
    style: Observable<Style>
) = this.apply {

    style.subscribe({
        this.view.backgroundTintList = ContextCompat.getColorStateList(
            this.view.context,
            it.buttonBackgroundColor
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.view.compoundDrawableTintList = ContextCompat.getColorStateList(
                this.view.context,
                it.buttonTextColor
            )
        }
        this.view.setTextColor(
            ContextCompat.getColorStateList(
                this.view.context,
                it.buttonTextColor
            )
        )
    }, {
        //ignore
    })
        .dispose(disposable)

}

fun <V: TextView>BindingBuilder<V>.highlightsEditTextBind(
    style: Observable<Style>
) = this.apply {
    style.subscribe({
        this.view.setCursorDrawableColor(it.buttonBackgroundColor)
    }, {
        //ignore
    })
        .dispose(disposable)
}

fun <V: TextInputLayout>BindingBuilder<V>.highlightsInputLayoutBind(
    style: Observable<Style>
) = this.apply {
    style.subscribe({
        this.view.hintTextColor = ContextCompat.getColorStateList(
            this.view.context,
            it.buttonBackgroundColor
        )
    }, {
        //ignore
    })
        .dispose(disposable)
}

fun <V: Switch>BindingBuilder<V>.highlightsSwitch(
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

fun <T: View>Fragment.bindView(@IdRes id: Int) = this.view!!.findViewById<T>(id)
    .bind(this)