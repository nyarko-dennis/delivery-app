package dev.dna.deliveryapp.model.user

import com.google.firebase.firestore.PropertyName

data class CartItem(
    var id:String? = null,

    @get:PropertyName("item_name")
    @set:PropertyName("item_name")
    var itemName:String,

    @get:PropertyName("item_image_url")
    @set:PropertyName("item_image_url")
    var itemImageUrl:String,

    @get:PropertyName("item_id")
    @set:PropertyName("item_id")
    var itemId:String,

    @get:PropertyName("item_description")
    @set:PropertyName("item_description")
    var itemDescription:String,

    @get:PropertyName("item_quantity")
    @set:PropertyName("item_quantity")
    var itemQuantity:Int,

    @get:PropertyName("item_unit_price")
    @set:PropertyName("item_unit_price")
    var itemUnitPrice:Float,

    @get:PropertyName("item_quantity_price")
    @set:PropertyName("item_quantity_price")
    var itemQuantityPrice:Float,

    @get:PropertyName("seller_id")
    @set:PropertyName("seller_id")
    var sellerId:String
)