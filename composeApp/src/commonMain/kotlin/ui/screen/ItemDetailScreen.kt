package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.Item
import item.ItemEvent
import kotlinx.coroutines.delay

class ItemDetailScreen(
    private val newItem: Item?,
    private val onEvent: (ItemEvent) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        ItemDetail(
            newItem = newItem,
            onEvent = onEvent
        )
    }

    @Composable
    fun ItemDetail(
        modifier: Modifier = Modifier,
        newItem: Item?,
        onEvent: (ItemEvent) -> Unit,
    ) {

        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        IconButton(
            onClick = { bottomSheetNavigator.hide() },
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close",
            )
        }
        Card() {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Item Detail"
                )
                Spacer(Modifier.height(30.dp))
                Row() {
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            bottomSheetNavigator.show(AddItemScreen(newItem = newItem, onEvent = onEvent))
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = "Edit",
                        )
                    }
                    IconButton(
                        onClick = { bottomSheetNavigator.hide() },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete",
                        )
                    }
                }
                Text(
                    text = "Item Name"
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Item Price"
                )
            }
        }
    }
}