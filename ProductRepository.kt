package repository

import com.example.readcsv.TaxProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<TaxProduct, Long>{

}