package com.example.demo.controllers


import com.example.demo.models.logs.OrderStatusLog
import com.example.demo.repositories.OrderStatusLogRepository


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orderStatusLog")
class OrderStatusLogController (@Autowired private val orderStatusLogRepository: OrderStatusLogRepository)  {
    @GetMapping("")
    fun getAllOrdersStatusLog(): List<OrderStatusLog> =
        orderStatusLogRepository.findAll().toList()

    @PostMapping("")
    fun createOrdersStatusLog(@RequestBody orderStatusLog: OrderStatusLog): ResponseEntity<OrderStatusLog> {
        val createdOrdersStatusLog = orderStatusLogRepository.save(orderStatusLog)
        return ResponseEntity(createdOrdersStatusLog, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getOrdersStatusLogById(@PathVariable("id") orderId: Long): ResponseEntity<List<OrderStatusLog>> {
        val ordersStatusLog = orderStatusLogRepository.findAllByOrdersIdOrder(orderId)
        return ResponseEntity(ordersStatusLog, HttpStatus.OK)
    }
}