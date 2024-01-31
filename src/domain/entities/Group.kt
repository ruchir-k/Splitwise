package domain.entities

data class Group(val name: String, val users: MutableCollection<User>) {

    val uid: Long = NEW_UID++
    var expenses: MutableList<Expense>? = null

    companion object {
        private var NEW_UID = 0L
    }

    override fun toString(): String {
        var retString: String = "group id: $uid \n"
        users.forEach { user: User ->
            retString += "user: ${user.name}, user id: ${user.uid} \n"
        }
        return retString
    }
}