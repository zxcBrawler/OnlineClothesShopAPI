package com.example.demo.controllers

import com.example.demo.models.DeliveryInfo
import com.example.demo.models.OrderComposition
import com.example.demo.repositories.OrderCompositionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ordersComp")
class OrderCompositionController (@Autowired private val orderCompositionRepository: OrderCompositionRepository)  {
    @GetMapping("")
    fun getAllOrderComp(): List<OrderComposition> =
        orderCompositionRepository.findAll().toList()

    @PostMapping("")
    fun createOrderComp(@RequestBody orderComp: OrderComposition): ResponseEntity<OrderComposition> {
        val createdOrderComp = orderCompositionRepository.save(orderComp)
        return ResponseEntity(createdOrderComp, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getOrderCompInfoByOrderId(@PathVariable("id") id : Long) : ResponseEntity<List<OrderComposition>>{
        return ResponseEntity(orderCompositionRepository.findByOrderIdIdOrder(id),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteOrderCompById(@PathVariable("id") orderCompId: Long): ResponseEntity<OrderComposition> {
        if (!orderCompositionRepository.existsById(orderCompId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        orderCompositionRepository.deleteById(orderCompId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}