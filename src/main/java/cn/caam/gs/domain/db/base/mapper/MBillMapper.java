package cn.caam.gs.domain.db.base.mapper;

import cn.caam.gs.domain.db.base.entity.MBill;

public interface MBillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_bill
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_bill
     *
     * @mbggenerated
     */
    int insert(MBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_bill
     *
     * @mbggenerated
     */
    int insertSelective(MBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_bill
     *
     * @mbggenerated
     */
    MBill selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_bill
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_bill
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MBill record);
}