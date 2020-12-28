package com.ronasit.core.extension

import android.os.Build
import android.text.Html
import android.widget.TextView
import com.nminin.bindingbuilder.BindingBuilder
import com.ronasit.core.model.HighlightText
import io.reactivex.rxjava3.core.Observable

fun BindingBuilder<TextView>.highlightsBind(
    data: Observable<HighlightText>,
    style: Observable<String>
) = this.apply {

    style.flatMap { color ->
        data.map { highlightText ->
            var text = highlightText.text ?: ""
            highlightText.highlights.forEach {
                text = text.replaceFirst("%@", "<font color=${color}>${it}</font>")
            }
            text
        }
    }
        .subscribe({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                this.view.setText(Html.fromHtml(it,  Html.FROM_HTML_MODE_COMPACT), TextView.BufferType.SPANNABLE)
            } else {
                this.view.setText(Html.fromHtml(it), TextView.BufferType.SPANNABLE)
            }
        }, {
            //ignore
        })

}