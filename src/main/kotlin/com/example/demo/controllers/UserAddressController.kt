package com.example.demo.controllers

import com.example.demo.models.Address
import com.example.demo.models.UserAddress
import com.example.demo.models.UserOrder
import com.example.demo.models.dto.Message
import com.example.demo.models.dto.UserAddressDTO
import com.example.demo.repositories.AddressRepository
import com.example.demo.repositories.UserAddressRepository
import com.example.demo.repositories.UserOrderRepository
import com.example.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/userAddress")
class UserAddressController (@Autowired private val userAddressRepository: UserAddressRepository,
    @Autowired private val addressRepository: AddressRepository,
    @Autowired private val userRepository: UserRepository,
    ) {
    @GetMapping("")
    fun getAllUserAddresses(): List<UserAddress> =
        userAddressRepository.findAll().toList()

    @PostMapping("")
    fun createUserAddress(userAddress: UserAddressDTO): ResponseEntity<UserAddress> {
        val newUserAddress = UserAddress()
        val newAddress = Address()
        newAddress.city = userAddress.city
        newAddress.directionAddress = userAddress.directionAddress
        newAddress.nameAddress = userAddress.nameAddress
        addressRepository.save(newAddress)

        newUserAddress.address = addressRepository.findById(newAddress.idAddress).orElse(null)
        newUserAddress.user = userRepository.findById(userAddress.userId).orElse(null)

        userAddressRepository.save(newUserAddress)

        return ResponseEntity(newUserAddress, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserAddressByUserId(@PathVariable("id") userId: Long): ResponseEntity<List<UserAddress>> {
        val userAddress = userAddressRepository.findAllByUserId(userId)
        return ResponseEntity(userAddress, HttpStatus.OK)
    }


    @DeleteMapping("/{id}")
    fun deleteUserAddressById(@PathVariable("id") userAddressId: Long): ResponseEntity<Any> {
        if (!userAddressRepository.existsById(userAddressId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userAddressRepository.deleteById(userAddressId)
        return ResponseEntity(Message("Successfully deleted"),HttpStatus.NO_CONTENT)
    }

}