package com.example.demo.repositories

import com.example.demo.models.Cart
import com.example.demo.models.ClothesColors
import com.example.demo.models.ClothesSizeClothes
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CartRepository: CrudRepository<Cart, Long> {

    fun findByUserId(userId : Long) : List<Cart>

    fun findBySizeClothesSizeClothesIdAndColorClothesCartColorsColorIdAndSizeClothesClothesIdClothesAndUserId
                (sizeId: Long?, colorId : Long?, clothesId : Long?, userId : Long?) : Cart?


}