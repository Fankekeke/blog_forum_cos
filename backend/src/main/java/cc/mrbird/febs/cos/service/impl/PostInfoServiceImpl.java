package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.*;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.service.IBulletinInfoService;
import cc.mrbird.febs.cos.service.IPostInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * 贴子消息 实现层
 *
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements IPostInfoService {

    private final CollectInfoMapper collectInfoMapper;

    private final FocusInfoMapper focusInfoMapper;

    private final UserInfoMapper userInfoMapper;

    private final UserRecordInfoMapper userRecordInfoMapper;

    private final IBulletinInfoService bulletinInfoService;

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
     * 首页统计信息
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> homeData() {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("userNum", 0);
                put("postNum", 0);
                put("historyNum", 0);
            }
        };


        result.put("userNum", userInfoMapper.selectCount(Wrappers.<UserInfo>lambdaQuery()));
        result.put("postNum", this.count(Wrappers.<PostInfo>lambdaQuery().eq(PostInfo::getDeleteFlag, 0)));
        result.put("historyNum", userRecordInfoMapper.selectCount(Wrappers.<UserRecordInfo>lambdaQuery()));

        Integer year = DateUtil.thisYear();
        Integer month = DateUtil.thisMonth() + 1;
        // 本月发帖数量
        result.put("monthOrderNum", baseMapper.selectPostNumByDate(year, month));
        // 获取本月浏览量
        result.put("monthOrderTotal", baseMapper.selectViewNumByDate(year, month));

        // 本年发帖数量
        result.put("yearOrderNum", baseMapper.selectPostNumByDate(year, null));
        // 本年浏览量
        result.put("yearOrderTotal", baseMapper.selectViewNumByDate(year, null));

        // 近十天发帖统计
        result.put("orderNumDayList", baseMapper.selectOrderNumWithinDays());
        // 近十天浏览统计
        result.put("orderViewDayList", baseMapper.selectOrderViewWithinDays());
        // 公告信息
        result.put("bulletinInfoList", bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, 1)));

        return result;
    }


    /**
     * 查询帖子及用户信息
     *
     * @param key 关键字
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> querySearch(String key) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("post", Collections.emptyList());
                put("user", Collections.emptyList());
            }
        };
        // 帖子
        PostInfo postInfo = new PostInfo();
        postInfo.setTitle(key);
        result.put("post", baseMapper.selectPostList(postInfo));
        // 用户
        UserInfo userInfo = new UserInfo();
        userInfo.setName(key);
        result.put("user", userInfoMapper.selectUserList(userInfo));
        return result;
    }

    /**
     * 根据用户获取贴子信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> getPostByUser(Integer userId) {
        return baseMapper.getPostByUser(userId);
    }

    /**
     * 获取用户及贴子详细信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> getUserPostDetail(Integer userId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("user", null);
                put("post", Collections.emptyList());
            }
        };

        // 用户信息
        UserInfo userInfo = userInfoMapper.selectById(userId);
        //关注与粉丝
        List<FocusInfo> focusInfoList1 = focusInfoMapper.selectList(Wrappers.<FocusInfo>lambdaQuery().eq(FocusInfo::getUserId, userId));
        List<FocusInfo> focusInfoList2 = focusInfoMapper.selectList(Wrappers.<FocusInfo>lambdaQuery().eq(FocusInfo::getCollectUserId, userId));
        userInfo.setFocusNum(focusInfoList1.size());
        userInfo.setFansNum(focusInfoList2.size());
        // 帖子
        result.put("post", baseMapper.getPostByUser(userId));
        return result;
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
     * 根据贴子编号获取详细信息
     *
     * @param postId 帖子ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> getPostInfoById(Integer postId) {
        PostInfo postInfo = this.getById(postId);
        postInfo.setPageviews(postInfo.getPageviews() + 1);
        this.updateById(postInfo);
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("postInfo", baseMapper.getPostInfoById(postId));
        result.put("replyInfo", baseMapper.replyListByPostId(postId));
        return result;
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
