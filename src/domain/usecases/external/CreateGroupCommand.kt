package domain.usecases.external

import data.BookKeeper
import utils.Utils
import domain.entities.Group
import domain.entities.User
import domain.repo.Command

class CreateGroupCommand: Command {
    override fun invoke(cmd: List<String>) {
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