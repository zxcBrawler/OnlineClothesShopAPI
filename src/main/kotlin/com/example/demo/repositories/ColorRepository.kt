package com.example.demo.repositories

import com.example.demo.models.Colors
import org.springframework.data.repository.CrudRepository

interface ColorRepository: CrudRepository<Colors, Long> {}