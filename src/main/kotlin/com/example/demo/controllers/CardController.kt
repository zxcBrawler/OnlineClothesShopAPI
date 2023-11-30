package com.example.demo.controllers

import com.example.demo.models.Card
import com.example.demo.models.Cart
import com.example.demo.repositories.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.jvm.optionals.toList

@RestController
@RequestMapping("/api/card")
class CardController(@Autowired private val cardRepository: CardRepository) {
    @GetMapping("")
    fun getAllCards(): List<Card> =
        cardRepository.findAll().toList()

    @GetMapping("/{id}")
    fun getCardById(@PathVariable("id") cardId: Long): ResponseEntity<Card> {
        val card = cardRepository.findById(cardId).orElse(null)
        return if (card != null) ResponseEntity(card, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping("")
    fun createCard(@RequestBody card: Card): ResponseEntity<Card> {
        val createdCard = cardRepository.save(card)
        return ResponseEntity(createdCard, HttpStatus.CREATED)
    }
    @PutMapping("/{id}")
    fun updateCardById(@PathVariable("id") cardId: Long, @RequestBody card: Card): ResponseEntity<Card> {

        val existingCard = cardRepository.findById(cardId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedCard = existingCard.copy(
            cardNum = card.cardNum,
            expDate = card.expDate,
            cvv = card.cvv,
        )
        cardRepository.save(updatedCard)
        return ResponseEntity(updatedCard, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCartById(@PathVariable("id") cartId: Long): ResponseEntity<Cart> {
        if (!cardRepository.existsById(cartId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        cardRepository.deleteById(cartId.toLong())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}