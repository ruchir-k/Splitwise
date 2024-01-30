package commands

import BookKeeper
import exceptions.NoSuchGroupException
import exceptions.NoSuchUserException
import jdk.jshell.execution.Util
import models.Group
import models.User

class SettleCommand:Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        val group: Group
        try {
            group = Utils.getGroup(cmd[1])
        } catch (e: NoSuchGroupException) {
            println(e.message)
            return
        }
        val ower: User
        try {
            ower = Utils.getUser(cmd[2])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }
        val owedTo: User
        try {
            owedTo = Utils.getUser(cmd[3])
        } catch (e: NoSuchUserException) {
            println(e.message)
            return
        }

        bk.settleBalance(group, ower, owedTo)

    }
}