package data.psql.tables.mapper

import data.psql.tables.GroupTable
import domain.entities.Group
import exceptions.NoSuchGroupException
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow

class GroupMapper {
    fun map(row: ResultRow?): Group? {
        if(row == null) {
            throw NoSuchGroupException("error in mapper")
        }
        return Group(
            name = row[GroupTable.name],
            users = Json.decodeFromString<List<Int>>(row[GroupTable.users]),
            id = row[GroupTable.groupid]
        )
    }
}