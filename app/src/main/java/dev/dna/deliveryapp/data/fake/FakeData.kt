package dev.dna.deliveryapp.data.fake

import dev.dna.deliveryapp.R

internal val FAKE_DATA = fakeData()
val tmp = SalesItem(
    itemName = "Pizza Hut",
    category = "Fast food",
    itemImage = R.drawable.fast_food_3,
    rating = 5.0
)

val orders_made = listOf<OrderItemInfo>(
    OrderItemInfo(
        tmp,
        StoresAround(
            storeName = "Pizza Man",
            listOfSaleItems = listOf(tmp),
            location = "Tech Junction"
        ),
        quantity = 2,
        orderStatus = OrderStatus.CONFIRMED
    ),
    OrderItemInfo(
        tmp,
        StoresAround(
            storeName = "Pizza Man",
            listOfSaleItems = listOf(tmp),
            location = "Tech Junction"
        ),
        quantity = 2,
        orderStatus = OrderStatus.CONFIRMED
    ),
    OrderItemInfo(
        tmp,
        StoresAround(
            storeName = "Pizza Man",
            listOfSaleItems = listOf(tmp),
            location = "Tech Junction"
        ),
        quantity = 2,
        orderStatus = OrderStatus.DELIVERED
    ),
    OrderItemInfo(
        tmp,
        StoresAround(
            storeName = "Pizza Man",
            listOfSaleItems = listOf(tmp),
            location = "Tech Junction"
        ),
        quantity = 2,
        orderStatus = OrderStatus.DISPATCHED
    ),
    OrderItemInfo(
        tmp,
        StoresAround(
            storeName = "Pizza Man",
            listOfSaleItems = listOf(tmp),
            location = "Tech Junction"
        ),
        quantity = 2,
        orderStatus = OrderStatus.PENDING
    ),

)

fun fakeData(): List<StoresAround> {
    val tmp = SalesItem(
        itemName = "Pizza Hut",
        category = "Fast food",
        itemImage = R.drawable.fast_food_3,
        rating = 5.0
    )

    val temp = StoresAround(
        storeName = "Pizza Man",
        listOfSaleItems = listOf(tmp),
        location = "Tech Junction"
    )

    return listOf(
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
        temp,
    )
}

val fake_data = listOf(
    SalesItem(itemName = "Promo", category = "Local", itemImage = R.drawable.gob3_1, rating = 5.0),
    SalesItem(itemName = "Promo", category = "Local", itemImage = R.drawable.gob3_2, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0),
    SalesItem(itemName = "KFC", category = "Fast Food", itemImage = R.drawable.kfc1, rating = 5.0)
)

val fake_data_fashion = listOf(
    SalesItem(
        itemName = "Lofty Fashion",
        category = "Lofty Fashion",
        itemImage = R.drawable.fashion_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Lofty Fashion",
        category = "Lofty Fashion",
        itemImage = R.drawable.fashion_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Jozzy's",
        category = "Jozzy's",
        itemImage = R.drawable.fashion_1,
        rating = 5.0
    )
)

val fake_data_electronics = listOf(
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Fatawos Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "TCL Electronics",
        category = "Ram",
        itemImage = R.drawable.electronics_1,
        rating = 5.0
    ),
)

val fake_data_cartItems = listOf(
    CartItem(
        itemName = "Gob3 pro max - Large",
        storeName = "Atta gob3",
        itemQuantity = 1,
        itemPrice = 1.00f
    ),
    CartItem(
        itemName = "Gob3 pro max - Large",
        storeName = "Atta gob3",
        itemQuantity = 1,
        itemPrice = 1.00f
    ),
    CartItem(
        itemName = "Gob3 pro max - Large",
        storeName = "Atta gob3",
        itemQuantity = 1,
        itemPrice = 1.00f
    ),
    CartItem(
        itemName = "Gob3 pro max - Large",
        storeName = "Atta gob3",
        itemQuantity = 1,
        itemPrice = 1.00f
    ),
)

val fake_data_health_and_beauty = listOf(
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Younique Looks and Styles",
        category = "Younique Looks and Styles",
        itemImage = R.drawable.cosmetic_2,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Ikedor Cosmetics",
        category = "Ikedor Cosmetics",
        itemImage = R.drawable.cosmetic_1,
        rating = 5.0
    ),
    SalesItem(
        itemName = "Oga's Naturals",
        category = "Oga's Naturals",
        itemImage = R.drawable.cosmetic_3,
        rating = 5.0
    ),

    )

data class RestaurantType(
    val type: String,
    val icon: Int
)

val fakeDataStore = listOf(
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),
    SalesItem(
        itemName = "Gob3 pro max - Large",
        category = "Local",
        itemImage = R.drawable.gob3_pro,
        rating = 5.0,
        price = 1.00f
    ),

    )