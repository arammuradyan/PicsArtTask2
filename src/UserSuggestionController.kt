package com.art.task

private var recentUsers: MutableSet<String> = mutableSetOf()

class UserSuggestionController : SuggestionController <User> {
    override fun search(userInput: String): MutableList<String> {
        val suggestionUsers : MutableList<String> = mutableListOf()

        for (user in userList){
            if ((user.name+" "+user.surname).contains(userInput,ignoreCase = true)){
                suggestionUsers.add(user.username)
            }
        }
        return suggestionUsers
    }

    override fun recent(): MutableSet<String> {
        return recentUsers
    }

    override fun getSelectedItem(userName: String): User {
        var selectedUser = User()

        for (user in userList){
            if (user.username.equals(userName)){
                selectedUser=user
                recentUsers.add(user.username)
                return selectedUser
            }
        }
        return selectedUser
    }
}
