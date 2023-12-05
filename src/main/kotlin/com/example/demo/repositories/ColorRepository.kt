package com.example.demo.repositories

import com.example.demo.models.Color
import org.springframework.data.repository.CrudRepository

interface ColorRepository: CrudRepository<Color, Long> {}