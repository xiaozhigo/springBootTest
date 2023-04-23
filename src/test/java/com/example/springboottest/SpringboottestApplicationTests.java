package com.example.springboottest;

import com.example.springboottest.dto.CustCustInfo;
import com.example.springboottest.service.CustCustInfoService;
import com.example.springboottest.util.AesEncryptTool;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringboottestApplicationTests {

	@Autowired
	private CustCustInfoService custCustInfoService;

	@Test
	public void contextLoads() {
		List<CustCustInfo> custCustInfos = custCustInfoService.queryCustInfo();
		String toJson = "";
		if(!CollectionUtils.isEmpty(custCustInfos)){
			custCustInfos.forEach(e ->{
				e.setMobileTelNo(AesEncryptTool.decrypt(e.getMobileTelNo()));
			});
			toJson = new Gson().toJson(custCustInfos);
		}
		System.out.println(toJson);
	}

}

