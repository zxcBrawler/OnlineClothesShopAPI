package com.example.demo.controllers

import com.example.demo.models.Colors
import com.example.demo.repositories.ColorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/colors")
class ColorController (@Autowired private val colorRepository: ColorRepository)  {
    @GetMapping("")
    fun getAllColors(): List<Colors> =
        colorRepository.findAll().toList()

    @PostMapping("")
    fun createColors(@RequestBody colors: Colors): ResponseEntity<Colors> {
        val createdColors = colorRepository.save(colors)
        return ResponseEntity(createdColors, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getColorsById(@PathVariable("id") colorsId: Long): ResponseEntity<Colors> {
        val colors = colorRepository.findById(colorsId).orElse(null)
        return if (colors != null) ResponseEntity(colors, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateColorsById(@PathVariable("id") colorsId: Long, @RequestBody colors: Colors): ResponseEntity<Colors> {

        val existingColors = colorRepository.findById(colorsId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedColors = existingColors.copy(
            nameColor = colors.nameColor)
        colorRepository.save(updatedColors)
        return ResponseEntity(updatedColors, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteColorsById(@PathVariable("id") colorsId: Long): ResponseEntity<Colors> {
        if (!colorRepository.existsById(colorsId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        colorRepository.deleteById(colorsId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}