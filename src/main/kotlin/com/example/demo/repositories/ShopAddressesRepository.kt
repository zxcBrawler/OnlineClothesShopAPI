package com.example.demo.repositories

import com.example.demo.models.ShopAddresses
import org.springframework.data.repository.CrudRepository

interface ShopAddressesRepository: CrudRepository<ShopAddresses, Long> {
}