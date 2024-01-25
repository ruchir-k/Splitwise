package models

import models.expenses.Expense

data class User (val name: String, val email: String, val hashedPass: String) {

    val uid: Long = NEW_UID++
    var expenses: MutableList<Expense>? = null

    companion object {
        private var NEW_UID = 0L
    }

    override fun equals(other: Any?): Boolean {
        if(other !is User) {
            return false
        }
        return uid == (other as User).uid
    }

    override fun toString(): String {
        return "uid:$uid, name:$name, email:$email"
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + hashedPass.hashCode()
        result = 31 * result + uid.hashCode()
        result = 31 * result + (expenses?.hashCode() ?: 0)
        return result
    }

}

//fun main() {
//    val u1: User = User("ruchir", "email", "123", "none", "abc")
//    println(u1)
//}
