package com.example.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "delivery_info")
data class DeliveryInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    //DONE
    @OneToOne
    @JoinColumn(name = "order_id")
    val order: Order = Order(), // order id

    //DONE
    @OneToOne
    @JoinColumn(name = "type_delivery_id")
    val typeDelivery: TypeDelivery = TypeDelivery(),

    //DONE
    @ManyToOne
    val shopAddresses: ShopAddresses = ShopAddresses(), // if type delivery is pickup then this value is not null

    //DONE
    @ManyToOne
    val addresses: Address = Address(), // if type delivery is home delivery then this value is not null
)
