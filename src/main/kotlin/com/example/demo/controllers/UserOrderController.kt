package com.example.demo.controllers

import com.example.demo.models.User
import com.example.demo.models.UserOrder
import com.example.demo.repositories.UserOrderRepository
import com.example.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/userOrder")
class UserOrderController(@Autowired private val userOrderRepository: UserOrderRepository) {
    @GetMapping("")
    fun getAllUserOrders(): List<UserOrder> =
        userOrderRepository.findAll().toList()

    @PostMapping("")
    fun createUserOrder(@RequestBody userOrder: UserOrder): ResponseEntity<UserOrder> {
        val createdUserOrder = userOrderRepository.save(userOrder)
        return ResponseEntity(createdUserOrder, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserOrderById(@PathVariable("id") userOrderId: Long): ResponseEntity<UserOrder> {
        val userOrder = userOrderRepository.findById(userOrderId).orElse(null)
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