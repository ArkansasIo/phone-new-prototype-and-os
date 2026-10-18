package io.arkansas.universalphone.backend

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    lateinit var dataSource: HikariDataSource
        private set

    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = System.getenv("MYSQL_URL") ?: "jdbc:mysql://localhost:3306/universal_phone"
            username = System.getenv("MYSQL_USER") ?: "phone"
            password = System.getenv("MYSQL_PASSWORD") ?: "change-me"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            maximumPoolSize = (System.getenv("MYSQL_POOL_SIZE") ?: "10").toInt()
            minimumIdle = 2
            connectionTimeout = 10_000
            validationTimeout = 5_000
        }
        dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }
}

fun Application.configureDatabase() = DatabaseFactory.init()
