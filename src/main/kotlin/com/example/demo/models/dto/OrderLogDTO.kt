package com.example.demo.models.dto

import com.example.demo.models.Order
import com.example.demo.models.StatusOrder

class OrderLogDTO {
    val currentStatus = StatusOrder()
    val order = Order()
    val timestamp = ""
}