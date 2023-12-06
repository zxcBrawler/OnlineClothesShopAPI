package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class ShopAddresses(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val shopAddressId: Long = 0,
    val shopAddressDirection: String = "", // where shop located exactly
    val shopMetro: String = "", //closest metro station
    val contactNumber: String = "", // contact info
    val latitude: String = "",
    val longitude: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "shopAddresses")
    var addressList : List<DeliveryInfo> = arrayListOf(),
    @JsonIgnore
    @OneToMany(mappedBy = "shopAddressesGarnish")
    var shopAddressesGarnish : List<ShopGarnish> = arrayListOf()
)
