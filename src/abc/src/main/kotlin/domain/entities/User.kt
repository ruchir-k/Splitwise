package domain.entities

data class User (val name: String, val email: String, var id: Long = 0L) {

    var expenses: MutableList<Expense>? = null

    override fun equals(other: Any?): Boolean {
        if(other !is User) {
            return false
        }
        return id == (other as User).id
    }

    override fun toString(): String {
        return "uid:$id, name:$name, email:$email"
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + (expenses?.hashCode() ?: 0)
        return result
    }

}