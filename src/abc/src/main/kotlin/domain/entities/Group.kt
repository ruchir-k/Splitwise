package domain.entities

data class Group(val name: String, val users: List<Int>, var id: Int = 0){
}