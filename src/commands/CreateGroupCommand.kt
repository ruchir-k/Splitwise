package commands

import BookKeeper
import Utils
import models.Group
import models.User

class CreateGroupCommand: Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        val name: String = cmd[1]

        val numOfUsers = cmd.size - 2
        var users = mutableListOf<User>()

        for(i in 0 until numOfUsers) {
            val user = Utils.getUser(cmd[2+i])
            users.add(user)
        }

        bk.createGroup(Group(name, users))

    }
}