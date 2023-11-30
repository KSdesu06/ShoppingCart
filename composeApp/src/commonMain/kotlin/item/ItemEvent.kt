package item

import domain.model.Item

sealed interface ItemEvent {
    data class OnNameChanged(val value: String): ItemEvent
    data class OnPriceChanged(val value: String): ItemEvent
    data class EditItem(val item: Item): ItemEvent
    object SaveItem: ItemEvent
    object DeleteItem: ItemEvent
}