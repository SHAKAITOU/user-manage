package cn.caam.gs.domain.db.base.mapper;

import cn.caam.gs.domain.db.base.entity.MUserExtend;

public interface MUserExtendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    int insert(MUserExtend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    int insertSelective(MUserExtend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    MUserExtend selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MUserExtend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(MUserExtend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_extend
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MUserExtend record);
}