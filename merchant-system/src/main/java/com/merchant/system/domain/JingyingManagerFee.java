/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2021/1/20 12:47
 */
package com.merchant.system.domain;

import com.merchant.common.annotation.Excel;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JingyingManagerFee {
    @Excel(name = "总费用")
    private String total;
    @Excel(name = "经营管理费详情")
    private List<Map<String, String>> detail;
}