package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.service.IPostInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 贴子消息 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/post-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostInfoController {

    private final IPostInfoService postInfoService;

    /**
     * 分页获取贴子消息
     *
     * @param page     分页对象
     * @param postInfo 贴子消息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<PostInfo> page, PostInfo postInfo) {
        return R.ok(postInfoService.selectPostPage(page, postInfo));
    }

    /**
     * 查询贴子消息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(postInfoService.getById(id));
    }

    /**
     * 查询贴子消息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(postInfoService.list());
    }

    /**
     * 新增贴子消息
     *
     * @param postInfo 贴子消息
     * @return 结果
     */
    @PostMapping
    public R save(PostInfo postInfo) {
        return R.ok(postInfoService.save(postInfo));
    }

    /**
     * 修改贴子消息
     *
     * @param postInfo 贴子消息
     * @return 结果
     */
    @PutMapping
    public R edit(PostInfo postInfo) {
        return R.ok(postInfoService.updateById(postInfo));
    }

    /**
     * 删除贴子消息
     *
     * @param ids ids
     * @return 贴子消息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(postInfoService.removeByIds(ids));
    }
}
