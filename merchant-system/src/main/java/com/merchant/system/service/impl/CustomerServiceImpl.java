package com.merchant.system.service.impl;

import com.merchant.common.annotation.DataScope;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.enums.CustomerStatus;
import com.merchant.common.exception.CustomException;
import com.merchant.common.utils.DateUtils;
import com.merchant.common.utils.StringUtils;
import com.merchant.system.domain.Customer;
import com.merchant.system.domain.bo.AddCustomerBO;
import com.merchant.system.domain.bo.CustomerBO;
import com.merchant.system.mapper.CustomerMapper;
import com.merchant.system.mapper.SysUserMapper;
import com.merchant.system.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * 我的客户Service业务层处理
 *
 * @author hanke
 * @date 2020-10-28
 */
@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询我的客户
     *
     * @param id 我的客户ID
     * @return 我的客户
     */
    @Override
    public Customer selectCustomerById(Integer id) {
        return customerMapper.selectCustomerById(id);
    }

    @Override
    public Customer selectXiansuoById(Integer id) {
        return customerMapper.selectXiansuoById(id);
    }


    /**
     * 查询我的客户列表
     *
     * @param customerBO 我的客户
     * @return 我的客户
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Customer> selectCustomerList(@RequestBody CustomerBO customerBO) {
        customerBO.setStatus(CustomerStatus.OK.getCode());
        return customerMapper.selectCustomerList(customerBO);
    }

    /**
     * 查询我的线索列表
     *
     * @param customerBO 我的客户
     * @return 我的线索集合
     */

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Customer> selectXiansuoList(@RequestBody CustomerBO customerBO) {
        customerBO.setStatus(CustomerStatus.DISABLE.getCode());
        return customerMapper.selectCustomerList(customerBO);
    }

    /**
     * 新增我的客户
     *
     * @param customer 我的客户
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer) {
        String customerNum = sid.nextShort();
        customer.setNum(customerNum);

        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改我的客户
     *
     * @param customerBO 我的客户
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customerBO) {
        return customerMapper.updateCustomer(customerBO);
    }

    @Override
    public int updateGenjinDate(Integer id) {
        return customerMapper.updateGenjinDate(id);
    }


    /**
     * 批量修改我的客户
     *
     * @param customerBO 我的客户
     * @return 结果
     */
    @Override
    public int updateCustomerByIds(CustomerBO customerBO) {
        return customerMapper.updateCustomerByIds(customerBO);
    }

    /**
     * 批量删除我的客户
     *
     * @param ids 需要删除的我的客户ID
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Integer[] ids) {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除我的客户信息
     *
     * @param id 我的客户ID
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Integer id) {
        return customerMapper.deleteCustomerById(id);
    }

    @Override
    public int transferCustomer(CustomerBO customerBO) {
        // 根据负责人phone 查询出负责人id和负责人name
        SysUser sysUser = new SysUser();
//        sysUser.setPhonenumber(customerBO.getManagerPhone());
        sysUser.setId(customerBO.getUserId().longValue());
        // 获取要转移给的人的User信息
        sysUser = sysUserMapper.selectUserById(customerBO.getUserId().longValue());
        List<Customer> customers = customerMapper.selectCustomerByIds(customerBO);
        if (sysUser == null) {
            return -1;
        }
        if (customers.size() == 0 || customers == null || customers.isEmpty()) {
            return -2;
        }
        customerBO.setUserId(sysUser.getId().intValue());
        customerBO.setUsername(sysUser.getUserName());
        customerBO.setDeptId(sysUser.getDeptId().intValue());

        // 更新customer负责人id和username
        return customerMapper.updateCustomerByIds(customerBO);
    }

    @Override
    public int evolveCustomer(CustomerBO customerBO) {
        // 清理冗余线索（phone相同的线索为冗余线索）,只保留一条最新更改的线索
//        customerMapper.clearRedundantXiansuo(customerBO.getPhone());
        // 修改客户表status为线索状态（status由0置为1）
        customerBO.setStatus(CustomerStatus.OK.getCode());
        // 设置负责人用户名
        return customerMapper.updateCustomerByIds(customerBO);
    }

    @Override
    public int existCustomer(String phone) {
        return customerMapper.countCustomerByPhone(phone);
    }

    @Override
    public String importCustomer(List<Customer> customerList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(customerList) || customerList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Customer customer : customerList) {
            try {
                // 验证是否存在这个用户
                int res = customerMapper.countCustomerByPhone(customer.getPhone());
                if (res == 0) {
                    customer.setCreateBy(operName);
                    this.insertCustomer(customer);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、用户： " + customer.getName() + " 导入成功");
                } else if (isUpdateSupport) {
                    customer.setUpdateBy(operName);
                    this.updateCustomer((CustomerBO) customer);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + customer.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + customer.getName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + customer.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public void degradeToXiansuo(int intervalDays) {
        customerMapper.autoDegradeToXianSuo(intervalDays);
    }


}
