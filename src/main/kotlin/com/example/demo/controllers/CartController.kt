package com.example.demo.controllers

import com.example.demo.models.Cart
import com.example.demo.models.ClothesColors
import com.example.demo.models.ClothesSizeClothes
import com.example.demo.models.dto.CartDTO
import com.example.demo.models.dto.Message
import com.example.demo.repositories.CartRepository
import com.example.demo.repositories.ClothesColorsRepository
import com.example.demo.repositories.ClothesSizeClothesRepository
import com.example.demo.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cart")
class CartController (@Autowired private val cartRepository: CartRepository,
    @Autowired private val clothesColorsRepository: ClothesColorsRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val clothesSizeClothesRepository: ClothesSizeClothesRepository)
{
    @GetMapping("")
    fun getAllCart(): List<Cart> =
        cartRepository.findAll().toList()

    @GetMapping("/{id}")
    fun getCartByUserId(@PathVariable("id") userId: Long): List<Cart> {
        return cartRepository.findByUserId(userId).toList()
    }

    @GetMapping("/user/{userId}/clothes/{clothesId}/size/{sizeId}/color/{colorId}")
    fun checkIfItemExistsInCart(
        @PathVariable userId: Long,
        @PathVariable sizeId: Long,
        @PathVariable colorId: Long,
        @PathVariable clothesId : Long
    ): Any {

        val existingCart = cartRepository
            .findBySizeClothesSizeClothesIdAndColorClothesCartColorsColorIdAndSizeClothesClothesIdClothesAndUserId(sizeId, colorId, clothesId, userId)

        return existingCart?.id ?: -1
    }

    @Transactional
    @PostMapping("")
    fun createCart(cart: CartDTO): ResponseEntity<Cart> {
       val existingColorClothesCart = this.clothesColorsRepository.findById(cart.colorClothes.toLong()).orElse(null)
        val existingUser = this.userRepository.findById(cart.user.toLong()).orElse(null)
        val existingSizeClothes = this.clothesSizeClothesRepository.findById(cart.sizeClothes.toLong()).orElse(null)
        val newCart = Cart()
        newCart.user = existingUser
        newCart.quantity = cart.quantity
        newCart.sizeClothes  = existingSizeClothes
        newCart.colorClothesCart = existingColorClothesCart
        cartRepository.save(newCart)
        return ResponseEntity(newCart, HttpStatus.CREATED)
    }
    @Transactional
    @PutMapping("/{id}")
    fun updateCartById(@PathVariable("id") cartId: Long, updateType : Int): ResponseEntity<Any> {

        val existingCart = cartRepository.findById(cartId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        if (updateType == 1) {
            existingCart.quantity++
        }
        else {
            existingCart.quantity--
        }
        cartRepository.save(existingCart)
        return ResponseEntity(cartRepository.findAll(), HttpStatus.OK)
    }
    @Transactional
    @DeleteMapping("/{id}")
    fun deleteCartById(@PathVariable("id") cartId: Long): ResponseEntity<Any> {
        if (!cartRepository.existsById(cartId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        cartRepository.deleteById(cartId)
        return ResponseEntity(cartRepository.findAll(), HttpStatus.OK)
    }
    @Transactional
    @DeleteMapping("deleteAll/{id}")
    fun deleteAllByUserId(@PathVariable("id") userId: Long): ResponseEntity<Any> {
        cartRepository.deleteAllByUserId(userId)
        return ResponseEntity(Message("Successfully deleted"), HttpStatus.OK)
    }
}