package com.example.demo.repositories

import com.example.demo.models.Orders
import org.springframework.data.repository.CrudRepository

interface OrdersRepository: CrudRepository<Orders, Long> {}