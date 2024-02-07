package adapter

import data.psql.DatabaseFactory
import data.psql.repos.PsqlUserRepo
import data.psql.tables.mapper.UserMapper
import domain.usecases.external.AddUserCommand

class AddUserService
constructor(
    private val addUserCommand: AddUserCommand
): Service{
    override suspend fun invoke() {
        print("Name: ")
        val name = readln()
        print("Email: ")
        val email = readln()
        addUserCommand.invoke(name, email)
    }

}