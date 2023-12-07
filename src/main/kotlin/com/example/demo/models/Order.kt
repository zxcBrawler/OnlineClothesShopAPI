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

    @ManyToOne
    var currentStatus: StatusOrder = StatusOrder(),

    @JsonIgnore
    @OneToMany(mappedBy = "orderId", cascade = [CascadeType.ALL])
    var orderId : List<OrderComposition> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "orders", cascade = [CascadeType.ALL])
    val userOrder : List<UserOrder> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    val deliveryInfo : List<DeliveryInfo> = arrayListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    val orderStatusLogOrder : List<OrderStatusLog> = arrayListOf(),

    @ManyToOne
    var userCard: UserCard = UserCard(),
)
