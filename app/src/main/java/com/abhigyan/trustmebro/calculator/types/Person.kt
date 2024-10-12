package com.abhigyan.trustmebro.calculator.types

import kotlinx.serialization.Serializable

@Serializable
class Person(var name: String, var phoneNumber: Long, var upiID: String, var bill: Bill){
    fun pay(){
        /* TODO */
    }

    override fun toString(): String {
        return this.name
    }
}