package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "delivery_info")
data class DeliveryInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    //DONE
    @ManyToOne
    var order: Order = Order(), // order id

    //DONE
    @ManyToOne
    var typeDelivery: TypeDelivery = TypeDelivery(),

    //DONE
    @ManyToOne
    var shopAddresses: ShopAddresses? = ShopAddresses(), // if type delivery is pickup then this value is not null

    //DONE
    @ManyToOne
    var addresses: Address? = Address(), // if type delivery is home delivery then this value is not null
)
