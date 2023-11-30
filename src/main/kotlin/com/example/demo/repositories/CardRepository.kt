package com.example.demo.repositories

import com.example.demo.models.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository: CrudRepository<Card, Long> {
}