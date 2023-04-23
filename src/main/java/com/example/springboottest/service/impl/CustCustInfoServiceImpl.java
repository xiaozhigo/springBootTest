package com.example.springboottest.service.impl;

import com.example.springboottest.dto.CustCustInfo;
import com.example.springboottest.mysql.CustCustInfoMapper;
import com.example.springboottest.service.CustCustInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustCustInfoServiceImpl implements CustCustInfoService {

    @Autowired
    private CustCustInfoMapper custCustInfoMapper;

    @Override
    public List<CustCustInfo> queryCustInfo() {
        CustCustInfo custCustInfo = new CustCustInfo();
        return custCustInfoMapper.queryMobileTelNoByCondition(custCustInfo);
    }
}
