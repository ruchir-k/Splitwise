package commands

import exceptions.BadCommandFormatException

object CommandFactory: Command {
    val commandMap = mutableMapOf<String,Command>().apply {
        put("add_user", AddUserCommand())
        put("add_expense", AddExpenseCommand())
        put("add_split", AddSplitCommand())
        put("show", ShowCommand())
        put("settle", SettleCommand())
    }
    override fun execute(cmd: List<String>) {
        if(!commandMap.containsKey(cmd[0])) {
            throw BadCommandFormatException("Unrecognized command ${cmd[0]}")
        }
        commandMap[cmd[0]]?.execute(cmd)
    }
}