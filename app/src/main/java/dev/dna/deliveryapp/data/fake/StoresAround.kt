package dev.dna.deliveryapp.data.fake

import dev.dna.deliveryapp.data.fake.SalesItem

data class StoresAround(
    val storeName: String,
    val listOfSaleItems: List<SalesItem>,
    val location: String
)
