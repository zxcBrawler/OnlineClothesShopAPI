package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "type_delivery")
data class TypeDelivery (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    var nameType : String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "typeDelivery")
    val deliveryInfo : List<DeliveryInfo> = arrayListOf()
)