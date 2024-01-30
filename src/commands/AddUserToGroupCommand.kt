package commands

import BookKeeper
import Utils
import models.Group
import models.User

class AddUserToGroupCommand:Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        val group:Group = Utils.getGroup(cmd[1])
        val user:User = Utils.getUser(cmd[2])
        bk.addUserToGroup(user, group)
    }
}