package com.ronasit.core.repository

import com.nminin.corearchcomponents.core.data.repository.RepositoryGet
import com.nminin.corearchcomponents.core.data.repository.RepositoryGetAll
import com.ronasit.core.base.repository.RepositorySet
import com.ronasit.core.model.Style

interface StyleRepository : RepositoryGet<Style>, RepositoryGetAll<List<Style>>, RepositorySet<Style>