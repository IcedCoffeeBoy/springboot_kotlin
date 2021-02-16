package com.spring.fileupload.product

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification

class ProductSpecificationsBuilder {
    fun toSpecification(request: ProductFindRequest): Specification<Product>? {
        var spec = Specification.where<Product?>(null)
        request.fromDate?.let { spec = spec.and(ProductSpecifications.startInvoiceDate(it)) }
        request.toDate?.let { spec = spec.and(ProductSpecifications.endInvoiceDate(it)) }
        request.fromPrice?.let { spec = spec.and(ProductSpecifications.fromPrice(it)) }
        request.toPrice?.let { spec = spec.and(ProductSpecifications.toPrice(it)) }
        request.minQuantity?.let { spec = spec.and(ProductSpecifications.minQuantity(it)) }
        request.maxQuantity?.let { spec = spec.and(ProductSpecifications.maxQuantity(it)) }
        request.invoiceNo?.let { spec = spec.and(ProductSpecifications.byInvoiceNo(it)) }
        request.stockCode?.let { spec = spec.and(ProductSpecifications.byStockCode(it)) }
        request.customerID?.let { spec = spec.and(ProductSpecifications.byCustomerId(it)) }
        request.country?.let { spec = spec.and(ProductSpecifications.byCountry(it)) }
        request.description?.let { spec = spec.and(ProductSpecifications.descriptionsContains(it)) }
        return spec
    }

    fun toPageable(request: ProductFindRequest): Pageable {
        if (request.sortingProperty == null) {
            return PageRequest.of(request.pageNumber, request.pageSize, Sort.Direction.DESC, "invoiceDate")
        }
        val orders = arrayOfNulls<Sort.Order>(request.sortingProperty!!.size)
        for (i in request.sortingProperty!!.indices) {
            if (request.directions != null && i < request.directions!!.size) {
                orders[i] = Sort.Order(request.directions!!.get(i), request.sortingProperty!![i].label)
            } else {
                orders[i] = Sort.Order(Sort.Direction.ASC, request.sortingProperty!![i].label)
            }
        }
        return PageRequest.of(request.pageNumber, request.pageSize, Sort.by(*orders))
    }
}