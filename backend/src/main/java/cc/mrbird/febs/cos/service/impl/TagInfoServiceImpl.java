package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.TagInfo;
import cc.mrbird.febs.cos.dao.TagInfoMapper;
import cc.mrbird.febs.cos.service.ITagInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 贴子模块 实现层
 *
 * @author FanK
 */
@Service
public class TagInfoServiceImpl extends ServiceImpl<TagInfoMapper, TagInfo> implements ITagInfoService {

    /**
     * 分页获取贴子模块
     *
     * @param page    分页对象
     * @param tagInfo 贴子模块
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectTagPage(Page<TagInfo> page, TagInfo tagInfo) {
        return baseMapper.selectTagPage(page, tagInfo);
    }
}
