package com.example.demo.controllers

import com.example.demo.models.Card
import com.example.demo.models.Cart
import com.example.demo.models.UserCard
import com.example.demo.repositories.UserCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/userCard")
class UserCardController ( @Autowired private val userCardRepository: UserCardRepository) {

    @GetMapping("")
    fun getAllCards(): List<UserCard> =
        userCardRepository.findAll().toList()

    @GetMapping("/{id}")
    fun getUserCardById(@PathVariable("id") cardId: Long): ResponseEntity<UserCard> {
        val card = userCardRepository.findById(cardId).orElse(null)
        return if (card != null) ResponseEntity(card, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping("")
    fun createUserCard(@RequestBody userCard: UserCard): ResponseEntity<UserCard> {
        val createdCard = userCardRepository.save(userCard)
        return ResponseEntity(createdCard, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteUserCartById(@PathVariable("id") userCardId: Long): ResponseEntity<UserCard> {
        if (!userCardRepository.existsById(userCardId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userCardRepository.deleteById(userCardId.toLong())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}