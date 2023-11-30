package item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.Item
import domain.repository.ItemRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemViewModel(
    private val itemRepository: ItemRepository
): ViewModel() {
    private val _state = MutableStateFlow(ItemState())
    val state = combine(
        _state,
        itemRepository.getItems()
    ) { state, items ->
        state.copy(
            items = items
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ItemState())

    var newItem: Item? by mutableStateOf(null)
        private set

    fun onEvent(event: ItemEvent) {
        when(event) {
            is ItemEvent.OnNameChanged -> {
                newItem = newItem?.copy(
                    name = event.value
                )
            }
            is ItemEvent.OnPriceChanged -> {
                newItem = newItem?.copy(
                    price = event.value.toInt()
                )
            }
            ItemEvent.SaveItem -> {
                newItem.let { item ->
                    viewModelScope.launch {
                        if (item != null) {
                            itemRepository.insertItem(item)
                            delay(300L)
                            newItem = null
                        }
                    }
                }
            }
            ItemEvent.DeleteItem -> TODO()
            is ItemEvent.EditItem -> TODO()
            else -> Unit
        }

    }

}