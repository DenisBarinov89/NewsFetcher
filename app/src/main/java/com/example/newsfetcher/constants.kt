package com.example.newsfetcher

const val API_KEY = "d39922992381461ab2f5334560e58eca"

//Параметры сортировок
const val SORT_POPULARITY = "Popularity"
const val SORT_DATE_ASCENDING = "Date"
const val SORT_TITLE_ASCENDING = "Title"

//Параметры сортировок для подставновки в GET запрос API
const val GET_ARTICLES_BY_POPULARITY = "popularity"
const val GET_ARTICLES_BY_PUBLISHED_AT = "publishedAt"
const val GET_ARTICLES_BY_RELEVANCY = "relevancy"

//Текст кнопок
const val GET_RESULT_BUTTON = "Show results"

//Ключи для передачи данных между фрагментами
const val BUNDLE_KEY_FOR_ARTICLE_MODEL = "article"