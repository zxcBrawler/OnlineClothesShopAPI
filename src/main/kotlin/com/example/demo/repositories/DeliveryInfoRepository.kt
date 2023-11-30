package com.example.demo.repositories

import com.example.demo.models.DeliveryInfo
import org.springframework.data.repository.CrudRepository

interface DeliveryInfoRepository : CrudRepository<DeliveryInfo, Long> {
}