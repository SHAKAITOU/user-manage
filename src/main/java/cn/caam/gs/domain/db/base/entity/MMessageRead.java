package cn.caam.gs.domain.db.base.entity;

public class MMessageRead {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_message_read.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_message_read.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_message_read.id
     *
     * @return the value of m_message_read.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_message_read.id
     *
     * @param id the value for m_message_read.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_message_read.user_id
     *
     * @return the value of m_message_read.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_message_read.user_id
     *
     * @param userId the value for m_message_read.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}