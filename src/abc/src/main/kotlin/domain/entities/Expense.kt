package domain.entities

data class Expense (val groupid: Int, val oweruserid: Int, val owedToUserId: Int, val amount: Double) {
}