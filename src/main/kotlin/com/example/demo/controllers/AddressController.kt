package com.example.demo.controllers

import com.example.demo.models.Address
import com.example.demo.models.User
import com.example.demo.repositories.AddressRepository
import com.example.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/address")
class AddressController(@Autowired private val addressRepository: AddressRepository) {

    @GetMapping("")
    fun getAllAddresses(): List<Address> =
        addressRepository.findAll().toList()
    @GetMapping("/{id}")
    fun getAddressById(@PathVariable("id") addressId: Long): ResponseEntity<Address> {
        val address = addressRepository.findById(addressId).orElse(null)
        return if (address != null) ResponseEntity(address, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping("")
    fun createAddress(@RequestBody address: Address): ResponseEntity<Address> {
        val createdAddress = addressRepository.save(address)
        return ResponseEntity(createdAddress, HttpStatus.CREATED)
    }
    @PutMapping("/{id}")
    fun updateAddressById(@PathVariable("id") addressId: Long, @RequestBody address: Address): ResponseEntity<Address> {

        val existingAddress = addressRepository.findById(addressId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedAddress = existingAddress.copy(
            nameAddress = address.nameAddress,
            directionAddress = address.directionAddress,
            )
        addressRepository.save(updatedAddress)
        return ResponseEntity(updatedAddress, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") addressId: Long): ResponseEntity<Address> {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        addressRepository.deleteById(addressId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}