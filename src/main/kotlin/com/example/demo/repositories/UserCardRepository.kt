package com.example.demo.repositories

import com.example.demo.models.UserCard
import org.springframework.data.repository.CrudRepository

interface UserCardRepository: CrudRepository<UserCard, Long> {
}