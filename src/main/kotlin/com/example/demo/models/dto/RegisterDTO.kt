package com.example.demo.models.dto

import com.example.demo.models.CategoryClothes

class RegisterDTO {
    var password = ""
    var gender = CategoryClothes(0,"")
    var email = ""
    var phoneNumber = ""
    var profilePhoto = ""
    var username = ""
}