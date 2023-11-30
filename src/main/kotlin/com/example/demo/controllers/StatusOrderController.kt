package com.example.demo.controllers

import com.example.demo.models.StatusOrder
import com.example.demo.models.TypeClothes
import com.example.demo.repositories.StatusOrderRepository
import com.example.demo.repositories.TypeClothesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/statusOrder")
class StatusOrderController (@Autowired private val statusOrderRepository: StatusOrderRepository) {
    @GetMapping("")
    fun getAllStatusOrder(): List<StatusOrder> =
        statusOrderRepository.findAll().toList()

    @PostMapping("")
    fun createStatusOrder(@RequestBody statusOrder: StatusOrder): ResponseEntity<StatusOrder> {
        val createdStatusOrder = statusOrderRepository.save(statusOrder)
        return ResponseEntity(createdStatusOrder, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getStatusOrderById(@PathVariable("id") statusOrderId: Long): ResponseEntity<StatusOrder> {
        val typeClothes = statusOrderRepository.findById(statusOrderId).orElse(null)
        return if (typeClothes != null) ResponseEntity(typeClothes, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateStatusOrderById(@PathVariable("id") statusOrderId: Long, @RequestBody statusOrder: StatusOrder): ResponseEntity<StatusOrder> {

        val existingStatusOrder = statusOrderRepository.findById(statusOrderId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedStatusOrder = existingStatusOrder.copy(
            nameStatus = statusOrder.nameStatus)
        statusOrderRepository.save(updatedStatusOrder)
        return ResponseEntity(updatedStatusOrder, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteStatusOrderById(@PathVariable("id") statusOrderId: Long): ResponseEntity<StatusOrder> {
        if (!statusOrderRepository.existsById(statusOrderId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        statusOrderRepository.deleteById(statusOrderId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}