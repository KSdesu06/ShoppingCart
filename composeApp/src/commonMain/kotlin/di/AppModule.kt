package di

import domain.repository.ItemRepository

expect class AppModule {
    val itemRepository: ItemRepository
}