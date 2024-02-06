package data.psql.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object BalancesTable: Table() {
    val groupid: Column<Int> = integer("groupid")
    val oweruserid: Column<Int> = integer("oweruserid")
    val owedToUserid: Column<Int> = integer("owedToUserid")
    val amount: Column<Double> = double("amount")
}