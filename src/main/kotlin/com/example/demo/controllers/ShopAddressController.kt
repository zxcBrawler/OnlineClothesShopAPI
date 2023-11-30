package com.example.demo.controllers

import com.example.demo.models.ShopAddresses
import com.example.demo.repositories.ShopAddressesRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/shopAddresses")
class ShopAddressesController (@Autowired private val shopAddressesRepository: ShopAddressesRepository)  {
    @GetMapping("")
    fun getAllShopAddresses(): List<ShopAddresses> =
        shopAddressesRepository.findAll().toList()

    @PostMapping("")
    fun createShopAddresses(@RequestBody shopAddresses: ShopAddresses): ResponseEntity<ShopAddresses> {
        val createdShopAddresses = shopAddressesRepository.save(shopAddresses)
        return ResponseEntity(createdShopAddresses, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getShopAddressesById(@PathVariable("id") shopAddressesId: Int): ResponseEntity<ShopAddresses> {
        val shopAddresses = shopAddressesRepository.findById(shopAddressesId.toLong()).orElse(null)
        return ResponseEntity(shopAddresses, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateShopAddressesById(@PathVariable("id") shopAddressesId: Long, @RequestBody shopAddresses: ShopAddresses): ResponseEntity<ShopAddresses> {

        val existingShopAddresses = shopAddressesRepository.findById(shopAddressesId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedShopAddresses = existingShopAddresses.copy(
            shopAddressDirection = shopAddresses.shopAddressDirection,
            shopMetro = shopAddresses.shopMetro)
        shopAddressesRepository.save(updatedShopAddresses)
        return ResponseEntity(updatedShopAddresses, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteShopAddressesById(@PathVariable("id") shopAddressesId: Long): ResponseEntity<ShopAddresses> {
        if (!shopAddressesRepository.existsById(shopAddressesId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        shopAddressesRepository.deleteById(shopAddressesId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}