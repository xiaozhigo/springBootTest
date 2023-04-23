package com.example.springboottest.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:SALEORDER.CUST_CUSTINFO表的实体类
 * @version:  1.0.0
 * @author:  LiLi
 * @创建时间: 2017-06-21
 */
public class CustCustInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 销售商代码
     */
    private String distributorCode;

    /**
     * 客户号
     */
    private String custId;

    /**
     * 序列号
     */
    private String id;

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 15位证件号码
     */
    private String certificateNo15;

    /**
     * 投资者名称
     */
    private String investorName;

    /**
     * 投资者类型
     */
    private String individualOrInstitution;

    /**
     * 网点代码
     */
    private String branchCode;

    /**
     * 手机号码
     */
    private String mobileTelNo;

    /**
     * 电子邮件
     */
    private String emailAddress;

    /**
     * 客户状态
     */
    private String custStatus;

    /**
     * 客户标志列表
     */
    private String custFlagList;

    /**
     * 投顾编号
     */
    private String investmentAdviserCode;

    /**
     * 证件有效期
     */
    private String certValidDate;

    /**
     * 客户风险级别
     */
    private String custRiskLevel;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 开户日期
     */
    private String registDate;

    /**
     * 修改日期
     */
    private String modifyDate;

    /**
     * 预留信息
     */
    private String obligateInfo;

    /**
     * 客户级别
     */
    private String custLevel;

    /**
     * 是否签订电子约定书
     */
    private String isSignFlag;

    /**
     * 绑定mac地址用来校验
     */
    private String macAddress;

    /**
     * 风险测评到期日
     */
    private String testMaturity;

    /**
     * 是否合格投资者
     */
    private String isQualityCust;

    /**
     * 合格投资者选择标识
     */
    private String qualityCustChoiceFlag;

    /**
     * 网点代码
     */
    private String netNo;

    /**
     * 活动编号
     */
    private String activityNo;

    /**
     * 记住我的选择
     */
    private String rememberMyChoiceFlag;

    /**
     * 客户专业程度
     */
    private String isProfessional;

    /**
     * 资产证明状态
     */
    private String assetCertifyStatus;

    /**
     * 资产证明有效日期
     */
    private String assetCertifyMaturity;

    /**
     * 投资经验审核状态
     */
    private String investExperStatus;

    /**
     * 投资经验有效日期
     */
    private String investExperMaturity;

    /**
     * 实际控制人
     */
    private String actualController;

    /**
     * 交易实际受益人
     */
    private String actualBeneficiary;

    /**
     * 诚信记录 是否有不诚信记录 0-否 1-是
     */
    private String creditRecord;

    /**
     * 税收属性
     */
    private String taxProperty;

    /**
     * 上海股东卡号
     */
    private String shStockCard;

    /**
     * 深圳股东卡号
     */
    private String szStockCard;

    /**
     * 诚信记录
     */
    private String creditRecordIn;

    /**
     * 住址
     */
    private String addr;

    /**
     * 推荐人ID
     */
    private String recommendCustId;

    /**
     * 专业投资者类型
     */
    private String professionalType;
    /**
     * 专业投资者有效期
     */
    private String professionalMaturity;

    /**
     * 经办人证件类型
     */
    private String linkmanCertificateType;

    /**
     * 经办人证件号码
     */
    private String linkmanCertificateNo;

    /**
     * 经办人姓名
     */
    private String linkmanName;

    /**
     * 经办人证件有效期
     */
    private String linkmanCertValidDate;

    /**
     * 经办人电话
     */
    private String linkmanTel;

    /**
     * 经办人地址
     */
    private String linkmanAddr;

    /**
     * 授权书有效期
     */
    private String linkManWarrantEndDate;

    /**
     * 经办人手机号码
     */
    private String linkManMobile;


    /**
     *  适当性专业投资者类别
     */
    private String professionSubType;

    /***
     * 适当性准入资格标识
     * @return
     */
    private String enterSeniorityFlag;

    /**
     * 是否存在非居民控制人标志
     */
    private String haveNonResConFlag;

    /**
     * 归档编号
     */
    private Long backUpId;

    /**证件资料是否已上传*/
    private String certImgFlag;

    private String auditflag;

    /**
     * 是否确认隐私协议 0:未确认1：已确认
     */
    private String privacyPolicyFlag;


    /**
     * 风险测评到期短信提醒日期（定投协议有效的客户）
     */
    private String planRiskTestExpiresSmsDate;
    /**
     * 风险测评过期天数 负数表示已过期 正数表示还有几天过期
     */
    private String expiresDays;

    /**
     * 手机号加密字段
     */
    private String mobileTelNoSecret;
    /**
     * 脱敏手机号
     */
    private String mobileTelNoDesensitize;

    /**
     * 外部来源(记录其它渠道生成的数据，格式可自定义（前缀唯一），如基慧通：JHT_111)
     */
    private String externalSource;

    public String getExternalSource() {
        return externalSource;
    }

    public void setExternalSource(String externalSource) {
        this.externalSource = externalSource;
    }

    public String getPlanRiskTestExpiresSmsDate() {
        return planRiskTestExpiresSmsDate;
    }

    public void setPlanRiskTestExpiresSmsDate(String planRiskTestExpiresSmsDate) {
        this.planRiskTestExpiresSmsDate = planRiskTestExpiresSmsDate;
    }

    public String getExpiresDays() {
        return expiresDays;
    }

    public void setExpiresDays(String expiresDays) {
        this.expiresDays = expiresDays;
    }
    private String certificationStatus;
    private Date certificationTime;
    /**
     * 账户代理人类型 0-本人 1-他人
     */
    private String accountAgent;
    /**
     * IP 地址
     */
    private String ipAddress;
    /**
     * 国际移动设备识别码
     */
    private String imei;
    /**
     * 通用唯一识别码
     */
    private String uuid;

    public String getAccountAgent() {
        return accountAgent;
    }

    public void setAccountAgent(String accountAgent) {
        this.accountAgent = accountAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCertificationTime() {
        return certificationTime;
    }

    public void setCertificationTime(Date certificationTime) {
        this.certificationTime = certificationTime;
    }

    public String getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(String certificationStatus) {
        this.certificationStatus = certificationStatus;

    }

    public String getAuditflag() {
        return auditflag;
    }

    public void setAuditflag(String auditflag) {
        this.auditflag = auditflag;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 专业投资者有效期
     * @return professionalMaturity 专业投资者有效期
     */
    public String getProfessionalMaturity() {
        return professionalMaturity;
    }

    /**
     * 专业投资者有效期
     * @param professionalMaturity 专业投资者有效期
     */
    public void setProfessionalMaturity(String professionalMaturity) {
        this.professionalMaturity = professionalMaturity;
    }

    /**
     * 专业投资者类型
     * @return professionalType 专业投资者类型
     */
    public String getProfessionalType() {
        return professionalType;
    }

    /**
     * 专业投资者类型
     * @param professionalType 专业投资者类型
     */
    public void setProfessionalType(String professionalType) {
        this.professionalType = professionalType;
    }

    /**
     * 推荐人ID
     * @return recommendCustId 推荐人ID
     */
    public String getRecommendCustId() {
        return recommendCustId;
    }

    /**
     * 推荐人ID
     * @param recommendCustId 推荐人ID
     */
    public void setRecommendCustId(String recommendCustId) {
        this.recommendCustId = recommendCustId;
    }

    /**
     * 销售商代码
     * @return DISTRIBUTORCODE 销售商代码
     */
    public String getDistributorCode() {
        return distributorCode;
    }

    /**
     * 销售商代码
     * @param distributorCode 销售商代码
     */
    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode == null ? null : distributorCode.trim();
    }

    /**
     * 客户号
     * @return CUSTID 客户号
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 客户号
     * @param custId 客户号
     */
    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    /**
     * 序列号
     * @return ID 序列号
     */
    public String getId() {
        return id;
    }

    /**
     * 序列号
     * @param id 序列号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 证件类型
     * @return CERTIFICATETYPE 证件类型
     */
    public String getCertificateType() {
        return certificateType;
    }

    /**
     * 证件类型
     * @param certificateType 证件类型
     */
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType == null ? null : certificateType.trim();
    }

    /**
     * 证件号码
     * @return CERTIFICATENO 证件号码
     */
    public String getCertificateNo() {
        return certificateNo;
    }

    /**
     * 证件号码
     * @param certificateNo 证件号码
     */
    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    /**
     * 15位证件号码
     * @return CERTIFICATENO15 15位证件号码
     */
    public String getCertificateNo15() {
        return certificateNo15;
    }

    /**
     * 15位证件号码
     * @param certificateNo15 15位证件号码
     */
    public void setCertificateNo15(String certificateNo15) {
        this.certificateNo15 = certificateNo15 == null ? null : certificateNo15.trim();
    }

    /**
     * 投资者名称
     * @return INVESTORNAME 投资者名称
     */
    public String getInvestorName() {
        return investorName;
    }

    /**
     * 投资者名称
     * @param investorName 投资者名称
     */
    public void setInvestorName(String investorName) {
        this.investorName = investorName == null ? null : investorName.trim();
    }

    /**
     * 投资者类型
     * @return INDIVIDUALORINSTITUTION 投资者类型
     */
    public String getIndividualOrInstitution() {
        return individualOrInstitution;
    }

    /**
     * 投资者类型
     * @param individualOrInstitution 投资者类型
     */
    public void setIndividualOrInstitution(String individualOrInstitution) {
        this.individualOrInstitution = individualOrInstitution == null ? null : individualOrInstitution.trim();
    }

    /**
     * 网点代码
     * @return BRANCHCODE 网点代码
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * 网点代码
     * @param branchCode 网点代码
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode == null ? null : branchCode.trim();
    }

    /**
     * 手机号码
     * @return MOBILETELNO 手机号码
     */
    public String getMobileTelNo() {
        return mobileTelNo;
    }

    /**
     * 手机号码
     * @param mobileTelNo 手机号码
     */
    public void setMobileTelNo(String mobileTelNo) {
        this.mobileTelNo = mobileTelNo == null ? null : mobileTelNo.trim();
    }

    /**
     * 电子邮件
     * @return EMAILADDRESS 电子邮件
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * 电子邮件
     * @param emailAddress 电子邮件
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    /**
     * 客户状态
     * @return CUSTSTATUS 客户状态
     */
    public String getCustStatus() {
        return custStatus;
    }

    /**
     * 客户状态
     * @param custStatus 客户状态
     */
    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus == null ? null : custStatus.trim();
    }

    /**
     * 客户标志列表
     * @return CUSTFLAGLIST 客户标志列表
     */
    public String getCustFlagList() {
        return custFlagList;
    }

    /**
     * 客户标志列表
     * @param custFlagList 客户标志列表
     */
    public void setCustFlagList(String custFlagList) {
        this.custFlagList = custFlagList == null ? null : custFlagList.trim();
    }

    /**
     * 投顾编号
     * @return INVESTMENTADVISERCODE 投顾编号
     */
    public String getInvestmentAdviserCode() {
        return investmentAdviserCode;
    }

    /**
     * 投顾编号
     * @param investmentAdviserCode 投顾编号
     */
    public void setInvestmentAdviserCode(String investmentAdviserCode) {
        this.investmentAdviserCode = investmentAdviserCode == null ? null : investmentAdviserCode.trim();
    }

    /**
     * 证件有效期
     * @return CERTVALIDDATE 证件有效期
     */
    public String getCertValidDate() {
        return certValidDate;
    }

    /**
     * 证件有效期
     * @param certValidDate 证件有效期
     */
    public void setCertValidDate(String certValidDate) {
        this.certValidDate = certValidDate == null ? null : certValidDate.trim();
    }

    /**
     * 客户风险级别
     * @return CUSTRISKLEVEL 客户风险级别
     */
    public String getCustRiskLevel() {
        return custRiskLevel;
    }

    /**
     * 客户风险级别
     * @param custRiskLevel 客户风险级别
     */
    public void setCustRiskLevel(String custRiskLevel) {
        this.custRiskLevel = custRiskLevel == null ? null : custRiskLevel.trim();
    }

    /**
     * 用户名
     * @return USERNAME 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 开户日期
     * @return REGISTDATE 开户日期
     */
    public String getRegistDate() {
        return registDate;
    }

    /**
     * 开户日期
     * @param registDate 开户日期
     */
    public void setRegistDate(String registDate) {
        this.registDate = registDate == null ? null : registDate.trim();
    }

    /**
     * 修改日期
     * @return MODIFYDATE 修改日期
     */
    public String getModifyDate() {
        return modifyDate;
    }

    /**
     * 修改日期
     * @param modifyDate 修改日期
     */
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate == null ? null : modifyDate.trim();
    }

    /**
     * 预留信息
     * @return OBLIGATEINFO 预留信息
     */
    public String getObligateInfo() {
        return obligateInfo;
    }

    /**
     * 预留信息
     * @param obligateInfo 预留信息
     */
    public void setObligateInfo(String obligateInfo) {
        this.obligateInfo = obligateInfo == null ? null : obligateInfo.trim();
    }

    /**
     * 客户级别
     * @return CUSTLEVEL 客户级别
     */
    public String getCustLevel() {
        return custLevel;
    }

    /**
     * 客户级别
     * @param custLevel 客户级别
     */
    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel == null ? null : custLevel.trim();
    }

    /**
     * 是否签订电子约定书
     * @return ISSIGNFLAG 是否签订电子约定书
     */
    public String getIsSignFlag() {
        return isSignFlag;
    }

    /**
     * 是否签订电子约定书
     * @param isSignFlag 是否签订电子约定书
     */
    public void setIsSignFlag(String isSignFlag) {
        this.isSignFlag = isSignFlag == null ? null : isSignFlag.trim();
    }

    /**
     * 绑定mac地址用来校验
     * @return MACADDRESS 绑定mac地址用来校验
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * 绑定mac地址用来校验
     * @param macAddress 绑定mac地址用来校验
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    /**
     * 风险测评到期日
     * @return TESTMATURITY 风险测评到期日
     */
    public String getTestMaturity() {
        return testMaturity;
    }

    /**
     * 风险测评到期日
     * @param testMaturity 风险测评到期日
     */
    public void setTestMaturity(String testMaturity) {
        this.testMaturity = testMaturity == null ? null : testMaturity.trim();
    }

    /**
     * 是否合格投资者
     * @return ISQUALITYCUST 是否合格投资者
     */
    public String getIsQualityCust() {
        return isQualityCust;
    }

    /**
     * 是否合格投资者
     * @param isQualityCust 是否合格投资者
     */
    public void setIsQualityCust(String isQualityCust) {
        this.isQualityCust = isQualityCust == null ? null : isQualityCust.trim();
    }

    /**
     * 网点代码
     * @return NETNO 网点代码
     */
    public String getNetNo() {
        return netNo;
    }

    /**
     * 网点代码
     * @param netNo 网点代码
     */
    public void setNetNo(String netNo) {
        this.netNo = netNo == null ? null : netNo.trim();
    }

    /**
     * 活动编号
     * @return ACTIVITYNO 活动编号
     */
    public String getActivityNo() {
        return activityNo;
    }

    /**
     * 活动编号
     * @param activityNo 活动编号
     */
    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo == null ? null : activityNo.trim();
    }

    /**
     * 记住我的选择
     * @return REMEMBERMYCHOICEFLAG 记住我的选择
     */
    public String getRememberMyChoiceFlag() {
        return rememberMyChoiceFlag;
    }

    /**
     * 记住我的选择
     * @param rememberMyChoiceFlag 记住我的选择
     */
    public void setRememberMyChoiceFlag(String rememberMyChoiceFlag) {
        this.rememberMyChoiceFlag = rememberMyChoiceFlag == null ? null : rememberMyChoiceFlag.trim();
    }

    /**
     * 客户专业程度
     * @return ISPROFESSIONAL 客户专业程度
     */
    public String getIsProfessional() {
        return isProfessional;
    }

    /**
     * 客户专业程度
     * @param isProfessional 客户专业程度
     */
    public void setIsProfessional(String isProfessional) {
        this.isProfessional = isProfessional == null ? null : isProfessional.trim();
    }

    /**
     * 资产证明状态
     * @return ASSETCERTIFYSTATUS 资产证明状态
     */
    public String getAssetCertifyStatus() {
        return assetCertifyStatus;
    }

    /**
     * 资产证明状态
     * @param assetCertifyStatus 资产证明状态
     */
    public void setAssetCertifyStatus(String assetCertifyStatus) {
        this.assetCertifyStatus = assetCertifyStatus == null ? null : assetCertifyStatus.trim();
    }

    /**
     * 资产证明有效日期
     * @return ASSETCERTIFYMATURITY 资产证明有效日期
     */
    public String getAssetCertifyMaturity() {
        return assetCertifyMaturity;
    }

    /**
     * 资产证明有效日期
     * @param assetCertifyMaturity 资产证明有效日期
     */
    public void setAssetCertifyMaturity(String assetCertifyMaturity) {
        this.assetCertifyMaturity = assetCertifyMaturity == null ? null : assetCertifyMaturity.trim();
    }

    /**
     * 投资经验审核状态
     * @return INVESTEXPERSTATUS 投资经验审核状态
     */
    public String getInvestExperStatus() {
        return investExperStatus;
    }

    /**
     * 投资经验审核状态
     * @param investExperStatus 投资经验审核状态
     */
    public void setInvestExperStatus(String investExperStatus) {
        this.investExperStatus = investExperStatus == null ? null : investExperStatus.trim();
    }

    /**
     * 投资经验有效日期
     * @return INVESTEXPERMATURITY 投资经验有效日期
     */
    public String getInvestExperMaturity() {
        return investExperMaturity;
    }

    /**
     * 投资经验有效日期
     * @param investExperMaturity 投资经验有效日期
     */
    public void setInvestExperMaturity(String investExperMaturity) {
        this.investExperMaturity = investExperMaturity == null ? null : investExperMaturity.trim();
    }

    /**
     * 实际控制人
     * @return ACTUALCONTROLLER 实际控制人
     */
    public String getActualController() {
        return actualController;
    }

    /**
     * 实际控制人
     * @param actualController 实际控制人
     */
    public void setActualController(String actualController) {
        this.actualController = actualController == null ? null : actualController.trim();
    }

    /**
     * 交易实际受益人
     * @return ACTUALBENEFICIARY 交易实际受益人
     */
    public String getActualBeneficiary() {
        return actualBeneficiary;
    }

    /**
     * 交易实际受益人
     * @param actualBeneficiary 交易实际受益人
     */
    public void setActualBeneficiary(String actualBeneficiary) {
        this.actualBeneficiary = actualBeneficiary == null ? null : actualBeneficiary.trim();
    }

    /**
     * 诚信记录
     * @return CREDITRECORD 诚信记录
     */
    public String getCreditRecord() {
        return creditRecord;
    }

    /**
     * 诚信记录
     * @param creditRecord 诚信记录
     */
    public void setCreditRecord(String creditRecord) {
        this.creditRecord = creditRecord == null ? null : creditRecord.trim();
    }

    /**
     * 税收属性
     * @return TAXPROPERTY 税收属性
     */
    public String getTaxProperty() {
        return taxProperty;
    }

    /**
     * 税收属性
     * @param taxProperty 税收属性
     */
    public void setTaxProperty(String taxProperty) {
        this.taxProperty = taxProperty == null ? null : taxProperty.trim();
    }

    /**
     * 上海股东卡号
     * @return SHSTOCKCARD 上海股东卡号
     */
    public String getShStockCard() {
        return shStockCard;
    }

    /**
     * 上海股东卡号
     * @param shStockCard 上海股东卡号
     */
    public void setShStockCard(String shStockCard) {
        this.shStockCard = shStockCard == null ? null : shStockCard.trim();
    }

    /**
     * 深圳股东卡号
     * @return SZSTOCKCARD 深圳股东卡号
     */
    public String getSzStockCard() {
        return szStockCard;
    }

    /**
     * 深圳股东卡号
     * @param szStockCard 深圳股东卡号
     */
    public void setSzStockCard(String szStockCard) {
        this.szStockCard = szStockCard == null ? null : szStockCard.trim();
    }

    /**
     * 诚信记录
     * @return CREDITRECORDIN 诚信记录
     */
    public String getCreditRecordIn() {
        return creditRecordIn;
    }

    /**
     * 诚信记录
     * @param creditRecordIn 诚信记录
     */
    public void setCreditRecordIn(String creditRecordIn) {
        this.creditRecordIn = creditRecordIn == null ? null : creditRecordIn.trim();
    }

    /**
     * 住址
     * @return ADDR 住址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 住址
     * @param addr 住址
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getLinkmanCertificateType() {
        return linkmanCertificateType;
    }

    public void setLinkmanCertificateType(String linkmanCertificateType) {
        this.linkmanCertificateType = linkmanCertificateType;
    }

    public String getLinkmanCertificateNo() {
        return linkmanCertificateNo;
    }

    public void setLinkmanCertificateNo(String linkmanCertificateNo) {
        this.linkmanCertificateNo = linkmanCertificateNo;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanCertValidDate() {
        return linkmanCertValidDate;
    }

    public void setLinkmanCertValidDate(String linkmanCertValidDate) {
        this.linkmanCertValidDate = linkmanCertValidDate;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getLinkmanAddr() {
        return linkmanAddr;
    }

    public void setLinkmanAddr(String linkmanAddr) {
        this.linkmanAddr = linkmanAddr;
    }

    public String getLinkManWarrantEndDate() {
        return linkManWarrantEndDate;
    }

    public void setLinkManWarrantEndDate(String linkManWarrantEndDate) {
        this.linkManWarrantEndDate = linkManWarrantEndDate;
    }

    public String getLinkManMobile() {
        return linkManMobile;
    }

    public void setLinkManMobile(String linkManMobile) {
        this.linkManMobile = linkManMobile;
    }

    public String getProfessionSubType() {
        return professionSubType;
    }

    public void setProfessionSubType(String professionSubType) {
        this.professionSubType = professionSubType;
    }

    public String getEnterSeniorityFlag() {
        return enterSeniorityFlag;
    }

    public void setEnterSeniorityFlag(String enterSeniorityFlag) {
        this.enterSeniorityFlag = enterSeniorityFlag;
    }



    public String getHaveNonResConFlag() {
        return haveNonResConFlag;
    }

    public void setHaveNonResConFlag(String haveNonResConFlag) {
        this.haveNonResConFlag = haveNonResConFlag;
    }

    public String getQualityCustChoiceFlag() {
        return qualityCustChoiceFlag;
    }

    public void setQualityCustChoiceFlag(String qualityCustChoiceFlag) {
        this.qualityCustChoiceFlag = qualityCustChoiceFlag;
    }

    public Long getBackUpId() {
        return backUpId;
    }

    public void setBackUpId(Long backUpId) {
        this.backUpId = backUpId;
    }

    public String getCertImgFlag() {
        return certImgFlag;
    }

    public void setCertImgFlag(String certImgFlag) {
        this.certImgFlag = certImgFlag;
    }

    public String getPrivacyPolicyFlag() {
        return privacyPolicyFlag;
    }

    public void setPrivacyPolicyFlag(String privacyPolicyFlag) {
        this.privacyPolicyFlag = privacyPolicyFlag;
    }

    public String getMobileTelNoSecret() {
        return mobileTelNoSecret;
    }

    public void setMobileTelNoSecret(String mobileTelNoSecret) {
        this.mobileTelNoSecret = mobileTelNoSecret;
    }

    public String getMobileTelNoDesensitize() {
        return mobileTelNoDesensitize;
    }

    public void setMobileTelNoDesensitize(String mobileTelNoDesensitize) {
        this.mobileTelNoDesensitize = mobileTelNoDesensitize;
    }

    @Override
    public String toString() {
        return "CustCustInfo{" +
                "distributorCode='" + distributorCode + '\'' +
                ", custId='" + custId + '\'' +
                ", id='" + id + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateNo='" + certificateNo + '\'' +
                ", certificateNo15='" + certificateNo15 + '\'' +
                ", investorName='" + investorName + '\'' +
                ", individualOrInstitution='" + individualOrInstitution + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", mobileTelNo='" + mobileTelNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", custStatus='" + custStatus + '\'' +
                ", custFlagList='" + custFlagList + '\'' +
                ", investmentAdviserCode='" + investmentAdviserCode + '\'' +
                ", certValidDate='" + certValidDate + '\'' +
                ", custRiskLevel='" + custRiskLevel + '\'' +
                ", userName='" + userName + '\'' +
                ", registDate='" + registDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", obligateInfo='" + obligateInfo + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", isSignFlag='" + isSignFlag + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", testMaturity='" + testMaturity + '\'' +
                ", isQualityCust='" + isQualityCust + '\'' +
                ", qualityCustChoiceFlag='" + qualityCustChoiceFlag + '\'' +
                ", netNo='" + netNo + '\'' +
                ", activityNo='" + activityNo + '\'' +
                ", rememberMyChoiceFlag='" + rememberMyChoiceFlag + '\'' +
                ", isProfessional='" + isProfessional + '\'' +
                ", assetCertifyStatus='" + assetCertifyStatus + '\'' +
                ", assetCertifyMaturity='" + assetCertifyMaturity + '\'' +
                ", investExperStatus='" + investExperStatus + '\'' +
                ", investExperMaturity='" + investExperMaturity + '\'' +
                ", actualController='" + actualController + '\'' +
                ", actualBeneficiary='" + actualBeneficiary + '\'' +
                ", accountAgent='" + accountAgent + '\'' +
                ", creditRecord='" + creditRecord + '\'' +
                ", taxProperty='" + taxProperty + '\'' +
                ", shStockCard='" + shStockCard + '\'' +
                ", szStockCard='" + szStockCard + '\'' +
                ", creditRecordIn='" + creditRecordIn + '\'' +
                ", addr='" + addr + '\'' +
                ", recommendCustId='" + recommendCustId + '\'' +
                ", professionalType='" + professionalType + '\'' +
                ", professionalMaturity='" + professionalMaturity + '\'' +
                ", linkmanCertificateType='" + linkmanCertificateType + '\'' +
                ", linkmanCertificateNo='" + linkmanCertificateNo + '\'' +
                ", linkmanName='" + linkmanName + '\'' +
                ", linkmanCertValidDate='" + linkmanCertValidDate + '\'' +
                ", linkmanTel='" + linkmanTel + '\'' +
                ", linkmanAddr='" + linkmanAddr + '\'' +
                ", linkManWarrantEndDate='" + linkManWarrantEndDate + '\'' +
                ", linkManMobile='" + linkManMobile + '\'' +
                ", professionSubType='" + professionSubType + '\'' +
                ", enterSeniorityFlag='" + enterSeniorityFlag + '\'' +
                ", haveNonResConFlag='" + haveNonResConFlag + '\'' +
                ", backUpId=" + backUpId +
                ", certImgFlag='" + certImgFlag + '\'' +
                ", auditflag='" + auditflag + '\'' +
                ", privacyPolicyFlag='" + privacyPolicyFlag + '\'' +
                ", mobileTelNoSecret='" + mobileTelNoSecret + '\'' +
                ", mobileTelNoDesensitize='" + mobileTelNoDesensitize + '\'' +
                '}';
    }
}
