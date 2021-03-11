package com.ronasit.menu.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.ronasit.core.extension.bindView
import com.ronasit.core.extension.highlightsBind
import com.ronasit.core.extension.toVisibility
import com.ronasit.core.model.Category
import com.ronasit.core.model.HighlightText
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import com.ronasit.menu.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel by viewModel<MenuViewModel>()

    private val styleViewModel by viewModel<StyleViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        baseObserve(viewModel)
        view.findViewById<RecyclerView>(R.id.recycler_categories)
            .bind<RecyclerView, Category>(this) {
                CategoryViewHolder.create(it)
            }
            .layoutManager(
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            )
            .observeItems(viewModel.getCategories())
            .onItemClick {
                notImplementedYet()
            }

        view.findViewById<TextView>(R.id.text_style)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_style,
                    listOf(R.string.highlight_fixel)
                ),
                styleViewModel.getStyle()
            )
            .onClick {
                coordinator.toStyle()
            }

        view.findViewById<TextView>(R.id.text_personalise)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_personalise,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle()
            )
            .observe(viewModel.isUserLoggedIn().map {
                (!it).toVisibility()
            }, VisibilityDecorator())
            .onClick {
                notImplementedYet()
            }
        bindView<View>(R.id.divider_personalise)
            .observe(viewModel.isUserLoggedIn().map {
                (!it).toVisibility()
            }, VisibilityDecorator())

        view.findViewById<TextView>(R.id.text_account)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_account,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle()
            )
            .observe(viewModel.isUserLoggedIn().map {
                it.toVisibility()
            }, VisibilityDecorator())
            .onClick {
                notImplementedYet()
            }

        view.findViewById<TextView>(R.id.text_track)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_order,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle()
            )
            .observe(viewModel.isUserLoggedIn().map {
                it.toVisibility()
            }, VisibilityDecorator())
            .onClick {
                notImplementedYet()
            }
        bindView<View>(R.id.divider_track)
            .observe(viewModel.isUserLoggedIn().map {
                it.toVisibility()
            }, VisibilityDecorator())
        bindView<View>(R.id.divider_account)
            .observe(viewModel.isUserLoggedIn().map {
                it.toVisibility()
            }, VisibilityDecorator())

        view.findViewById<TextView>(R.id.text_invite)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_invite,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle()
            )
            .onClick {
                notImplementedYet()
            }
        view.findViewById<TextView>(R.id.text_help)
            .bind(this)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_help,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle()
            )
            .onClick {
                notImplementedYet()
            }

        bindView<Button>(R.id.button_logout)
            .observe(
                viewModel.getUser()
                    .map {
                        (it.value != null).toVisibility()
                    },
                VisibilityDecorator()
            )
            .onClick {
                viewModel.logout()
            }
        bindView<View>(R.id.layout_authorization)
            .observe(
                viewModel.getUser()
                    .map {
                        (it.value == null).toVisibility()
                    },
                VisibilityDecorator()
            )


        bindView<AppCompatButton>(R.id.button_log_in)
            .highlightsBind(
                styleViewModel.getStyle()
            )
            .onClick {
                coordinator.toLogIn()
            }

        view.findViewById<Button>(R.id.button_sign_up)
            .setOnClickListener {
                coordinator.toSignUp()
            }
    }

    private fun notImplementedYet() {
        Toast.makeText(
            requireContext(),
            "Not implemented yet",
            Toast.LENGTH_SHORT
        ).show()
    }
}