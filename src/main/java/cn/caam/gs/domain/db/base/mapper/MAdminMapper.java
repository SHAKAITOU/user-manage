package cn.caam.gs.domain.db.base.mapper;

import cn.caam.gs.domain.db.base.entity.MAdmin;

public interface MAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_admin
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_admin
     *
     * @mbggenerated
     */
    int insert(MAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_admin
     *
     * @mbggenerated
     */
    int insertSelective(MAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_admin
     *
     * @mbggenerated
     */
    MAdmin selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MAdmin record);
}