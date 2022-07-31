package com.techmania.springr.Model

data class PostX(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)