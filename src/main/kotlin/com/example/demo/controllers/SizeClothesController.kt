package com.example.demo.controllers

import com.example.demo.models.SizeClothes
import com.example.demo.repositories.SizeClothesRepository
import com.example.demo.repositories.UserOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sizeClothes")
class SizeClothesController(@Autowired private val sizeClothesRepository: SizeClothesRepository)  {
    @GetMapping("")
    fun getAllSizeClothes(): List<SizeClothes> =
        sizeClothesRepository.findAll().toList()

    @PostMapping("")
    fun createSizeClothes(@RequestBody sizeClothes: SizeClothes): ResponseEntity<SizeClothes> {
        val createdSizeClothes = sizeClothesRepository.save(sizeClothes)
        return ResponseEntity(createdSizeClothes, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getSizeClothesById(@PathVariable("id") sizeClothesId: Long): ResponseEntity<SizeClothes> {
        val sizeClothes = sizeClothesRepository.findById(sizeClothesId).orElse(null)
        return if (sizeClothes != null) ResponseEntity(sizeClothes, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateSizeClothesById(@PathVariable("id") sizeClothesId: Long, @RequestBody sizeClothes: SizeClothes): ResponseEntity<SizeClothes> {

        val existingSizeClothes = sizeClothesRepository.findById(sizeClothesId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedSizeClothes = existingSizeClothes.copy(
            nameSize = sizeClothes.nameSize)
        sizeClothesRepository.save(updatedSizeClothes)
        return ResponseEntity(updatedSizeClothes, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteSizeClothesById(@PathVariable("id") sizeClothesId: Long): ResponseEntity<SizeClothes> {
        if (!sizeClothesRepository.existsById(sizeClothesId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        sizeClothesRepository.deleteById(sizeClothesId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}