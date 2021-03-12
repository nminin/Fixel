package com.ronasit.landing.ui.brands

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.ronasit.core.base.binding.GlideImageDecorator
import com.ronasit.core.extension.bindView
import com.ronasit.core.extension.highlightsBind
import com.ronasit.core.extension.safeMap
import com.ronasit.core.model.Brand
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import com.ronasit.landing.R
import com.ronasit.landing.ui.binding.HighlightButtonDecorator
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrandsLandingFragment : Fragment(R.layout.fragment_landing_brand) {
    private val brandsViewModel by viewModel<BrandsViewModel>()
    private val styleViewModel by viewModel<StyleViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        initContent()
        initHeader()
    }

    private fun initContent() {
        bindView<TextView>(R.id.text_header_content)
            .highlightsBind(
                brandsViewModel.getTitle()
                    .safeMap {
                        it.content
                    },
                styleViewModel.getStyle(),
                "#000000"
            )

        view?.findViewById<RecyclerView>(R.id.recycler_brands)
            ?.bind<RecyclerView, Brand>(viewLifecycleOwner) {
                BrandViewHolder.create(it)
            }
            ?.layoutManager(
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            ?.observeItems(
                brandsViewModel.getBrands()
            )
            ?.onItemClick {
                Toast.makeText(
                    requireContext(),
                    "${it.name}: ${it.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        bindView<AppCompatButton>(R.id.button_all_brands)
            .highlightsBind(styleViewModel.getStyle())
            .observe(brandsViewModel.getLandingHeader().map {
                View.VISIBLE
            }, VisibilityDecorator())
            .observe(brandsViewModel.getButton(), HighlightButtonDecorator())
            .onClick {
                Toast.makeText(
                    requireContext(),
                    "Not implemented yet",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun initHeader() {
        bindView<View>(R.id.layout_brands_header)
            .observe(brandsViewModel.getLandingHeader().map {
                View.VISIBLE
            }, VisibilityDecorator())
        bindView<ImageView>(R.id.image_header)
            .observe(
                brandsViewModel.getLandingHeader()
                    .map {
                        it.backgroundImage
                    },
                GlideImageDecorator()
            )
        bindView<TextView>(R.id.text_title)
            .highlightsBind(
                brandsViewModel.getLandingHeader().safeMap {
                    it.title?.content
                },
                styleViewModel.getStyle()
            )
        bindView<TextView>(R.id.text_subtitle)
            .highlightsBind(
                brandsViewModel.getLandingHeader().safeMap {
                    it.subtitle?.content
                },
                styleViewModel.getStyle()
            )
        bindView<AppCompatButton>(R.id.button_special_products)
            .highlightsBind(styleViewModel.getStyle())
            .observe(brandsViewModel.getLandingHeader().safeMap {
                it.buttonAllProducts
            }, HighlightButtonDecorator())
            .onClick {
                Toast.makeText(
                    requireContext(),
                    "Not implemented yet",
                    Toast.LENGTH_SHORT
                ).show()
            }

        bindView<AppCompatButton>(R.id.button_all_products)
            .observe(brandsViewModel.getLandingHeader().safeMap {
                it.buttonSpecialProducts
            }, HighlightButtonDecorator())
            .onClick {
                Toast.makeText(
                    requireContext(),
                    "Not implemented yet",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}