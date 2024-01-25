package commands

import BookKeeper
import models.User

class AddUserCommand: Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        val user = User(cmd[1],cmd[2], cmd[3])
        bk.addUser(user)
    }
}