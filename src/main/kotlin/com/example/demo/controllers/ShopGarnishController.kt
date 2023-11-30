package com.example.demo.controllers

import com.example.demo.models.ShopGarnish
import com.example.demo.repositories.ShopGarnishRepository
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/shopGarnish")
class ShopGarnishController (@Autowired private val shopGarnishRepository: ShopGarnishRepository)  {
    @GetMapping("")
    fun getAllShopGarnish(): List<ShopGarnish> =
        shopGarnishRepository.findAll().toList()

    @PostMapping("")
    fun createShopGarnish(@RequestBody shopGarnish: ShopGarnish): ResponseEntity<ShopGarnish> {
        val createdShopGarnish = shopGarnishRepository.save(shopGarnish)
        return ResponseEntity(createdShopGarnish, HttpStatus.CREATED)
    }

    @GetMapping("/{colorId}/{sizeId}")
    fun getShopGarnishById(@PathVariable("colorId") colorId: Long, @PathVariable("sizeId") sizeId: Long): ResponseEntity<Any> {
       val existingShopGarnish = shopGarnishRepository.getShopGarnishBySizeClothesGarnishSizeClothesIdAndColorClothesGarnishId(sizeId, colorId)
        return ResponseEntity(existingShopGarnish, HttpStatus.OK)
    }



    @DeleteMapping("/{id}")
    fun deleteShopGarnishById(@PathVariable("id") shopGarnishId: Long): ResponseEntity<ShopGarnish> {
        if (!shopGarnishRepository.existsById(shopGarnishId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        shopGarnishRepository.deleteById(shopGarnishId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}