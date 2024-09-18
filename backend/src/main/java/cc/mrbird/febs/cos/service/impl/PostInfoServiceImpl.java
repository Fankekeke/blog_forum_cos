package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.dao.PostInfoMapper;
import cc.mrbird.febs.cos.service.IPostInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 贴子消息 实现层
 *
 * @author FanK
 */
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements IPostInfoService {

    /**
     * 分页获取贴子消息
     *
     * @param page     分页对象
     * @param postInfo 贴子消息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectPostPage(Page<PostInfo> page, PostInfo postInfo) {
        return baseMapper.selectPostPage(page, postInfo);
    }

    /**
     * 获取模块下的贴子
     *
     * @param tagId 模块ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getPostByTag(Integer tagId) {
        return baseMapper.getPostByTag(tagId);
    }

    /**
     * 获取贴子详细信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> postDetail(Integer postId) {
        return baseMapper.postDetail(postId);
    }

    /**
     * 模糊查询帖子信息
     *
     * @param key 关键词
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> postByKey(String key) {
        return baseMapper.postByKey(key);
    }

    /**
     * 推荐贴子
     *
     * @param tagId          模块ID
     * @param collectUserIds 关注用户
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> recommend(Integer tagId, List<Long> collectUserIds) {
        return baseMapper.recommend(tagId, StringUtils.join(collectUserIds.toArray(), ","));
    }
}
