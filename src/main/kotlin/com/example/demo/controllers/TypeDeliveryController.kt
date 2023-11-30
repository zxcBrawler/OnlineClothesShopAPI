package com.example.demo.controllers

import com.example.demo.models.TypeDelivery
import com.example.demo.repositories.TypeDeliveryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/typeDelivery")
class TypeDeliveryController(@Autowired private val typeDeliveryRepository: TypeDeliveryRepository) {
    @GetMapping("")
    fun getAllTypesDelivery(): List<TypeDelivery> =
        typeDeliveryRepository.findAll().toList()

    @PostMapping("")
    fun createTypeDelivery(@RequestBody typeDelivery: TypeDelivery): ResponseEntity<TypeDelivery> {
        val createdTypeDelivery = typeDeliveryRepository.save(typeDelivery)
        return ResponseEntity(createdTypeDelivery, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getTypeDeliveryById(@PathVariable("id") typeDeliveryId: Long): ResponseEntity<TypeDelivery> {
        val typeDelivery = typeDeliveryRepository.findById(typeDeliveryId).orElse(null)
        return if (typeDelivery != null) ResponseEntity(typeDelivery, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateTypeDeliveryById(@PathVariable("id") typeDeliveryId: Long, @RequestBody typeDelivery: TypeDelivery): ResponseEntity<TypeDelivery> {

        val existingTypeDelivery = typeDeliveryRepository.findById(typeDeliveryId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedTypeDelivery = existingTypeDelivery.copy(
            nameType = typeDelivery.nameType)
        typeDeliveryRepository.save(updatedTypeDelivery)
        return ResponseEntity(updatedTypeDelivery, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteTypeDeliveryById(@PathVariable("id") typeDeliveryId: Long): ResponseEntity<TypeDelivery> {
        if (!typeDeliveryRepository.existsById(typeDeliveryId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        typeDeliveryRepository.deleteById(typeDeliveryId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}