package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.entity.ReplyInfo;
import cc.mrbird.febs.cos.service.IPostInfoService;
import cc.mrbird.febs.cos.service.IReplyInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回复管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/reply-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReplyInfoController {

    private final IReplyInfoService replyInfoService;

    private final IPostInfoService postInfoService;

    /**
     * 分页获取回复管理
     *
     * @param page      分页对象
     * @param replyInfo 回复管理
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ReplyInfo> page, ReplyInfo replyInfo) {
        return R.ok(replyInfoService.selectReplyPage(page, replyInfo));
    }

    /**
     * 获取具体的帖子回复信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    @GetMapping("/list/{id}")
    public R replyListByPostId(@PathVariable(value = "id") Integer postId) {
        PostInfo postInfo = postInfoService.getById(postId);
        postInfoService.update(Wrappers.<PostInfo>lambdaUpdate().set(PostInfo::getPageviews, postInfo.getPageviews() + 1).eq(PostInfo::getId, postId));
        return R.ok(replyInfoService.replyListByPostId(postId));
    }

    /**
     * 查询回复管理 详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(replyInfoService.getById(id));
    }

    /**
     * 查询回复管理 列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(replyInfoService.list());
    }

    /**
     * 新增回复管理
     *
     * @param replyInfo 回复管理
     * @return 结果
     */
    @PostMapping
    public R save(ReplyInfo replyInfo) {
        return R.ok(replyInfoService.save(replyInfo));
    }

    /**
     * 修改回复管理
     *
     * @param replyInfo 回复管理
     * @return 结果
     */
    @PutMapping
    public R edit(ReplyInfo replyInfo) {
        return R.ok(replyInfoService.updateById(replyInfo));
    }

    /**
     * 删除回复管理
     *
     * @param ids ids
     * @return 回复管理
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(replyInfoService.removeByIds(ids));
    }
}
