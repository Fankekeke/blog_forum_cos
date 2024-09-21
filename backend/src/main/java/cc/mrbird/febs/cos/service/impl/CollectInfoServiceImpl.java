package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CollectInfo;
import cc.mrbird.febs.cos.dao.CollectInfoMapper;
import cc.mrbird.febs.cos.service.ICollectInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 帖子收藏 实现层
 *
 * @author FanK
 */
@Service
public class CollectInfoServiceImpl extends ServiceImpl<CollectInfoMapper, CollectInfo> implements ICollectInfoService {

    /**
     * 分页获取帖子收藏
     *
     * @param page        分页对象
     * @param collectInfo 帖子收藏
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectCollectPage(Page<CollectInfo> page, CollectInfo collectInfo) {
        return baseMapper.selectCollectPage(page, collectInfo);
    }

    /**
     * 根据用户获取收藏列表
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectCollectByUser(Integer userId) {
        return baseMapper.selectCollectByUser(userId);
    }
}
