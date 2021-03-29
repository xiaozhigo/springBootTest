package com.example.springboottest.config;

import com.example.springboottest.service.OkHttp3Service;
import com.example.springboottest.service.impl.OkHttp3ServiceImpl;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 
 */
@Configuration
@ConditionalOnClass(OkHttpClient.class)
@ConditionalOnProperty(prefix = OkHttp3Properties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({OkHttp3Properties.class})
public class OkHttp3Configuration {


	@Bean
	public OkHttpClient okHttpClient(OkHttp3Properties properties) throws Exception {
		return new OkHttpClient().newBuilder()
				.connectTimeout(properties.getConnectTimeout(), TimeUnit.SECONDS)
				.readTimeout(properties.getReadTimeout(), TimeUnit.SECONDS)
				.writeTimeout(properties.getWriteTimeout(), TimeUnit.SECONDS)
				.pingInterval(properties.getPingInterval(), TimeUnit.SECONDS)
				.retryOnConnectionFailure(properties.isRetryOnConnectionFailure())
				.build();
	}

	@Bean
	public OkHttp3Service okHttp3Service(OkHttpClient okHttpClient){
		OkHttp3ServiceImpl okHttp3Service = new OkHttp3ServiceImpl();
		okHttp3Service.setOkHttpClient(okHttpClient);
		return okHttp3Service;
	}
	


}
