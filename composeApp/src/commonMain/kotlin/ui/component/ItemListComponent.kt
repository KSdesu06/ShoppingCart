package ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Item
import kotlinx.coroutines.Delay

@Composable
fun ItemListComponent(
    modifier: Modifier = Modifier,
    item: Item
) {
    Column {
        Box {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Text(
                    text = "${item.name}",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${item.price}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}