package com.example.springboottest.config;

import java.io.Serializable;

/**
 * Date: 13-3-18 Time: 下午3:29
 */
@SuppressWarnings("serial")
public class Span implements Serializable {
	private String traceId;
	private Long id;
	private String parentId; // optional
	private String spanName;
	private String serviceId;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSpanName() {
		return spanName;
	}

	public void setSpanName(String spanName) {
		this.spanName = spanName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		return "Span{" + "traceId='" + traceId + '\'' + ", id='" + id + '\'' + '}';
	}
}
