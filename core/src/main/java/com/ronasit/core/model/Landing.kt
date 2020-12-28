package com.ronasit.core.model

class Landing(
    val headerImage: String? = null,
    val textHeader: HighlightText? = null,
    val textMain: HighlightText? = null,
    val textPromo: HighlightText? = null,
    val textBottom: HighlightText? = null,
    val buttonVisibility: Boolean = false,
    val buttonText: String = "",
    val categoriesHeader: HighlightText? = null,
    val categories: List<Category> = emptyList()
)