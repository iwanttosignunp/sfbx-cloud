package com.itheima.sfbx.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.sfbx.framework.commons.exception.ProjectException;
import com.itheima.sfbx.framework.commons.utils.BeanConv;
import com.itheima.sfbx.framework.commons.utils.EmptyUtil;
import com.itheima.sfbx.framework.commons.utils.ExceptionsUtil;
import com.itheima.sfbx.insurance.dto.SafeguardVO;
import com.itheima.sfbx.insurance.enums.SafeguardEnum;
import com.itheima.sfbx.insurance.mapper.SafeguardMapper;
import com.itheima.sfbx.insurance.pojo.Safeguard;
import com.itheima.sfbx.insurance.service.ISafeguardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SafeguardServiceImpl extends ServiceImpl<SafeguardMapper, Safeguard> implements ISafeguardService{
    @Override
    public Page<SafeguardVO> findPage(SafeguardVO safeguardVO, int pageNum, int pageSize) {
        Page<Safeguard> safeguardPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Safeguard> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardKey()), Safeguard::getSafeguardKey, safeguardVO.getSafeguardKey());
        queryWrapper.like(!EmptyUtil.isNullOrEmpty(safeguardVO.getSafeguardKeyName()), Safeguard::getSafeguardKeyName, safeguardVO.getSafeguardKeyName());
        queryWrapper.eq(!EmptyUtil.isNullOrEmpty(safeguardVO.getDataState()), Safeguard::getDataState, safeguardVO.getDataState());
        queryWrapper.orderByDesc(Safeguard::getCreateTime);
        Page<Safeguard> resultPage = page(safeguardPage, queryWrapper);
        Page<SafeguardVO> safeguardVOPage = BeanConv.toPage(resultPage, SafeguardVO.class);
        return safeguardVOPage;
    }

    @Override
    public SafeguardVO findById(String safeguardId) {
        return null;
    }

    @Override
    public SafeguardVO save(SafeguardVO safeguardVO) {
        try{
            Safeguard safeguard = BeanConv.toBean(safeguardVO, Safeguard.class);
            boolean flag = save(safeguard);
            if(!flag){
                throw new RuntimeException("保存保障项失败");
            }
            return BeanConv.toBean(safeguard, SafeguardVO.class);
        }catch (Exception e) {
            log.error("保存保障项失败：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.SAVE_FAIL);
        }
    }

    @Override
    public Boolean update(SafeguardVO safeguardVO) {
        try {
            Safeguard safeguard = BeanConv.toBean(safeguardVO, Safeguard.class);
            boolean flag = updateById(safeguard);
            if (!flag) {
                throw new RuntimeException("更新保障项失败！");
            }
            return flag;
        } catch (Exception e) {
            log.error("更新保障项失败：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.UPDATE_FAIL);
        }
    }

    @Override
    public Boolean delete(String[] checkedIds) {
        try{
            List<Long> idsList = Arrays.asList(checkedIds).stream().map(Long::new).collect(Collectors.toList());
            boolean flag = removeByIds(idsList);
            if(!flag){
                throw new RuntimeException("删除保障项失败");
            }
            return flag;
        } catch (Exception e) {
            log.error("删除保障项失败：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(SafeguardEnum.DEL_FAIL);
        }
    }

    @Override
    public List<SafeguardVO> findList(SafeguardVO safeguardVO) {
        return List.of();
    }

    @Override
    public List<SafeguardVO> findShowPageItemByKey(List<String> safeguardKeyList) {
        return List.of();
    }

    @Override
    public SafeguardVO findBySafeguardKey(String safeguardKey) {
        return null;
    }
}
