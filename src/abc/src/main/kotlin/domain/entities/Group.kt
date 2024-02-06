package domain.entities

data class Group(val name: String, val users: MutableCollection<User>, var id: Long = 0L){
}