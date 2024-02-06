package domain.usecases.external

import data.BookKeeper
import domain.entities.User
import domain.repo.Command

class AddUserCommand: Command {
    override suspend fun invoke(cmd: List<String>) {
        val bk = BookKeeper
        bk.addUser(cmd[1],cmd[2])
    }
}