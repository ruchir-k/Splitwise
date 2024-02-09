package data.psql.repos

import data.psql.DatabaseFactory
import data.psql.tables.UserTable
import data.psql.tables.mapper.UserMapper
import domain.entities.User
import domain.repo.UserRepo
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import javax.inject.Inject

class PsqlUserRepo
@Inject
constructor(
    val db: DatabaseFactory,
    val userMapper: UserMapper
): UserRepo {
    override suspend fun addUser(name: String, email: String): User {
        var statement: InsertStatement<Number>? = null
        db.dbQuery {
            statement = UserTable.insert { userNew ->
                userNew[UserTable.name] = name
                userNew[UserTable.email] = email
            }
        }
        return statement?.resultedValues?.get(0).let {
            userMapper.map(it)!!
        }
    }

    override suspend fun getUser(id: Int): User? =
        db.dbQuery {
            UserTable.select {
                UserTable.userid.eq(id)
            }.map {
                userMapper.map(it)
            }.singleOrNull()
        }


}