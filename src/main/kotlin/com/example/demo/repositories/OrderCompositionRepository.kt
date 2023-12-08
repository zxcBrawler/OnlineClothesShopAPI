package com.example.demo.repositories

import com.example.demo.models.OrderComposition
import org.springframework.data.repository.CrudRepository

interface OrderCompositionRepository: CrudRepository<OrderComposition, Long> {

    fun findByOrderIdIdOrder (orderId : Long) : List<OrderComposition>
}