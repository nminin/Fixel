package com.ronasit.style

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.bindSelectable
import com.ronasit.core.extension.bindView
import com.ronasit.core.extension.highlightsBind
import com.ronasit.core.model.HighlightText
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.OnFragmentBackPressed
import org.koin.androidx.viewmodel.ext.android.viewModel

class StyleFragment() : Fragment(R.layout.fragment_style), OnFragmentBackPressed {
    private val viewModel by viewModel<ChooseStyleViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.text_style)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_style,
                    listOf(R.string.highlight_my)
                ),
                viewModel.getSelectedStyle()
            )

        view.findViewById<ImageView>(R.id.button_back)
            .setOnClickListener {
                onBackPressed()
            }

        bindView<AppCompatButton>(R.id.button_save)
            .highlightsBind(
                viewModel.getSelectedStyle()
            )
            .onClick {
                viewModel.saveChanges()
                onBackPressed()
            }
        view.findViewById<RecyclerView>(R.id.recycler_styles)
            .bindSelectable(this) {
                StyleViewHolder.create(it)
            }
            .observeSelectedItems(viewModel.getSelectedStyle().map {
                listOf(it)
            })
            .observeItems(viewModel.getAllStyles())
            .layoutManager(
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    true
                )
            )
            .onItemClick {
                viewModel.setStyle(it)
            }

    }

    override fun onBackPressed() {
        coordinator.navigateUp()
    }
}