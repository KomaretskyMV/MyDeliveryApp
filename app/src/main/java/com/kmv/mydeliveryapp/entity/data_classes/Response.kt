package com.kmv.mydeliveryapp.entity.data_classes

data class Response(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)