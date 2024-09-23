package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.FocusInfo;
import cc.mrbird.febs.cos.dao.FocusInfoMapper;
import cc.mrbird.febs.cos.service.IFocusInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 关注用户 实现层
 *
 * @author FanK
 */
@Service
public class FocusInfoServiceImpl extends ServiceImpl<FocusInfoMapper, FocusInfo> implements IFocusInfoService {

    /**
     * 分页获取关注用户
     *
     * @param page      分页对象
     * @param focusInfo 关注用户
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectFocusPage(Page<FocusInfo> page, FocusInfo focusInfo) {
        return baseMapper.selectFocusPage(page, focusInfo);
    }

    /**
     * 根据用户获取关注用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectFocusByUser(Integer userId) {
        return baseMapper.selectFocusByUser(userId);
    }

    /**
     * 关注/取关 用户
     *
     * @param userId      用户ID
     * @param focusUserId 关注用户ID
     * @param type        操作 1.关注 2.取关
     * @return 结果
     */
    @Override
    public Boolean focusUser(Integer userId, Integer focusUserId, Integer type) {
        if (type == 1) {
            FocusInfo focusInfo = new FocusInfo();
            focusInfo.setUserId(Long.valueOf(userId));
            focusInfo.setCollectUserId(Long.valueOf(focusUserId));
            focusInfo.setDeleteFlag(0);
            focusInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
            return this.save(focusInfo);
        } else {
            return this.remove(Wrappers.<FocusInfo>lambdaQuery().eq(FocusInfo::getUserId, userId).eq(FocusInfo::getCollectUserId, focusUserId));
        }
    }
}
