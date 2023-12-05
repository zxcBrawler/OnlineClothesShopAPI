package com.example.demo.repositories

import com.example.demo.models.Card
import com.example.demo.models.UserCard
import org.springframework.data.repository.CrudRepository

interface UserCardRepository: CrudRepository<UserCard, Long> {

    fun findByUserId(userId : Long) : List<UserCard>

    fun findByCardId (cardId : Long) : UserCard

    fun deleteByCardId (cardId : Long) : Any
}