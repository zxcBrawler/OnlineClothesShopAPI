package com.example.demo.controllers

import com.example.demo.models.DeliveryInfo
import com.example.demo.models.TypeDelivery
import com.example.demo.repositories.DeliveryInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/deliveryInfo")
class DeliveryInfoController (@Autowired private val deliveryInfoRepository: DeliveryInfoRepository) {
    @GetMapping("")
    fun getAllDeliveryInfo(): List<DeliveryInfo> =
        deliveryInfoRepository.findAll().toList()

    @PostMapping("")
    fun createDeliveryInfo(@RequestBody deliveryInfo: DeliveryInfo): ResponseEntity<DeliveryInfo> {
        val createdDeliveryInfo = deliveryInfoRepository.save(deliveryInfo)
        return ResponseEntity(createdDeliveryInfo, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getTypeDeliveryById(@PathVariable("id") deliveryInfoId: Long): ResponseEntity<DeliveryInfo> {
        val deliveryInfo = deliveryInfoRepository.findById(deliveryInfoId).orElse(null)
        return if (deliveryInfo != null) ResponseEntity(deliveryInfo, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }
}