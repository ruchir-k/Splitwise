package data.psql.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserTable: Table() {
    val userid:Column<Int> = integer("userid").autoIncrement()
    val name:Column<String> = varchar("name", 512)
    val email:Column<String> = varchar("email", 512)

    override val primaryKey: PrimaryKey = PrimaryKey(userid)
}