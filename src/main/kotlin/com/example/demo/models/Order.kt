package com.example.demo.models

import com.example.demo.models.logs.OrderStatusLog
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idOrder: Long = 0,
    @Column(unique = true)
    var numberOrder: String = "",
    var timeOrder: String = "",
    var dateOrder: String = "",
    var sumOrder: String = "",

    @OneToOne
    @JoinColumn(name = "status_id")
    var currentStatus: StatusOrder = StatusOrder(),

    @JsonIgnore
    @OneToMany(mappedBy = "orderId")
    var orderId : List<OrderComposition> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    val userOrder : List<UserOrder> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    val orderStatusLogOrder : List<OrderStatusLog> = arrayListOf(),

    @ManyToOne
    var userCard: UserCard = UserCard(),
)
