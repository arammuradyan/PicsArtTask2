package com.art.task

/**
 * User and has tag suggestions
 *
 * We've got database of "users" and "hashtags".
 *  User can search through "users" and "hashtags",
 *  Select item from result
 *  Selected item should be added to recent items(users, hashtags).
 *  Recent list should be unique.
 *
 * Typing "#something", "hashtag" should be searched in database
 * Typing "@someone", "user" should be searched in database
 * Typing "#", result should be recent "hashtags"
 * Typing "@", result should be recent "users"
 *
 *
 * For example
 * 1. Search users or hash tags
 * 2. Display searched items
 * 3. Select user by username (hashtags by name)
 * 4. Display item info (For user: id, username, name, surname. For hashtag: id, name)
 * 5. Add selected user(hashtag) to recent list
 * 6. Display recent users(hashtags)
 */
val tagSuggestionController: SuggestionController<Tag> = TagSuggestionController()
val userSuggestionController: SuggestionController<User> = UserSuggestionController()
var userinput: String="";

fun main() {
    println("""Please enter username or hashtag
        |Note: hashtags should start with # and username with @
        |Type "exit" to end the program
    """.trimMargin())

    getUserInput()
    when{
        userinput.equals("#") -> printRecentTags()
        userinput.equals("@") -> printRecentUsers()
        userinput.startsWith("#") && userinput.length>1  -> searchTags()
        userinput.startsWith("@") && userinput.length>1  -> searchUsers()
        userinput.trim().equals("exit",ignoreCase = true) -> println("EXIT")
        userinput[0]!='#' || userinput[0]!='@' -> showWorning()
    }
}

fun getUserInput(){
    userinput= readLine().toString()
    if(!userinput.trim().isEmpty()) return else getUserInput()
}

fun printRecentTags(){
    if (!tagSuggestionController.recent().isEmpty()){
    println("Recent hashtags: ${tagSuggestionController.recent()}")
        main()
    }else{
        println("Recent hashtags are empty")
        main()
    }
}

fun printRecentUsers(){
    if (!userSuggestionController.recent().isEmpty()){
    println("Recent users ${userSuggestionController.recent()}")
        main()
    }else{
        println("Recent users are empty")
        main()
    }
}


var searchedTags: MutableList<String> = mutableListOf()

fun searchTags(){
       searchedTags = tagSuggestionController.search(userinput.removePrefix("#"))
       if (!searchedTags.isEmpty()) {
           println("searched tags: $searchedTags")
       } else {
           println("No result")
           main()
       }

    println("Type hashtag that you interested in")
    getUserInput()
    if (searchedTags.contains(userinput)) {

        val selectedTag = tagSuggestionController.getSelectedItem(userinput)
        if (selectedTag.name.isEmpty()) {
            println("No result")
            main()
        } else {
            println(selectedTag)
            main()
        }

    }else {
        println("No such hashtag in searched hashtags list")
        main()
    }
}

var searchedUsers: MutableList<String> = mutableListOf()

fun searchUsers(){
    searchedUsers = userSuggestionController.search(userinput.removePrefix("@"))
       if(!searchedUsers.isEmpty()){
           println("searched users: $searchedUsers")
       } else {
           println("No result")
           main()
       }

    println("Type username that you interested in")
    getUserInput()
    if (searchedUsers.contains(userinput)) {

        val selectedUser = userSuggestionController.getSelectedItem(userinput)
        if (selectedUser.name.isEmpty()) {
            println("No result")
            main()
        } else {
            println(selectedUser)
            main()
        }

    } else {
        println("No such user in searched users list")
        main()
    }
}

fun showWorning(){
    println("""Wrong input
        |Searched input must start with "#" for hashtags, or with "@" for users
    """.trimMargin())
    main()
}



