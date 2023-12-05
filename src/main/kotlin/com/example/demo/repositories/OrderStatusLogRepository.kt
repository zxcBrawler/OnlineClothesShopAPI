package com.example.demo.repositories

import com.example.demo.models.logs.OrderStatusLog
import org.springframework.data.repository.CrudRepository

interface OrderStatusLogRepository: CrudRepository<OrderStatusLog, Long> {

    fun findAllByOrdersIdOrder (orderId : Long) : List<OrderStatusLog>
}