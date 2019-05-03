package com.media3.jobcoin.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentData {
    @SerializedName("mkey")
    @Expose
    private String mkey;
    @SerializedName("txnid")
    @Expose
    private String txnid;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("current_amount")
    @Expose
    private String currentAmount;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("productinfo")
    @Expose
    private String productinfo;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("mailid")
    @Expose
    private String mailid;
    @SerializedName("phoneno")
    @Expose
    private String phoneno;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("membershipName")
    @Expose
    private String membershipName;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("surl")
    @Expose
    private String surl;
    @SerializedName("furl")
    @Expose
    private String furl;
    @SerializedName("cancel")
    @Expose
    private String cancel;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
