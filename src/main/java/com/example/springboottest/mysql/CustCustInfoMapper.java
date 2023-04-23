package com.example.springboottest.mysql;

import com.example.springboottest.dto.CustCustInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustCustInfoMapper {

    List<CustCustInfo> selectByCondition(@Param("condition") CustCustInfo condition);

    List<CustCustInfo> queryMobileTelNoByCondition(@Param("condition") CustCustInfo condition);

}