package ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


class GetItemScreen() : Screen {
    @Composable
    override fun Content() {

        GetItemScreen()

    }

    @Composable
    fun GetItemScreen(modifier: Modifier = Modifier) {

        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        bottomSheetNavigator.show(AddItemScreen())
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Add Item",
                    )
                }
            }
        ) {
            Column(modifier = modifier
                .padding(16.dp)
            ) {
                Spacer(Modifier.height(50.dp))
                Text(text = "Get Item Screen")

            }
        }
    }
}