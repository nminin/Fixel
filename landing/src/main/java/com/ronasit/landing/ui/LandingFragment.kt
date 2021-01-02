package com.ronasit.landing.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.ronasit.core.base.binding.GlideImageDecorator
import com.ronasit.core.extension.highlightsBind
import com.ronasit.core.extension.toVisibility
import com.ronasit.core.ui.CustomDialogHost
import com.ronasit.core.ui.Fragment
import com.ronasit.landing.R
import com.ronasit.landing.databinding.LayoutContentBinding
import com.ronasit.landing.databinding.LayoutFooterBinding
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
        initFooter(view)
    }

    private fun initFooter(view: View) {
        with(LayoutFooterBinding.bind(view.findViewById(R.id.layout_footer))) {
            this.imageFb.setOnClickListener {
                openLink(getString(R.string.facebook_link))
            }
            this.imageInst.setOnClickListener {
                openLink(getString(R.string.instagram_link))
            }
            this.imageTwitter.setOnClickListener {
                openLink(getString(R.string.twitter_link))
            }
            this.imagePinterest.setOnClickListener {
                openLink(getString(R.string.tiktok_link))
            }
        }
    }

    private fun initContent(view: View) {
        with(LayoutContentBinding.bind(view.findViewById(R.id.layout_content))) {
            this.root.bind(viewLifecycleOwner)
                .observe(landingViewModel.getLandingModel()
                    .map {
                        (!it.categories.isNullOrEmpty()).toVisibility()
                    }, VisibilityDecorator()
                )
            this.textHeader.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().map {
                        it.categoriesHeader
                    },
                    styleViewModel.getColor()
                )

            this.recyclerCategories.bind(viewLifecycleOwner) {
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
                .observe(landingViewModel.getLandingModel()
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
                    landingViewModel.getLandingModel().map {
                        it.textHeader
                    },
                    styleViewModel.getColor()
                )
            this.textMain.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().map {
                        it.textMain
                    },
                    styleViewModel.getColor()
                )
            this.textPromo.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().map {
                        it.textPromo
                    },
                    styleViewModel.getColor()
                )
            this.textBottom.bind(viewLifecycleOwner)
                .highlightsBind(
                    landingViewModel.getLandingModel().map {
                        it.textBottom
                    },
                    styleViewModel.getColor()
                )

            this.imageInfo.setOnClickListener {
                showInfoDialog()
            }

            landingViewModel.getLandingModel()
                .subscribe({
                    this.buttonHeader.visibility = if (it.buttonVisibility) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                    this.textButton.text = it.buttonText
                }, {
                    //ignore
                })

        }
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