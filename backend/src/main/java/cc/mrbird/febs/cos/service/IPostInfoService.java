package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.FocusInfo;
import cc.mrbird.febs.cos.entity.PostInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 贴子消息 service层
 *
 * @author FanK
 */
public interface IPostInfoService extends IService<PostInfo> {

    /**
     * 分页获取贴子消息
     *
     * @param page     分页对象
     * @param postInfo 贴子消息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectPostPage(Page<PostInfo> page, PostInfo postInfo);

    /**
     * 首页统计信息
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> homeData();

    /**
     * 查询帖子及用户信息
     *
     * @param key 关键字
     * @return 结果
     */
    LinkedHashMap<String, Object> querySearch(String key);

    /**
     * 根据用户获取贴子信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getPostByUser(Integer userId);

    /**
     * 获取用户及贴子详细信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    LinkedHashMap<String, Object> getUserPostDetail(Integer userId);

    /**
     * 获取模块下的贴子
     *
     * @param tagId 模块ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getPostByTag(Integer tagId);

    /**
     * 获取贴子详细信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    LinkedHashMap<String, Object> postDetail(Integer postId);

    /**
     * 根据贴子编号获取详细信息
     *
     * @param postId 帖子ID
     * @return 结果
     */
    LinkedHashMap<String, Object> getPostInfoById(Integer postId);

    /**
     * 模糊查询帖子信息
     *
     * @param key 关键词
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> postByKey(String key);

    /**
     * 推荐贴子
     *
     * @param tagId          模块ID
     * @param collectUserIds 关注用户
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> recommend(Integer tagId, List<Long> collectUserIds);

    /**
     * 关注用户发送邮件
     *
     * @param focusInfoList 关注用户
     * @param content       消息内容
     */
    void emailSendFocus(List<FocusInfo> focusInfoList, String content);

    /**
     * 贴子回复发送邮件
     *
     * @param userId  用户ID
     * @param content 消息内容
     */
    void emailSendReply(Integer userId, String content);
}
