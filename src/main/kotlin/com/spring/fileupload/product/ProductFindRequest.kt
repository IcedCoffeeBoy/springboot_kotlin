package com.spring.fileupload.product

import com.fasterxml.jackson.annotation.JsonAutoDetect
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.Min

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class ProductFindRequest(
    var pageNumber: @Min(value = 0) Int = 0,
    var pageSize: @Min(value = 1) Int = 10,
    var sortingProperty: Array<ProductSortable>? = null,
    var directions: Array<Sort.Direction>? = null,

    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    var fromDate: LocalDate?,

    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    var toDate: LocalDate?,
    var fromPrice: Double? = null,
    var toPrice: Double? = null,
    var minQuantity: Long? = null,
    var maxQuantity: Long? = null,
    var invoiceNo: String? = null,
    var stockCode: String? = null,
    var customerID: String? = null,
    var country: String? = null,
    var description: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductFindRequest

        if (pageNumber != other.pageNumber) return false
        if (pageSize != other.pageSize) return false
        if (sortingProperty != null) {
            if (other.sortingProperty == null) return false
            if (!sortingProperty.contentEquals(other.sortingProperty)) return false
        } else if (other.sortingProperty != null) return false
        if (directions != null) {
            if (other.directions == null) return false
            if (!directions.contentEquals(other.directions)) return false
        } else if (other.directions != null) return false
        if (fromDate != other.fromDate) return false
        if (toDate != other.toDate) return false
        if (fromPrice != other.fromPrice) return false
        if (toPrice != other.toPrice) return false
        if (minQuantity != other.minQuantity) return false
        if (maxQuantity != other.maxQuantity) return false
        if (invoiceNo != other.invoiceNo) return false
        if (stockCode != other.stockCode) return false
        if (customerID != other.customerID) return false
        if (country != other.country) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pageNumber
        result = 31 * result + pageSize
        result = 31 * result + (sortingProperty?.contentHashCode() ?: 0)
        result = 31 * result + (directions?.contentHashCode() ?: 0)
        result = 31 * result + (fromDate?.hashCode() ?: 0)
        result = 31 * result + (toDate?.hashCode() ?: 0)
        result = 31 * result + (fromPrice?.hashCode() ?: 0)
        result = 31 * result + (toPrice?.hashCode() ?: 0)
        result = 31 * result + (minQuantity?.hashCode() ?: 0)
        result = 31 * result + (maxQuantity?.hashCode() ?: 0)
        result = 31 * result + (invoiceNo?.hashCode() ?: 0)
        result = 31 * result + (stockCode?.hashCode() ?: 0)
        result = 31 * result + (customerID?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }
}