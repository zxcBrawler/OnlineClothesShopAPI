package com.example.demo.controllers

import com.example.demo.models.Orders
import com.example.demo.repositories.OrdersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrdersController (@Autowired private val ordersRepository: OrdersRepository)  {
    @GetMapping("")
    fun getAllOrders(): List<Orders> =
        ordersRepository.findAll().toList()

    @PostMapping("")
    fun createOrders(@RequestBody orders: Orders): ResponseEntity<Orders> {
        val createdOrders = ordersRepository.save(orders)
        return ResponseEntity(createdOrders, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getOrdersById(@PathVariable("id") ordersId: Long): ResponseEntity<Orders> {
        val orders = ordersRepository.findById(ordersId).orElse(null)
        return if (orders != null) ResponseEntity(orders, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateOrdersById(@PathVariable("id") ordersId: Long, @RequestBody orders: Orders): ResponseEntity<Orders> {

        val existingOrders = ordersRepository.findById(ordersId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedOrders = existingOrders.copy(
            timeOrder = orders.timeOrder,
            dateOrder = orders.dateOrder,
            sumOrder = orders.sumOrder,
            currentStatus = orders.currentStatus,
            numberOrder = orders.numberOrder)
        ordersRepository.save(updatedOrders)
        return ResponseEntity(updatedOrders, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteOrdersById(@PathVariable("id") ordersId: Long): ResponseEntity<Orders> {
        if (!ordersRepository.existsById(ordersId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        ordersRepository.deleteById(ordersId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}