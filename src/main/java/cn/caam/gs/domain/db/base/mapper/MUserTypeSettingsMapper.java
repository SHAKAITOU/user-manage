package cn.caam.gs.domain.db.base.mapper;

import cn.caam.gs.domain.db.base.entity.MUserTypeSettings;

public interface MUserTypeSettingsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_type_settings
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String userType);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_type_settings
     *
     * @mbggenerated
     */
    int insert(MUserTypeSettings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_type_settings
     *
     * @mbggenerated
     */
    int insertSelective(MUserTypeSettings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_type_settings
     *
     * @mbggenerated
     */
    MUserTypeSettings selectByPrimaryKey(String userType);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_type_settings
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MUserTypeSettings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_user_type_settings
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MUserTypeSettings record);
}