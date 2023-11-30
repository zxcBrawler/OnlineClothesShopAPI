package com.example.demo.repositories

import com.example.demo.models.TypeDelivery
import org.springframework.data.repository.CrudRepository

interface TypeDeliveryRepository : CrudRepository<TypeDelivery, Long> {
}