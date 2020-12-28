package com.nminin.corearchcomponents.core.data

interface Specification<T> {
    fun isSatisfiedBy(item: T?): Boolean
}