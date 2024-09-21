package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ReplyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 回复管理 mapper层
 *
 * @author FanK
 */
public interface ReplyInfoMapper extends BaseMapper<ReplyInfo> {

    /**
     * 分页获取回复管理
     *
     * @param page      分页对象
     * @param replyInfo 回复管理
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectReplyPage(Page<ReplyInfo> page, @Param("replyInfo") ReplyInfo replyInfo);

    /**
     * 获取用户回复信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> replyListByUserId(@Param("userId") Integer userId);

    /**
     * 获取具体的帖子回复信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> replyListByPostId(@Param("postId") Integer postId);
}
