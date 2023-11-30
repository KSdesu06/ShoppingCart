package di

import android.content.Context
import com.example.kat.database.ItemDatabase
import data.ItemRepositoryImpl
import domain.repository.ItemRepository
import driver.DatabaseDriverFactory

actual class AppModule(private val context: Context) {
    actual val itemRepository: ItemRepository by lazy {
        ItemRepositoryImpl(
            database = ItemDatabase(
                driver = DatabaseDriverFactory(context).createDriver()
            )
        )
    }
}