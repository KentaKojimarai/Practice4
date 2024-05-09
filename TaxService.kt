package com.example.readcsv

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import repository.ProductRepository
import java.io.File

@Service
class TaxProductService(private val productRepository: ProductRepository) {

    // Foodの税率（8%）
    private val foodTaxRate = 0.08

    // それ以外の税率（10%）
    private val otherTaxRate = 0.1

    @Transactional
    fun readFromCSV(filePath: String): List<TaxProduct> {
        val products = mutableListOf<TaxProduct>()
        File(filePath).useLines { lines ->
            lines.drop(1).forEach { line ->
                val tokens = line.split(",")
                val category = tokens[0]
                val price = tokens[2].toInt()
                // カテゴリーごとに適切な税率を適用
                val taxRate = if (category == "Food") foodTaxRate else otherTaxRate
                // 税金を計算して新しい金額を設定
                val taxedPrice = (price * (1 + taxRate)).toInt()
                val product = TaxProduct(
                    category = category,
                    name = tokens[1],
                    price = taxedPrice,
                    origin = tokens[3]
                )
                products.add(product)
            }
        }
        return products
    }

    fun importCSVData(filePath: String) {
        val products = readFromCSV(filePath)
        productRepository.saveAll(products)
    }
}
