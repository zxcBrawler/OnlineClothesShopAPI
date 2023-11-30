package com.example.demo.repositories

import com.example.demo.models.Cart
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CartRepository: CrudRepository<Cart, Long> {

    fun findByUserId(userId : Long) : List<Cart>

}