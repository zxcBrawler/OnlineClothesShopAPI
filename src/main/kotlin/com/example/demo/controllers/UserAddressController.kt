package com.example.demo.controllers

import com.example.demo.models.UserAddress
import com.example.demo.models.UserOrder
import com.example.demo.repositories.UserAddressRepository
import com.example.demo.repositories.UserOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/userAddress")
class UserAddressController (@Autowired private val userAddressRepository: UserAddressRepository) {
    @GetMapping("")
    fun getAllUserAddresses(): List<UserAddress> =
        userAddressRepository.findAll().toList()

    @PostMapping("")
    fun createUserOrder(@RequestBody userAddress: UserAddress): ResponseEntity<UserAddress> {
        val createdUserAddress = userAddressRepository.save(userAddress)
        return ResponseEntity(createdUserAddress, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserAddressById(@PathVariable("id") userAddressId: Long): ResponseEntity<UserAddress> {
        val userAddress = userAddressRepository.findById(userAddressId).orElse(null)
        return if (userAddress != null) ResponseEntity(userAddress, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }


    @DeleteMapping("/{id}")
    fun deleteUserAddressById(@PathVariable("id") userAddressId: Long): ResponseEntity<UserAddress> {
        if (!userAddressRepository.existsById(userAddressId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userAddressRepository.deleteById(userAddressId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}