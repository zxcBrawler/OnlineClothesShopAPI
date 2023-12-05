package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


@Entity
@Table(name = "card")
data class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var cardNum: String = "",
    var expDate: String = "",
    var cvv: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "card", cascade = [CascadeType.ALL])
    val userCard : List<UserCard> = arrayListOf(),
)
