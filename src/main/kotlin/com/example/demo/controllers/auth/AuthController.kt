package com.example.demo.controllers.auth

import com.example.demo.models.User
import com.example.demo.models.dto.LoginDTO
import com.example.demo.models.dto.LoginResponse
import com.example.demo.models.dto.Message
import com.example.demo.models.dto.RegisterDTO
import com.example.demo.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/api/auth"])
class AuthController (private val userService: UserService) {

    @PostMapping("/register")
    fun register(body: RegisterDTO) : ResponseEntity<User> {
        val user = User()
        user.username = body.username
        user.password = body.password
        user.email = body.email
        user.phoneNumber = body.phoneNumber
        user.profilePhoto = body.profilePhoto
        user.gender = body.gender
        user.passwordHash = userService.setPassword(user)
        return ResponseEntity.ok(this.userService.saveUser(user))
    }

    @PostMapping("/login")
    fun login(body: LoginDTO, response: HttpServletResponse) : ResponseEntity<Any>{
        val loginResponse = LoginResponse()
        val user = this.userService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("user not found"))

        if(!this.userService.comparePassword(body.password, user)){
            return ResponseEntity.badRequest().body(Message("Invalid password"))
        }
       val issuer = user.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) //1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        loginResponse.user = user
        loginResponse.accessToken = jwt

        response.addCookie(cookie)
        return ResponseEntity.ok(loginResponse)
    }
}