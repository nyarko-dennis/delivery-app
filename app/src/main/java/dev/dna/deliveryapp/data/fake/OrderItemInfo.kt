package dev.dna.deliveryapp.data.fake

data class OrderItemInfo(
    val item: SalesItem,
    val store: StoresAround,
    val quantity:Int,
    val orderStatus: OrderStatus
)

enum class OrderStatus{
    CONFIRMED,
    PENDING,
    DISPATCHED,
    DELIVERED
}