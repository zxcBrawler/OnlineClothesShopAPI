package com.example.demo.controllers

import com.example.demo.models.*
import com.example.demo.models.dto.Message
import com.example.demo.models.dto.OrderDTO
import com.example.demo.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@RestController
@RequestMapping("/api/userOrder")
class UserOrderController(
    @Autowired private val userOrderRepository: UserOrderRepository,
    @Autowired private val statusOrderRepository: StatusOrderRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val orderRepository: OrdersRepository,
    @Autowired private val userCardRepository: UserCardRepository,
    @Autowired private val orderCompositionRepository: OrderCompositionRepository,
    @Autowired private val addressesRepository: AddressRepository,
    @Autowired private val shopAddressesRepository: ShopAddressesRepository,
    @Autowired private val typeDeliveryRepository: TypeDeliveryRepository,
    @Autowired private val cartRepository: CartRepository,
    @Autowired private val deliveryInfoRepository: DeliveryInfoRepository,
    @Autowired private val clothesRepository: ClothesRepository,
    @Autowired private val colorRepository: ColorRepository,
    @Autowired private val sizeClothesRepository: SizeClothesRepository,
    ) {
    @GetMapping("")
    fun getAllUserOrders(): List<UserOrder> =
        userOrderRepository.findAll().toList()

    @PostMapping("")
    fun createUserOrder(userOrder: OrderDTO): ResponseEntity<Any> {
        val newUserOrder = UserOrder()
        val order = Order()
        var orderComp = OrderComposition()
        val deliveryInfo = DeliveryInfo()
        val generateNumber = Random().nextInt(1,99999)
        val generatedOrderNumber = generateNumber.toString() + Calendar.YEAR.toString() + Calendar.MONTH.toString() + Calendar.DAY_OF_YEAR.toString()
        val currentStatus = statusOrderRepository.findById(1).orElse(null)
        val userCard = userCardRepository.findById(userOrder.userCardId).orElse(null)
        val currentUser = userRepository.findById(userCard.user.id).orElse(null)

        order.numberOrder = generatedOrderNumber
        order.dateOrder = LocalDate.now().toString()
        order.timeOrder = LocalTime.now().toString()
        order.currentStatus = currentStatus
        order.sumOrder = userOrder.sumOrder
        order.userCard = userCard

        orderRepository.save(order)

        for (item in userOrder.orderComp){
            val clothesQuantity = cartRepository.findById(item).orElse(null)
            orderComp.quantity = clothesQuantity.quantity
            orderComp.clothesComp = clothesRepository.findById(clothesQuantity.colorClothesCart.clothes.idClothes).orElse(null)
            orderComp.orderId = orderRepository.findById(order.idOrder).orElse(null)
            orderComp.colorClothes = colorRepository.findById(clothesQuantity.colorClothesCart.colors.colorId).orElse(null)
            orderComp.sizeClothes = sizeClothesRepository.findById(clothesQuantity.sizeClothes.sizeClothes.id).orElse(null)
            orderCompositionRepository.save(orderComp)
            orderComp = OrderComposition()
        }

        newUserOrder.user = currentUser
        newUserOrder.orders = orderRepository.findById(order.idOrder).orElse(null)

        userOrderRepository.save(newUserOrder)

        deliveryInfo.order = orderRepository.findById(order.idOrder).orElse(null)
        deliveryInfo.typeDelivery = typeDeliveryRepository.findById(userOrder.typeDelivery).orElse(null)
        if (userOrder.typeDelivery.toInt() == 1){
            deliveryInfo.shopAddresses = userOrder.shopAddress?.let { shopAddressesRepository.findById(it).orElse(null) }!!
            deliveryInfo.addresses = null
        }
        else {
            deliveryInfo.addresses = userOrder.userAddress?.let { addressesRepository.findById(it).orElse(null) }!!
            deliveryInfo.shopAddresses = null
        }

        deliveryInfoRepository.save(deliveryInfo)

        return ResponseEntity(Message("Order formed"), HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAllUserOrderById(@PathVariable("id") userId: Long): ResponseEntity<List<UserOrder>> {
        val userOrder = userOrderRepository.findAllByUserId(userId)

        return if (userOrder != null) ResponseEntity(userOrder, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    fun deleteUserOrderById(@PathVariable("id") userOrderId: Long): ResponseEntity<UserOrder> {
        if (!userOrderRepository.existsById(userOrderId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userOrderRepository.deleteById(userOrderId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}