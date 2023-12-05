package com.example.demo.repositories

import com.example.demo.models.Order
import org.springframework.data.repository.CrudRepository

interface OrdersRepository: CrudRepository<Order, Long> {


}