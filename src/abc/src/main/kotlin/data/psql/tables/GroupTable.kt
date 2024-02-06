package data.psql.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object GroupTable: Table() {
    val groupid: Column<Int> = integer("groupid").autoIncrement()
    val users: Column<String> = varchar("users", 512)

    override val primaryKey: PrimaryKey = PrimaryKey(groupid)
}