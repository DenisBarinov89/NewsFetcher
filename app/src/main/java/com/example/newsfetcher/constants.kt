package com.example.newsfetcher

const val API_KEY = "d39922992381461ab2f5334560e58eca"

//Параметры сортировок
const val SORT_POPULARITY = "По популярности"
const val SORT_DATE_ASCENDING = "По дате"
const val SORT_TITLE_ASCENDING = "По алфавиту"
const val BUNDLE_KEY_SORT_FOR_FILTER_FRAGMENT = "Sort"

//Параметры сортировок для подставновки в GET запрос API
const val GET_ARTICLES_BY_POPULARITY = "popularity"
const val GET_ARTICLES_BY_PUBLISHED_AT = "publishedAt"
const val GET_ARTICLES_BY_RELEVANCY = "relevancy"