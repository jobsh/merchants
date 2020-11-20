package com.merchant.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cityName;
	private int cityId;
	private int proId;
}
