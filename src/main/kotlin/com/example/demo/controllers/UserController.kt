package com.example.demo.controllers

import com.example.demo.models.User
import com.example.demo.models.dto.RegisterDTO
import com.example.demo.repositories.UserRepository
import com.example.demo.service.UserService
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/users"])
class UserController (@Autowired private val userRepository: UserRepository) {
    @GetMapping("")
    fun getAllUsers(): List<User> =
        userRepository.findAll().toList()

    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userRepository.save(user)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: Long): ResponseEntity<User> {
        val user = userRepository.findById(userId).orElse(null)
        return if (user != null) ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, @RequestBody user: RegisterDTO): ResponseEntity<User> {

        val existingUser = userRepository.findById(userId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedUser = existingUser.copy(
            username = user.username,
            passwordHash = BCrypt.hashpw(user.password, BCrypt.gensalt()),
            phoneNumber = user.phoneNumber,
            profilePhoto = user.profilePhoto,
            email = user.email,
            gender = user.gender)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long): ResponseEntity<User> {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userRepository.deleteById(userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}
