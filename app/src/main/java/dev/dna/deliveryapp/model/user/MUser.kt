package dev.dna.deliveryapp.model.user

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MUser(
    @Exclude
    var id:String? = null,

    @get:PropertyName("full_name")
    @set:PropertyName("full_name")
    var fullName:String,

    var email:String,

    @get:PropertyName("phone_number")
    @set:PropertyName("phone_number")
    var phoneNumber: String
)