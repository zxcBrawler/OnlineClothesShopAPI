package com.example.demo.models.dto

class OrderDTO {
    var sumOrder = ""
    var userCardId : Long = 0
    var typeDelivery : Long = 0
    var shopAddress : Long? = null
    var userAddress : Long? = null
    var orderComp : List<Long> = arrayListOf()
}