package item

import domain.model.Item

data class ItemState(
    val items: List<Item> = emptyList()
)
