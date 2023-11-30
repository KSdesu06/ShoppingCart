package domain.repository

import domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    suspend fun insertItem(item: Item)
    suspend fun deleteItem(id: Long)
}