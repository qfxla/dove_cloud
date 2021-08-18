package com.dove.authority.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author run
 * @since 2021-03-21
 */
@TableName("t_authority_enterprise")
@ApiModel(value="Enterprise对象", description="")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业id", hidden = true)
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "企业名称")
    @NotNull
    private String name;

    @ApiModelProperty(value = "企业简称")
    private String abbreviation;

    @ApiModelProperty(value = "工商注册号/社会统一信用代码")
    @NotNull
    private String businessRegistrationNumber;

    @ApiModelProperty(value = "证照有效期至")
    @NotNull
    private Date credentialValidityDate;

    @ApiModelProperty(value = "联系人名称")
    @NotNull
    private String contactName;

    @ApiModelProperty(value = "联系方式")
    @NotNull
    private String contactInformation;

    @ApiModelProperty(value = "经营类型")
    @NotNull
    private String managementType;

    @ApiModelProperty(value = "法人代表")
    @NotNull
    private String legalRepresentative;

    @ApiModelProperty(value = "经营者性质")
    @NotNull
    private String managementCharacter;

    @ApiModelProperty(value = "菜类商务部备案编码")
    private String vegetablesCommerceDepartmentCoding;

    @ApiModelProperty(value = "肉类商务部备案编码")
    private String meatCommerceDepartmentCoding;

    @ApiModelProperty(value = "企业主体二维码")
    private String enterpriseDominantCode;

    @ApiModelProperty(value = "展示模板")
    private String showcaseTemplate;

    @ApiModelProperty(value = "备案日期")
    private Date recordsDate;

    @ApiModelProperty(value = "门店编号")
    private String storeNumber;

    @ApiModelProperty(value = "地址(具体到门牌号)")
    @NotNull
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "企业图片")
    private String pictures;

    @ApiModelProperty(value = "企业视频")
    private String video;

    @ApiModelProperty(value = "企业视频名称")
    private String videoName;

    @ApiModelProperty(value = "企业微信公众号")
    private String wechatOfficialAccounts;

    @ApiModelProperty(value = "食品经营许可证")
    private String foodBusinessCertificate;

    @ApiModelProperty(value = "食品生产许可证")
    private String foodManufactureCertificate;

    @ApiModelProperty(value = "食品流通许可证")
    private String foodCirculateCertificate;

    @ApiModelProperty(value = "餐饮服务许可证")
    private String cateringServiceLicense;

    @ApiModelProperty(value = "企业认证")
    private String authentication;

    @ApiModelProperty(value = "公司简介")
    private String introduction;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间", hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除,是1、否0", hidden = true)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public Date getCredentialValidityDate() {
        return credentialValidityDate;
    }

    public void setCredentialValidityDate(Date credentialValidityDate) {
        this.credentialValidityDate = credentialValidityDate;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getManagementType() {
        return managementType;
    }

    public void setManagementType(String managementType) {
        this.managementType = managementType;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getManagementCharacter() {
        return managementCharacter;
    }

    public void setManagementCharacter(String managementCharacter) {
        this.managementCharacter = managementCharacter;
    }

    public String getVegetablesCommerceDepartmentCoding() {
        return vegetablesCommerceDepartmentCoding;
    }

    public void setVegetablesCommerceDepartmentCoding(String vegetablesCommerceDepartmentCoding) {
        this.vegetablesCommerceDepartmentCoding = vegetablesCommerceDepartmentCoding;
    }

    public String getMeatCommerceDepartmentCoding() {
        return meatCommerceDepartmentCoding;
    }

    public void setMeatCommerceDepartmentCoding(String meatCommerceDepartmentCoding) {
        this.meatCommerceDepartmentCoding = meatCommerceDepartmentCoding;
    }

    public String getEnterpriseDominantCode() {
        return enterpriseDominantCode;
    }

    public void setEnterpriseDominantCode(String enterpriseDominantCode) {
        this.enterpriseDominantCode = enterpriseDominantCode;
    }

    public String getShowcaseTemplate() {
        return showcaseTemplate;
    }

    public void setShowcaseTemplate(String showcaseTemplate) {
        this.showcaseTemplate = showcaseTemplate;
    }

    public Date getRecordsDate() {
        return recordsDate;
    }

    public void setRecordsDate(Date recordsDate) {
        this.recordsDate = recordsDate;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getWechatOfficialAccounts() {
        return wechatOfficialAccounts;
    }

    public void setWechatOfficialAccounts(String wechatOfficialAccounts) {
        this.wechatOfficialAccounts = wechatOfficialAccounts;
    }

    public String getFoodBusinessCertificate() {
        return foodBusinessCertificate;
    }

    public void setFoodBusinessCertificate(String foodBusinessCertificate) {
        this.foodBusinessCertificate = foodBusinessCertificate;
    }

    public String getFoodManufactureCertificate() {
        return foodManufactureCertificate;
    }

    public void setFoodManufactureCertificate(String foodManufactureCertificate) {
        this.foodManufactureCertificate = foodManufactureCertificate;
    }

    public String getFoodCirculateCertificate() {
        return foodCirculateCertificate;
    }

    public void setFoodCirculateCertificate(String foodCirculateCertificate) {
        this.foodCirculateCertificate = foodCirculateCertificate;
    }

    public String getCateringServiceLicense() {
        return cateringServiceLicense;
    }

    public void setCateringServiceLicense(String cateringServiceLicense) {
        this.cateringServiceLicense = cateringServiceLicense;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
