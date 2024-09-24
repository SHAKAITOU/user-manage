package cn.caam.gs.domain.db.base.entity;

import java.math.BigDecimal;

public class MBill {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.bill_code
     *
     * @mbggenerated
     */
    private String billCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.bill_type
     *
     * @mbggenerated
     */
    private String billType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.bill_amount
     *
     * @mbggenerated
     */
    private BigDecimal billAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.invoice_title
     *
     * @mbggenerated
     */
    private String invoiceTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.credit_code
     *
     * @mbggenerated
     */
    private String creditCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.bill_date
     *
     * @mbggenerated
     */
    private String billDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.check_status
     *
     * @mbggenerated
     */
    private String checkStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.vote_method
     *
     * @mbggenerated
     */
    private String voteMethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_bill.bill_memo
     *
     * @mbggenerated
     */
    private String billMemo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.id
     *
     * @return the value of m_bill.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.id
     *
     * @param id the value for m_bill.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.user_id
     *
     * @return the value of m_bill.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.user_id
     *
     * @param userId the value for m_bill.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.bill_code
     *
     * @return the value of m_bill.bill_code
     *
     * @mbggenerated
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.bill_code
     *
     * @param billCode the value for m_bill.bill_code
     *
     * @mbggenerated
     */
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.bill_type
     *
     * @return the value of m_bill.bill_type
     *
     * @mbggenerated
     */
    public String getBillType() {
        return billType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.bill_type
     *
     * @param billType the value for m_bill.bill_type
     *
     * @mbggenerated
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.bill_amount
     *
     * @return the value of m_bill.bill_amount
     *
     * @mbggenerated
     */
    public BigDecimal getBillAmount() {
        return billAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.bill_amount
     *
     * @param billAmount the value for m_bill.bill_amount
     *
     * @mbggenerated
     */
    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.invoice_title
     *
     * @return the value of m_bill.invoice_title
     *
     * @mbggenerated
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.invoice_title
     *
     * @param invoiceTitle the value for m_bill.invoice_title
     *
     * @mbggenerated
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.credit_code
     *
     * @return the value of m_bill.credit_code
     *
     * @mbggenerated
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.credit_code
     *
     * @param creditCode the value for m_bill.credit_code
     *
     * @mbggenerated
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.bill_date
     *
     * @return the value of m_bill.bill_date
     *
     * @mbggenerated
     */
    public String getBillDate() {
        return billDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.bill_date
     *
     * @param billDate the value for m_bill.bill_date
     *
     * @mbggenerated
     */
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.check_status
     *
     * @return the value of m_bill.check_status
     *
     * @mbggenerated
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.check_status
     *
     * @param checkStatus the value for m_bill.check_status
     *
     * @mbggenerated
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.vote_method
     *
     * @return the value of m_bill.vote_method
     *
     * @mbggenerated
     */
    public String getVoteMethod() {
        return voteMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.vote_method
     *
     * @param voteMethod the value for m_bill.vote_method
     *
     * @mbggenerated
     */
    public void setVoteMethod(String voteMethod) {
        this.voteMethod = voteMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_bill.bill_memo
     *
     * @return the value of m_bill.bill_memo
     *
     * @mbggenerated
     */
    public String getBillMemo() {
        return billMemo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_bill.bill_memo
     *
     * @param billMemo the value for m_bill.bill_memo
     *
     * @mbggenerated
     */
    public void setBillMemo(String billMemo) {
        this.billMemo = billMemo;
    }
}