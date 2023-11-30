package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.Item
import item.ItemEvent
import item.ItemState
import ui.component.TextFieldComponent

class AddItemScreen(
    private val newItem: Item?,
    private val onEvent: (ItemEvent) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        AddItem(newItem = newItem, onEvent = onEvent)
    }

    @Composable
    fun AddItem(
        modifier: Modifier = Modifier,
        backgroundColor: Color = Color.Black,
        newItem: Item?,
        onEvent: (ItemEvent) -> Unit
    ) {

        var name by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
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

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TextFieldComponent(
                value = newItem?.name ?: "",
                onValueChange = { onEvent(ItemEvent.OnNameChanged(it)) },
                placeholder = { Text(text = "Enter Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(Modifier.height(16.dp))
            TextFieldComponent(
                value = newItem?.price.toString(),
                onValueChange = { onEvent(ItemEvent.OnPriceChanged(it)) },
                placeholder = { Text(text = "Enter Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(Modifier.height(30.dp))
            OutlinedButton(
                onClick = { onEvent(ItemEvent.SaveItem) },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Save Item")
            }
        }
    }
}