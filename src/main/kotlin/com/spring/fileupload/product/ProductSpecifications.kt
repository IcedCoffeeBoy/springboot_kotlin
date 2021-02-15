package com.spring.fileupload.product

import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

/**
 * List of specification for product
 */
class ProductSpecifications {
    companion object {
        fun startInvoiceDate(date: LocalDate): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.greaterThanOrEqualTo(root.get(ProductInfo.INVOICE_DATE), LocalDateTime.of(date, LocalTime.MIN))
            }
        }

        fun endInvoiceDate(date: LocalDate): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.lessThanOrEqualTo(root.get(ProductInfo.INVOICE_DATE), LocalDateTime.of(date, LocalTime.MAX))
            }
        }

        fun fromPrice(price: Double): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.greaterThanOrEqualTo(root.get(ProductInfo.UNIT_PRICE), price)
            }
        }

        fun toPrice(price: Double): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.lessThanOrEqualTo(root.get(ProductInfo.UNIT_PRICE), price)
            }
        }

        fun minQuantity(quantity: Long): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.greaterThanOrEqualTo(root.get(ProductInfo.QUANTITY), quantity)
            }
        }

        fun maxQuantity(quantity: Long): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.lessThanOrEqualTo(root.get(ProductInfo.QUANTITY), quantity)
            }
        }

        fun byInvoiceNo(invoiceNo: String): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.equal(root.get<Any>(ProductInfo.INVOICE_NO), invoiceNo)
            }
        }

        fun byStockCode(stockCode: String): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.equal(root.get<Any>(ProductInfo.STOCK_CODE), stockCode)
            }
        }

        fun byCustomerId(customerId: String): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.equal(root.get<Any>(ProductInfo.CUSTOMER_ID), customerId)
            }
        }

        fun byCountry(country: String): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.like(cb.lower(root.get(ProductInfo.COUNTRY)), "%" + country.toLowerCase() + "%")
            }
        }

        fun descriptionsContains(keywords: String): Specification<Product?> {
            return Specification { root: Root<Product?>, _: CriteriaQuery<*>?, cb: CriteriaBuilder ->
                cb.like(cb.lower(root.get(ProductInfo.DESCRIPTION)), "%" + keywords.toLowerCase() + "%")
            }
        }
    }
}
