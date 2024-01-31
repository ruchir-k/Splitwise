package domain.usecases.external

import data.BookKeeper
import utils.Utils
import exceptions.NoSuchGroupException
import exceptions.NoSuchUserException
import domain.entities.Group
import domain.entities.User
import domain.repo.Command

class ShowCommand: Command {
    override fun invoke(cmd: List<String>) {
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