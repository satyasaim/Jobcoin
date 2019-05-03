package com.media3.jobcoin.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HrListData {
    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("pay_id")
    @Expose
    private String payId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("txn_id")
    @Expose
    private String txnId;
    @SerializedName("payment_gross")
    @Expose
    private String paymentGross;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("payer_email")
    @Expose
    private String payerEmail;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("product_status")
    @Expose
    private String productStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("pay_date")
    @Expose
    private String payDate;
    @SerializedName("active_date")
    @Expose
    private String activeDate;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getPaymentGross() {
        return paymentGross;
    }

    public void setPaymentGross(String paymentGross) {
        this.paymentGross = paymentGross;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }
}
