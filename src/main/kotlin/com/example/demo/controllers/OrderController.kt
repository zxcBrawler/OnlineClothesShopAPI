package com.example.demo.controllers

import com.example.demo.models.Order
import com.example.demo.models.dto.OrderDTO
import com.example.demo.repositories.OrdersRepository
import com.example.demo.repositories.StatusOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalTime
import java.util.*

//ADMIN CONTROLLER
@RestController
@RequestMapping("/api/orders")
class OrderController (
    @Autowired private val ordersRepository: OrdersRepository,
    @Autowired private val statusOrderRepository: StatusOrderRepository,
    )  {
    @GetMapping("")
    fun getAllOrders(): List<Order> =
        ordersRepository.findAll().toList()

    @PostMapping("")
    fun createOrders(orders: OrderDTO): ResponseEntity<Order> {
        val order = Order()
        val generateNumber = Random().nextInt(1,999)
        val generatedOrderNumber = (generateNumber + Calendar.YEAR + Calendar.MONTH + Calendar.DAY_OF_YEAR).toString()
        val currentStatus = statusOrderRepository.findById(1).orElse(null)

        order.numberOrder = generatedOrderNumber
        order.dateOrder = Calendar.DATE.toString()
        order.timeOrder = LocalTime.now().toString()
        order.currentStatus = currentStatus
        order.sumOrder = orders.sumOrder

        return ResponseEntity(order, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getOrdersById(@PathVariable("id") ordersId: Long): ResponseEntity<Order> {
        val orders = ordersRepository.findById(ordersId).orElse(null)
        return if (orders != null) ResponseEntity(orders, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateOrdersById(@PathVariable("id") ordersId: Long, @RequestBody orders: Order): ResponseEntity<Order> {

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
    fun deleteOrdersById(@PathVariable("id") ordersId: Long): ResponseEntity<Order> {
        if (!ordersRepository.existsById(ordersId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        ordersRepository.deleteById(ordersId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}