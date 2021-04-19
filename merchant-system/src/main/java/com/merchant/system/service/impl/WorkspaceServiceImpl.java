package com.merchant.system.service.impl;

import com.merchant.common.utils.DateUtils;
import com.merchant.system.domain.vo.*;
import com.merchant.system.mapper.WorkspaceMapper;
import com.merchant.system.service.ISysConfigService;
import com.merchant.system.service.IWorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.merchant.common.utils.DateUtils.getMonthFirstDay;

/**
 * @Classname WorkspaceServiceImpl
 * @Description TODO
 * @Date 2020/12/24 16:34
 * @Created by hanke
 */
@Service
public class WorkspaceServiceImpl implements IWorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;

    @Autowired
    private ISysConfigService sysConfigService;

    @Override
    public List<DianmianNumVO> selectDianmianNumList(String type) {
        return workspaceMapper.selectDianmianNumList(type);
    }

    @Override
    public List<DianmianAndContractAllVO> dianmianAndContractAllList(String type) {
        return workspaceMapper.dianmianAndContractAllList(type);
    }

    @Override
    public List<ContractDandianAndQuyuVO> selectContractDandianAndQuyuVO(String type) {
        return workspaceMapper.selectContractDandianAndQuyuVO(type);
    }

    @Override
    public List<CustomerWorkSpaceVO> selectCustomerWorkspaceList(String type, Integer deptId) {
        return workspaceMapper.selectCustomerWorkspaceList(type, deptId);
    }

    @Override
    public List<CustomerFeeWorkSpaceVO> selectCustomerFeeWorkspaceList(String type, Integer deptId) {
        return workspaceMapper.selectCustomerFeeWorkspaceList(type, deptId);
    }

    @Override
    public Integer selectQyNumByCompanyId(Integer companyId, Integer month) {
        String begin = DateUtils.getMonthFirstDay(month);
        String end = DateUtils.getMonthLastDay(month);
        return workspaceMapper.selectQyNumByCompanyId(companyId, begin, end);
    }

    @Override
    public Map<String, Integer> selectQyNumAll(Integer month) {
        Integer sjzQyNum = this.selectQyNumByCompanyId(2, month);
        Integer czQyNum = this.selectQyNumByCompanyId(5, month);
        Integer cdQyNum = this.selectQyNumByCompanyId(11, month);
        Map<String, Integer> map = new HashMap<>();
        map.put("sjzQyNum", sjzQyNum);
        map.put("czQyNum", czQyNum);
        map.put("cdQyNum", cdQyNum);
        return map;
    }

    @Override
    public Map<String, Integer> selectQyNumAllOfQuarter() {
        Integer sjzQyObjectOfQuarter = Integer.parseInt(sysConfigService.selectConfigByKey("sjz_qy_object_quarter"));
        Integer czQyObjectOfQuarter = Integer.parseInt(sysConfigService.selectConfigByKey("cz_qy_object_of_quarter"));
        Integer cdQyObjectOfQuarter = Integer.parseInt(sysConfigService.selectConfigByKey("cd_qy_object_quarter"));

        String currentQuarterStartTime = DateUtils.getCurrentQuarterStartTime();
        String currentQuarterEndTime = DateUtils.getCurrentQuarterEndTime();

        Integer sjzQyNumOfQuarter = workspaceMapper.selectQyNumByCompanyId(2, currentQuarterStartTime, currentQuarterEndTime);
        Integer czQyNumOfQuarter = workspaceMapper.selectQyNumByCompanyId(5, currentQuarterStartTime, currentQuarterEndTime);
        Integer cdQyNumOfQuarter = workspaceMapper.selectQyNumByCompanyId(11, currentQuarterStartTime, currentQuarterEndTime);
    
        Map<String, Integer> map = new HashMap<>();

        map.put("sjzQyObject", sjzQyObjectOfQuarter);
        map.put("czQyObject", czQyObjectOfQuarter);
        map.put("cdQyObject", cdQyObjectOfQuarter);

        map.put("sjzQyNum", sjzQyNumOfQuarter);
        map.put("czQyNum", czQyNumOfQuarter);
        map.put("cdQyNum", cdQyNumOfQuarter);
        return map;
    }

    @Override
    public Map<String, Integer> selectQyNumAllOfYear() {

        Integer sjzQyObjectOfYear= Integer.parseInt(sysConfigService.selectConfigByKey("sjz_qy_object_year"));
        Integer czQyObjectOfYear = Integer.parseInt(sysConfigService.selectConfigByKey("cz_qy_object_of_year"));
        Integer cdQyObjectOfYear = Integer.parseInt(sysConfigService.selectConfigByKey("cd_qy_object_year"));

        String currentYearStart = DateUtils.getCurrentYearStart();
        String currentYearEnd = DateUtils.getCurrentYearEnd();

        Integer sjzQyNumOfYear = workspaceMapper.selectQyNumByCompanyId(2, currentYearStart, currentYearEnd);
        Integer czQyNumOfYear = workspaceMapper.selectQyNumByCompanyId(5, currentYearStart, currentYearEnd);
        Integer cdQyNumOfYear = workspaceMapper.selectQyNumByCompanyId(11, currentYearStart, currentYearEnd);

        Map<String, Integer> map = new HashMap<>();

        map.put("sjzQyObject", sjzQyObjectOfYear);
        map.put("czQyObject", czQyObjectOfYear);
        map.put("cdQyObject", cdQyObjectOfYear);

        map.put("sjzQyNum", sjzQyNumOfYear);
        map.put("czQyNum", czQyNumOfYear);
        map.put("cdQyNum", cdQyNumOfYear);
        return map;
    }


}
