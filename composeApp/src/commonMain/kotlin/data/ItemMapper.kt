package data

import com.example.kat.database.Item_table
import domain.model.Item

fun Item_table.toItem(): Item {
    return Item(
        id = id,
        name = name,
        price = price.toInt()
    )
}