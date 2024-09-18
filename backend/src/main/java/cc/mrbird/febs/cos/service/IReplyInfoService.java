package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ReplyInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 回复管理 service层
 *
 * @author FanK
 */
public interface IReplyInfoService extends IService<ReplyInfo> {

    /**
     * 分页获取回复管理
     *
     * @param page      分页对象
     * @param replyInfo 回复管理
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectReplyPage(Page<ReplyInfo> page, ReplyInfo replyInfo);

    /**
     * 获取具体的帖子回复信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> replyListByPostId(Integer postId);
}
