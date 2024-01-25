package commands

import kotlin.jvm.Throws

interface Command {
    fun execute(cmd: List<String>)
}