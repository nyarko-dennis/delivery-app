package dev.dna.deliveryapp.data.fake

data class SalesItem(
    val itemName: String,
    val category: String,
    val itemImage: Int,
    val rating: Double,
    val price: Float? = null,
    val itemDescription:String = "served with gizzard pear and\nassorted meat"
)
