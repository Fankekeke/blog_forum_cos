package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.PostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 贴子消息 mapper层
 *
 * @author FanK
 */
public interface PostInfoMapper extends BaseMapper<PostInfo> {

    /**
     * 分页获取贴子消息
     *
     * @param page     分页对象
     * @param postInfo 贴子消息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectPostPage(Page<PostInfo> page, @Param("postInfo") PostInfo postInfo);

    /**
     * 获取贴子消息
     *
     * @param postInfo 贴子消息
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectPostList(@Param("postInfo") PostInfo postInfo);

    /**
     * 根据用户获取贴子信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getPostByUser(@Param("userId") Integer userId);

    /**
     * 获取模块下的贴子
     *
     * @param tagId 模块ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> getPostByTag(@Param("tagId") Integer tagId);

    /**
     * 获取贴子详细信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    LinkedHashMap<String, Object> postDetail(@Param("postId") Integer postId);

    /**
     * 模糊查询帖子信息
     *
     * @param key 关键词
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> postByKey(@Param("key") String key);

    /**
     * 推荐贴子
     *
     * @param tagId          模块ID
     * @param collectUserIds 关注用户
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> recommend(@Param("tagId") Integer tagId, @Param("collectUserIds") String collectUserIds);

    /**
     * 根据贴子ID获取回复信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> replyInfoByPostId(@Param("postId") Integer postId);

    /**
     * 根据贴子ID获取回复信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> replyListByPostId(@Param("postId") Integer postId);

    /**
     * 根据贴子编号获取详细信息
     *
     * @param postId 帖子ID
     * @return 结果
     */
    LinkedHashMap<String, Object> getPostInfoById(@Param("postId") Integer postId);

    /**
     * 十天内发帖统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderNumWithinDays();

    /**
     * 十天内访问记录统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderViewWithinDays();

    /**
     * 根据时间获取发帖数量
     *
     * @param year  年份
     * @param month 月份
     * @return 结果
     */
    Integer selectPostNumByDate(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * 根据时间获取浏览量
     *
     * @param year  年份
     * @param month 月份
     * @return 结果
     */
    Integer selectViewNumByDate(@Param("year") Integer year, @Param("month") Integer month);
}
