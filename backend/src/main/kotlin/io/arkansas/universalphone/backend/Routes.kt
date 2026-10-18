package io.arkansas.universalphone.backend

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils

object Devices : Table("devices") {
    val id = varchar("id", 64)
    val model = varchar("model", 128)
    val osVersion = varchar("os_version", 32)
    val createdAt = long("created_at")
    override val primaryKey = PrimaryKey(id)
}

object Settings : Table("device_settings") {
    val deviceId = varchar("device_id", 64)
    val key = varchar("setting_key", 128)
    val value = text("setting_value")
    override val primaryKey = PrimaryKey(deviceId, key)
}

@Serializable data class DeviceRegistration(val id: String, val model: String, val osVersion: String)
@Serializable data class SettingValue(val key: String, val value: String)

fun Route.apiRoutes() {
    route("/api/v1") {
        post("/devices") {
            val request = call.receive<DeviceRegistration>()
            transaction {
                Devices.insertIgnore {
                    it[id] = request.id; it[model] = request.model
                    it[osVersion] = request.osVersion; it[createdAt] = System.currentTimeMillis()
                }
            }
            call.respond(HttpStatusCode.Created, request)
        }
        get("/devices/{id}/settings") {
            val deviceId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = transaction {
                Settings.selectAll().where { Settings.deviceId eq deviceId }.map { SettingValue(it[Settings.key], it[Settings.value]) }
            }
            call.respond(result)
        }
        put("/devices/{id}/settings/{key}") {
            val deviceId = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val key = call.parameters["key"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val body = call.receive<SettingValue>()
            transaction {
                Settings.deleteWhere { (Settings.deviceId eq deviceId) and (Settings.key eq key) }
                Settings.insert { it[Settings.deviceId] = deviceId; it[Settings.key] = key; it[value] = body.value }
            }
            call.respond(HttpStatusCode.NoContent)
        }
    }
}

fun initializeSchema() = transaction { SchemaUtils.createMissingTablesAndColumns(Devices, Settings) }
