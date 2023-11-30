package com.example.demo.repositories

import com.example.demo.models.StatusOrder
import org.springframework.data.repository.CrudRepository

interface StatusOrderRepository: CrudRepository<StatusOrder, Long> {}