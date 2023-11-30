package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


@Entity
@Table(name = "card")
data class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val cardNum: String = "",
    val expDate: String = "",
    val cvv: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "card")
    val userCard : List<UserCard> = arrayListOf(),
)
