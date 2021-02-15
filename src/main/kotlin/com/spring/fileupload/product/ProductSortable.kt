package com.spring.fileupload.product

enum class ProductSortable(val label: String) {
    INVOICE_NO(ProductInfo.INVOICE_NO),
    STOCK_CODE(ProductInfo.STOCK_CODE),
    DESCRIPTION(ProductInfo.DESCRIPTION),
    QUANTITY(ProductInfo.QUANTITY),
    INVOICE_DATE(ProductInfo.INVOICE_NO),
    UNIT_PRICE(ProductInfo.UNIT_PRICE),
    CUSTOMER_ID(ProductInfo.CUSTOMER_ID),
    COUNTRY(ProductInfo.COUNTRY);
}