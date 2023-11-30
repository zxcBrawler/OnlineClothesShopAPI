package com.example.demo.repositories

import com.example.demo.models.UserAddress
import org.springframework.data.repository.CrudRepository

interface UserAddressRepository: CrudRepository<UserAddress, Long> {
}