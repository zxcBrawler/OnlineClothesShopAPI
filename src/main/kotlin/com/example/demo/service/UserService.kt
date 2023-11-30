package com.example.demo.service

import com.example.demo.models.User
import com.example.demo.repositories.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository) {
    fun saveUser(user : User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User?{
        return this.userRepository.findByEmail(email)
    }
    fun setPassword(user: User) : String {
        user.passwordHash = BCrypt.hashpw(user.password, BCrypt.gensalt())
        return user.passwordHash
    }
    fun comparePassword(password: String, user: User): Boolean{
        return BCrypt.checkpw(password, user.passwordHash)
    }
}