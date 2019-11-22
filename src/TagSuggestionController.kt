package com.art.task

private var recentHashtags: MutableSet<String> = mutableSetOf()

class TagSuggestionController : SuggestionController <Tag> {
   override fun search(userInput: String): MutableList<String> {
        val suggestionTags : MutableList<String> = mutableListOf()

        for (tag in tagList){
            if (tag.name.contains(userInput,ignoreCase = true)){
                suggestionTags.add(tag.name)
            }
        }
        return suggestionTags
    }

    override fun recent(): MutableSet<String> {
        return recentHashtags
    }

    override fun getSelectedItem(tagName: String): Tag {
        var selectedTag = Tag()

        for (tag in tagList){
            if (tag.name.equals(tagName)){
                selectedTag=tag
                recentHashtags.add(tag.name)
                return selectedTag
            }
        }
        return selectedTag
    }
}

