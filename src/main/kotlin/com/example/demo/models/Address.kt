package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAddress: Long = 0,
    var city : String = "",
    var nameAddress: String = "",
    var directionAddress: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "addresses")
    var shopAddressList : List<DeliveryInfo> = arrayListOf(),
    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL])
    var userAddressList : List<UserAddress> = arrayListOf()


)
