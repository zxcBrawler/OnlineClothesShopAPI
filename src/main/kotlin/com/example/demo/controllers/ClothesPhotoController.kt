package com.example.demo.controllers
import com.example.demo.models.ClothesPhoto
import com.example.demo.repositories.ClothesPhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clothesPhotos")
class ClothesPhotoController(@Autowired private val clothesPhotoRepository: ClothesPhotoRepository)  {
    @GetMapping("")
    fun getAllClothesPhoto(): List<ClothesPhoto> =
        clothesPhotoRepository.findAll().toList()

    @PostMapping("")
    fun createClothesPhoto(@RequestBody clothesPhoto: ClothesPhoto): ResponseEntity<ClothesPhoto> {
        val createdClothesColors = clothesPhotoRepository.save(clothesPhoto)
        return ResponseEntity(createdClothesColors, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getClothesPhotoById(@PathVariable("id") clothesColorsId: Long): ResponseEntity<ClothesPhoto> {
        val clothesColors = clothesPhotoRepository.findById(clothesColorsId).orElse(null)
        return if (clothesColors != null) ResponseEntity(clothesColors, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateClothesPhotoById(@PathVariable("id") clothesPhotoId: Long, @RequestBody clothesPhoto: ClothesPhoto): ResponseEntity<ClothesPhoto> {

        val existingClothesPhoto = clothesPhotoRepository.findById(clothesPhotoId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedClothesPhoto = existingClothesPhoto.copy(
            photoAddress = clothesPhoto.photoAddress
        )
        clothesPhotoRepository.save(updatedClothesPhoto)
        return ResponseEntity(updatedClothesPhoto, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteClothesPhotoById(@PathVariable("id") clothesColorsId: Long): ResponseEntity<ClothesPhoto> {
        if (!clothesPhotoRepository.existsById(clothesColorsId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        clothesPhotoRepository.deleteById(clothesColorsId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}