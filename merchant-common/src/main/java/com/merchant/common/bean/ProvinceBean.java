package com.merchant.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinceBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int code;
	private String proRemark;
}
