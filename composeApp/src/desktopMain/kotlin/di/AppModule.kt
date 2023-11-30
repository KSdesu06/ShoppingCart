package di

import com.example.kat.database.ItemDatabase
import data.ItemRepositoryImpl
import domain.repository.ItemRepository
import driver.DatabaseDriverFactory

actual class AppModule {
    actual val itemRepository: ItemRepository by lazy {
        val database = ItemDatabase(DatabaseDriverFactory().createDriver())
        ItemRepositoryImpl(database = database)
    }

}