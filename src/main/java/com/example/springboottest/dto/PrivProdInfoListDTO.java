package com.example.springboottest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PrivProdInfoListDTO implements Serializable {

    private static final long serialVersionUID = 6152636902648380482L;
    private String prodName;

    private String prodCode;

    private String prodAlia;

    private String onlineSign;

    private String shrType;

    private Long shrTypeId;

    private String payType;

    private BigDecimal rate;

    private BigDecimal useableAmt;

    private Integer useableCont;

    private Date reserveStartDate;

    private Date reserveEndDate;

    private String tradeCode;

    private BigDecimal buyAmt;

    private String strategyType;

    private String fundManager;

    private BigDecimal netVal;

    private String profCalcType;

    /**
     * 1：低 2：较低 3：中等 4：较高 5：高
     */
    private String prodRiskLevel;

    private BigDecimal newInc;

    private String remainProgress;

    private String reportTime;

    private Date expeExpiDate;

    private BigDecimal yearIncome;

    private Integer holdCount;

    private BigDecimal income;

    private String prodlabel;

    private String prodEval;

    private String earnType;

    private String prodType;

    private String matrType;

    private BigDecimal qrnhsyl;

    private String hwfundCode;

    private BigDecimal buyFee;

    private String buyRate;

    private String feeCalculateType;

    private Date prodEstaDate;

    private String closeTime;//锁定期

    private Date netvDate;//净值日期

    private String reserveDuration;//可预约时间段
    private String saleType;
    private String range;


    private String queryType;//查询类型：0-在售 1-存续 2-证券类专区

    private String periodName;//涨跌幅类型

    private Boolean mgrAlwaysVisible;

    private String industryCategory; // 行业分类

    private String firstIndustry; //一级行业分类

    private String indexDay;//指标计算初始时点(1,建仓日 2 成立日)

    private Date buildingDay;//建仓日

    private Integer isSnowBallProduct;//是否雪球产品

    private Long privProdId;

    @ApiModelProperty("挂钩标的")
    private String hookingTarget;

    @ApiModelProperty(value = "结构类型")
    private String structureType;

    @ApiModelProperty(value = "购买费率")
    private String buyFeeDesc;

    @ApiModelProperty("募集开始日期")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date raisBegDate;

    @ApiModelProperty("募集结束日期")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date raisEndDate;

    private String fundCode;

    private String shrCustNum;

    private String prodRiskLevelName;

    private String strategyTypeName;

    private String onlineSignName;

    @ApiModelProperty(value = "1:通用、2:证券类-开放期、3:证券类-募集期、4:雪球")
    private String kindType;

}
