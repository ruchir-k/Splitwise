package domain.usecases.external

import data.BookKeeper
import domain.entities.User
import domain.repo.Command

class AddUserCommand: Command {
    override fun invoke(cmd: List<String>) {
        val bk = BookKeeper
        val user = User(cmd[1],cmd[2], cmd[3])
        bk.addUser(user)
    }
}