package data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.kat.database.ItemDatabase
import domain.model.Item
import domain.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl(
    database: ItemDatabase
): ItemRepository {

    private val dbQueries = database.itemjaQueries
    override fun getItems(): Flow<List<Item>> {
        return dbQueries
            .getItems()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { itemEntities ->
                itemEntities.map { itemEntity ->
                    itemEntity.toItem()
                }
            }
    }

    override suspend fun insertItem(item: Item) {
        dbQueries.insertItem(
            id = item.id,
            name = item.name,
            price = item.price.toLong()
        )
    }

    override suspend fun deleteItem(id: Long) {
        dbQueries.deleteItem(id)
    }

}