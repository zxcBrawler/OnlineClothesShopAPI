package com.example.demo.models

import jakarta.persistence.*


@Entity
@Table(name = "user_card")
data class UserCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    //DONE
    @ManyToOne
    val user: User = User(),

    //DONE
    @ManyToOne
    val card: Card = Card(),
)
