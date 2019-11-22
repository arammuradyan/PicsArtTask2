package com.art.task

interface SuggestionController<T> {
    fun search(userInput: String): MutableList<String>
    fun recent(): MutableSet<String>
    fun getSelectedItem(userInput: String):T
}