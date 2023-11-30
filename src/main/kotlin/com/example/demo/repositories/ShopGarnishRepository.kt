package com.example.demo.repositories

import com.example.demo.models.ShopGarnish
import org.springframework.data.repository.CrudRepository

interface ShopGarnishRepository: CrudRepository<ShopGarnish, Long> {

   fun getShopGarnishBySizeClothesGarnishSizeClothesIdAndColorClothesGarnishId (sizeId : Long, colorId : Long) : List<ShopGarnish>
}