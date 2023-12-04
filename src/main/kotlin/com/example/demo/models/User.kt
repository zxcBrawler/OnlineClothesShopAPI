package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "user")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(unique = true)
    var username: String = "",

    var passwordHash: String = "",
    @Column(unique = true)
    var email: String = "",
    @Column(unique = true)
    var phoneNumber: String = "",

    var profilePhoto: String = "",

    @OneToOne
    @JoinColumn(name = "gender_id")
    var gender: CategoryClothes = CategoryClothes(),

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val cart : List<Cart> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val userAddress : List<UserAddress> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val userCard : List<UserCard> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val userOrder : List<UserOrder> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    val userLog : List<UserLogs> = arrayListOf(),
)

