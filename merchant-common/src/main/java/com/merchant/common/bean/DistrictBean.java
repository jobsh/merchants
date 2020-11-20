package com.merchant.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DistrictBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String disName;
	private int disId;
	private int cityId;
}
