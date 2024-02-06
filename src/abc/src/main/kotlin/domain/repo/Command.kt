package domain.repo

interface Command {
    suspend fun invoke(cmd: List<String>)
}