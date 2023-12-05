package com.example.demo.controllers

import com.example.demo.models.Order
import com.example.demo.models.UserOrder
import com.example.demo.models.dto.OrderDTO
import com.example.demo.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalTime
import java.util.*

@RestController
@RequestMapping("/api/userOrder")
class UserOrderController(
    @Autowired private val userOrderRepository: UserOrderRepository,
    @Autowired private val statusOrderRepository: StatusOrderRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val orderRepository: OrdersRepository,
    @Autowired private val userCardRepository: UserCardRepository
    ) {
    @GetMapping("")
    fun getAllUserOrders(): List<UserOrder> =
        userOrderRepository.findAll().toList()

    @PostMapping("")
    fun createUserOrder(userOrder: OrderDTO): ResponseEntity<UserOrder> {
        val newUserOrder = UserOrder()
        val order = Order()
        val generateNumber = Random().nextInt(1,99999)
        val generatedOrderNumber = (generateNumber + Calendar.YEAR + Calendar.MONTH + Calendar.DAY_OF_YEAR).toString()
        val currentStatus = statusOrderRepository.findById(1).orElse(null)
        val userCard = userCardRepository.findById(userOrder.userCardId).orElse(null)
        val currentUser = userRepository.findById(userCard.user.id).orElse(null)

        order.numberOrder = generatedOrderNumber
        order.dateOrder = Calendar.DATE.toString()
        order.timeOrder = LocalTime.now().toString()
        order.currentStatus = currentStatus
        order.sumOrder = userOrder.sumOrder
        order.userCard = userCard

        orderRepository.save(order)

        newUserOrder.user = currentUser
        newUserOrder.orders = orderRepository.findById(order.idOrder).orElse(null)

        userOrderRepository.save(newUserOrder)

        return ResponseEntity(newUserOrder, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAllUserOrderById(@PathVariable("id") userId: Long): ResponseEntity<List<UserOrder>> {
        val userOrder = userOrderRepository.findAllByUserId(userId)

        return if (userOrder != null) ResponseEntity(userOrder, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    fun deleteUserOrderById(@PathVariable("id") userOrderId: Long): ResponseEntity<UserOrder> {
        if (!userOrderRepository.existsById(userOrderId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userOrderRepository.deleteById(userOrderId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}