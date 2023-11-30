package driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.kat.database.ItemDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val jdbcUrl = "jdbc:sqlite:item_na.db"
        val driver = JdbcSqliteDriver(jdbcUrl)

        ItemDatabase.Schema.create(driver)

        return driver

    }
}
