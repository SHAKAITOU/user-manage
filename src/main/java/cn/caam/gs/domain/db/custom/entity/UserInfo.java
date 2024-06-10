package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UserInfo {
	
    private String id;
	private MUser user;
	
	/** 会员类型(F0002) */
	private String userTypeName;
	
	/** 入会途径(F0012) */
	private String membershipPathName;
	
	/** 关注(F0012)*/
    private String focusOnName;
    /** 性别(F0001)*/
    private String sexName;
    /** 民族(F0005)*/
    private String nationalityName;
    /** 政治面貌(F0003)*/
    private String politicalName;
    /** 学历(F0004)*/
    private String eduDegreeName;
    /** 学位(F0006)*/
    private String bachelorName;
    /** 职务(F0007)*/
    private String positionName;
    /** 单位性质(F0008)*/
    private String employerTypeName;
    /** 职称(F0009)*/
    private String jobTitleName;
    /** 证件类型(F0010)*/
    private String certificateTypeName;
    /** 所在地区(F0011)*/
    private String areaName;
    /** 所在地区市级(F0011)*/
    private String areaSubName;
    /** 审核状态(F0013)*/
    private String checkStatusName;

}
