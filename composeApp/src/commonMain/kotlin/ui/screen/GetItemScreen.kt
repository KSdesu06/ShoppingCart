package ui.screen

import App
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.AppModule
import domain.model.Item
import domain.repository.ItemRepository
import item.ItemEvent
import item.ItemState
import item.ItemViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.component.ItemListComponent


class GetItemScreen(
    private val appModule: AppModule,
) : Screen {
    @Composable
    override fun Content() {
        val itemRepository: ItemRepository
        val viewModel = getViewModel(
            key = "item-screen",
            factory = viewModelFactory { ItemViewModel(appModule.itemRepository) }
        )
        val state by viewModel.state.collectAsState()
        GetItemScreen(
            state = state,
            newItem = viewModel.newItem,
            onEvent = viewModel::onEvent
        )
    }

    @Composable
    fun GetItemScreen(
        modifier: Modifier = Modifier,
        state: ItemState,
        newItem: Item?,
        onEvent: (ItemEvent) -> Unit,
    ) {

        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        bottomSheetNavigator.show(
                            AddItemScreen(
                                newItem = newItem,
                                onEvent = onEvent
                            )
                        )
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Add Item",
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Items",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                }

                items(state.items) { item ->
                    ItemListComponent(
                        modifier = Modifier
                            .clickable {
                                bottomSheetNavigator.show(ItemDetailScreen())
                            },
                        item = item
                    )
                }
            }
        }
    }
}
