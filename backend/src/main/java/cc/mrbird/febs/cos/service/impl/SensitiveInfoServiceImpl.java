package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SensitiveInfo;
import cc.mrbird.febs.cos.dao.SensitiveInfoMapper;
import cc.mrbird.febs.cos.service.ISensitiveInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 敏感词管理 实现层
 *
 * @author FanK
 */
@Service
public class SensitiveInfoServiceImpl extends ServiceImpl<SensitiveInfoMapper, SensitiveInfo> implements ISensitiveInfoService {

    /**
     * 分页获取敏感词管理
     *
     * @param page          分页对象
     * @param sensitiveInfo 敏感词管理
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSensitivePage(Page<SensitiveInfo> page, SensitiveInfo sensitiveInfo) {
        return baseMapper.selectSensitivePage(page,sensitiveInfo);
    }
}
