package domain.repo

interface Command {
    fun invoke(cmd: List<String>)
}