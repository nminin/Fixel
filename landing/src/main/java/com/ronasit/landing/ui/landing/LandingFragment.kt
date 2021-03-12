package com.ronasit.landing.ui.landing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.ButtonTextDecorator
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.ronasit.core.base.binding.GlideImageDecorator
import com.ronasit.core.extension.bindView
import com.ronasit.core.extension.highlightsBind
import com.ronasit.core.extension.safeMap
import com.ronasit.core.extension.toVisibility
import com.ronasit.core.model.Category
import com.ronasit.core.ui.CustomDialogHost
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import com.ronasit.landing.R
import com.ronasit.landing.databinding.LayoutContentBinding
import com.ronasit.landing.databinding.LayoutHeaderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingFragment : Fragment(R.layout.fragment_landing) {
    private val landingViewModel by viewModel<LandingViewModel>()
    private val styleViewModel by viewModel<StyleViewModel>()
    override fun initView(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initHeader(view)
        initContent(view)
    }

    private fun initContent(view: View) {
        with(LayoutContentBinding.bind(view.findViewById(R.id.layout_content))) {
            this.root.bind(viewLifecycleOwner)
                .observe(
                    landingViewModel.getLandingModel()
                        .map {
                            (!it.categories.isNullOrEmpty()).toVisibility()
                        }, VisibilityDecorator()
                )

            this.textHeader.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().safeMap {
                        it.categoriesHeader
                    },
                    styleViewModel.getStyle(),
                    "#000000"
                )

            this.recyclerCategories.bind<RecyclerView, Category>(viewLifecycleOwner) {
                CategoryViewHolder.create(it)
            }
                .layoutManager(
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                )
                .observeItems(
                    landingViewModel.getLandingModel().map {
                        it.categories
                    }
                )
                .onItemClick {
                    Toast.makeText(
                        requireContext(),
                        "${it.name}: ${it.genderId}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun initHeader(view: View) {
        with(LayoutHeaderBinding.bind(view.findViewById(R.id.layout_header))) {
            this.root.bind(viewLifecycleOwner)
                .observe(
                    landingViewModel.getLandingModel()
                        .map {
                            (it.textHeader != null).toVisibility()
                        }, VisibilityDecorator()
                )
            this.imageHeader.bind(viewLifecycleOwner)
                .observe(
                    landingViewModel.getLandingModel()
                        .map {
                            it.headerImage
                        },
                    GlideImageDecorator()
                )
            this.textHeader.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().safeMap {
                        it.textHeader
                    },
                    styleViewModel.getStyle()
                )
            this.textMain.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().safeMap {
                        it.textMain
                    },
                    styleViewModel.getStyle()
                )
            this.textPromo.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().safeMap {
                        it.textPromo
                    },
                    styleViewModel.getStyle()
                )
            this.textBottom.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().safeMap {
                        it.textBottom
                    },
                    styleViewModel.getStyle()
                )

            this.imageInfo.setOnClickListener {
                showInfoDialog()
            }
        }
        bindView<AppCompatButton>(R.id.button_header)
            .highlightsBind(styleViewModel.getStyle())
            .observe(landingViewModel.getLandingModel().map {
                it.buttonText
            }, ButtonTextDecorator())
            .observe(landingViewModel.getLandingModel().map {
                it.buttonVisibility.toVisibility(View.GONE)
            }, VisibilityDecorator())
    }

    private fun openLink(link: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(link)
            )
        )
    }

    private fun showInfoDialog() {
        (activity as CustomDialogHost).showDialog(InfoDialogFragment())
    }
}