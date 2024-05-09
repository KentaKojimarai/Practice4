package com.example.readcsv

import jakarta.persistence.*

@Entity
@Table(name = "taxproduct", schema = "data")
data class TaxProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val category: String,
    val name: String,
    val price: Int,
    val origin: String
)