package com.merchant.quartz.task;

import com.merchant.common.enums.ContractStatus;
import com.merchant.system.domain.bo.ContractBO;
import com.merchant.system.service.IContractService;
import com.merchant.system.service.ICustomerService;
import com.merchant.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.merchant.common.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author hanke
 */
@Component("ryTask")
public class RyTask {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IContractService contractService;


    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    public void autoDegradeToXiansuo() {

        Integer intervalDays = Integer.valueOf(configService.selectConfigByKey("sys.customer.autoDegrade"));

        customerService.degradeToXiansuo(intervalDays);
    }

    /**
     * 合同到期后自动设置合同状态为合同到期状态到期
     */
    public void contractExpire() {
        ContractBO contractBO = new ContractBO();
        contractBO.setStatus(ContractStatus.EXPIRED.getCode());
        try {
            contractService.autoExpire(contractBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  当续约的新合同到了合同开始日期时要自动变成有效执行中状态
     */
    public void contractBegin() {
        ContractBO contractBO = new ContractBO();
        contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        try {
            contractService.autoBegin(contractBO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}