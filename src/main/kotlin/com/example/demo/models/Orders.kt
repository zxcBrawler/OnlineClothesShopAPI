package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idOrder: Long = 0,
    @Column(unique = true)
    val numberOrder: String = "",
    val timeOrder: String = "",
    val dateOrder: String = "",
    val sumOrder: String = "",

    @OneToOne
    @JoinColumn(name = "status_id")
    val currentStatus: StatusOrder = StatusOrder(),
    @JsonIgnore
    @OneToMany(mappedBy = "orderId")
    var orderId : List<OrderComposition> = arrayListOf(),
    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    val userOrder : List<UserOrder> = arrayListOf(),

    @ManyToOne
    val userCard: UserCard = UserCard(),
)
