package com.example.demo.repositories

import com.example.demo.models.UserOrder
import org.springframework.data.repository.CrudRepository

interface UserOrderRepository: CrudRepository<UserOrder, Long> {
    fun findAllByUserId (userId : Long) : List<UserOrder>?
}