package domain.usecases.external

import data.BookKeeper
import utils.Utils
import domain.entities.Group
import domain.entities.User
import domain.repo.Command

class AddUserToGroupCommand: Command {
    override suspend fun invoke(cmd: List<String>) {
        val bk = BookKeeper
        val group: Group = Utils.getGroup(cmd[1])
        val user: User = Utils.getUser(cmd[2])
        bk.addUserToGroup(user, group)
    }
}