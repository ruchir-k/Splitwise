package data.psql.repos

import data.psql.DatabaseFactory
import data.psql.tables.GroupTable
import data.psql.tables.mapper.GroupMapper
import domain.entities.Group
import domain.repo.GroupRepo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update

class PsqlGroupRepo
constructor(
    val db: DatabaseFactory,
    val groupMapper: GroupMapper
): GroupRepo {
    override suspend fun createGroup(name: String, users: List<Int>): Group {
        var statement: InsertStatement<Number>? = null
        db.dbQuery {
            statement = GroupTable.insert { groupNew ->
                groupNew[GroupTable.name] = name
                groupNew[GroupTable.users] = Json.encodeToString(users)
            }
        }
        return statement?.resultedValues?.get(0).let {
            groupMapper.map(it)!!
        }
    }

    override suspend fun getGroup(id: Int): Group? =
        db.dbQuery {
            GroupTable.select {
                GroupTable.groupid.eq(id)
            }.map {
                groupMapper.map(it)
            }.singleOrNull()
        }

    override suspend fun addUserToGroup(userid: Int, groupid: Int) {
        db.dbQuery {
            val existingUsersJson = GroupTable
                .select { GroupTable.groupid.eq(groupid) }
                .singleOrNull()?.get(GroupTable.users)

            val existingUsersList: MutableList<Int> = Json.decodeFromString(existingUsersJson!!)
            existingUsersList.add(userid)

            GroupTable.update({ GroupTable.groupid.eq(groupid) }) {
                it[users] = Json.encodeToString(existingUsersList)
            }
        }
    }
}