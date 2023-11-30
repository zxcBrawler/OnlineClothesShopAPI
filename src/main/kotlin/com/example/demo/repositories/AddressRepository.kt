package com.example.demo.repositories

import com.example.demo.models.Address
import org.springframework.data.repository.CrudRepository

interface AddressRepository: CrudRepository<Address, Long> {}