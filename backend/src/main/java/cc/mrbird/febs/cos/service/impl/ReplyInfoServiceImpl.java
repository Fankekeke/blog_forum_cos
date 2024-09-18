package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ReplyInfo;
import cc.mrbird.febs.cos.dao.ReplyInfoMapper;
import cc.mrbird.febs.cos.service.IReplyInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 回复管理 实现层
 *
 * @author FanK
 */
@Service
public class ReplyInfoServiceImpl extends ServiceImpl<ReplyInfoMapper, ReplyInfo> implements IReplyInfoService {

    /**
     * 分页获取回复管理
     *
     * @param page      分页对象
     * @param replyInfo 回复管理
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectReplyPage(Page<ReplyInfo> page, ReplyInfo replyInfo) {
        return baseMapper.selectReplyPage(page, replyInfo);
    }

    /**
     * 获取具体的帖子回复信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> replyListByPostId(Integer postId) {
        return baseMapper.replyListByPostId(postId);
    }
}
