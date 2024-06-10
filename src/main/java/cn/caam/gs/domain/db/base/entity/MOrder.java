package cn.caam.gs.domain.db.base.entity;

import java.math.BigDecimal;

public class MOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.pay_path
     *
     * @mbggenerated
     */
    private String payPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.order_method
     *
     * @mbggenerated
     */
    private String orderMethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.order_type
     *
     * @mbggenerated
     */
    private String orderType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.pay_type
     *
     * @mbggenerated
     */
    private String payType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.order_amount
     *
     * @mbggenerated
     */
    private BigDecimal orderAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.pay_amount
     *
     * @mbggenerated
     */
    private BigDecimal payAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.pay_date
     *
     * @mbggenerated
     */
    private String payDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.check_date
     *
     * @mbggenerated
     */
    private String checkDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.check_status
     *
     * @mbggenerated
     */
    private String checkStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.refund_date
     *
     * @mbggenerated
     */
    private String refundDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.refund_status
     *
     * @mbggenerated
     */
    private String refundStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.bill_status
     *
     * @mbggenerated
     */
    private String billStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.memo
     *
     * @mbggenerated
     */
    private String memo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.id
     *
     * @return the value of m_order.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.id
     *
     * @param id the value for m_order.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.user_id
     *
     * @return the value of m_order.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.user_id
     *
     * @param userId the value for m_order.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.pay_path
     *
     * @return the value of m_order.pay_path
     *
     * @mbggenerated
     */
    public String getPayPath() {
        return payPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.pay_path
     *
     * @param payPath the value for m_order.pay_path
     *
     * @mbggenerated
     */
    public void setPayPath(String payPath) {
        this.payPath = payPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.order_method
     *
     * @return the value of m_order.order_method
     *
     * @mbggenerated
     */
    public String getOrderMethod() {
        return orderMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.order_method
     *
     * @param orderMethod the value for m_order.order_method
     *
     * @mbggenerated
     */
    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.order_type
     *
     * @return the value of m_order.order_type
     *
     * @mbggenerated
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.order_type
     *
     * @param orderType the value for m_order.order_type
     *
     * @mbggenerated
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.pay_type
     *
     * @return the value of m_order.pay_type
     *
     * @mbggenerated
     */
    public String getPayType() {
        return payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.pay_type
     *
     * @param payType the value for m_order.pay_type
     *
     * @mbggenerated
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.order_amount
     *
     * @return the value of m_order.order_amount
     *
     * @mbggenerated
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.order_amount
     *
     * @param orderAmount the value for m_order.order_amount
     *
     * @mbggenerated
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.pay_amount
     *
     * @return the value of m_order.pay_amount
     *
     * @mbggenerated
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.pay_amount
     *
     * @param payAmount the value for m_order.pay_amount
     *
     * @mbggenerated
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.pay_date
     *
     * @return the value of m_order.pay_date
     *
     * @mbggenerated
     */
    public String getPayDate() {
        return payDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.pay_date
     *
     * @param payDate the value for m_order.pay_date
     *
     * @mbggenerated
     */
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.check_date
     *
     * @return the value of m_order.check_date
     *
     * @mbggenerated
     */
    public String getCheckDate() {
        return checkDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.check_date
     *
     * @param checkDate the value for m_order.check_date
     *
     * @mbggenerated
     */
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.check_status
     *
     * @return the value of m_order.check_status
     *
     * @mbggenerated
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.check_status
     *
     * @param checkStatus the value for m_order.check_status
     *
     * @mbggenerated
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.refund_date
     *
     * @return the value of m_order.refund_date
     *
     * @mbggenerated
     */
    public String getRefundDate() {
        return refundDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.refund_date
     *
     * @param refundDate the value for m_order.refund_date
     *
     * @mbggenerated
     */
    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.refund_status
     *
     * @return the value of m_order.refund_status
     *
     * @mbggenerated
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.refund_status
     *
     * @param refundStatus the value for m_order.refund_status
     *
     * @mbggenerated
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.bill_status
     *
     * @return the value of m_order.bill_status
     *
     * @mbggenerated
     */
    public String getBillStatus() {
        return billStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.bill_status
     *
     * @param billStatus the value for m_order.bill_status
     *
     * @mbggenerated
     */
    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_order.memo
     *
     * @return the value of m_order.memo
     *
     * @mbggenerated
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_order.memo
     *
     * @param memo the value for m_order.memo
     *
     * @mbggenerated
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}