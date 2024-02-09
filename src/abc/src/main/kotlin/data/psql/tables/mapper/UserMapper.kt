package data.psql.tables.mapper

import data.psql.tables.UserTable
import domain.entities.User
import exceptions.NoSuchUserException
import org.jetbrains.exposed.sql.ResultRow
import javax.inject.Inject

class UserMapper
@Inject
constructor(){
    fun map(row: ResultRow?): User? {
        if(row == null) {
            throw NoSuchUserException("error in mapper")
        }
        return User (
            name = row[UserTable.name],
            email = row[UserTable.email],
            id = row[UserTable.userid]
        )
    }
}