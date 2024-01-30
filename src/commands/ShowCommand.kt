package commands

import BookKeeper
import Utils
import exceptions.NoSuchGroupException
import exceptions.NoSuchUserException
import models.Group
import models.User

class ShowCommand: Command {
    override fun execute(cmd: List<String>) {
        val bk = BookKeeper
        if(cmd.size > 2) {
            val group: Group
            try {
                group = Utils.getGroup(cmd[1])
                val user: User
                try {
                    user = Utils.getUser(cmd[2])
                    bk.showBalance(group, user)
                } catch (e: NoSuchUserException) {
                    println(e.message)
                }
            } catch (e: NoSuchGroupException) {
                println(e.message)
            }
        }
        else if (cmd.size == 2) {
            val group: Group
            try {
                group = Utils.getGroup(cmd[1])
                bk.showAllBalancesGroup(group)
            } catch (e: NoSuchGroupException) {
                println(e.message)
            }
        }
        else {
            bk.showAllBalances()
        }
    }
}