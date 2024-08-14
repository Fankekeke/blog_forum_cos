package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.TagInfo;
import cc.mrbird.febs.cos.service.ITagInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 贴子模块 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/tag-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagInfoController {

    private final ITagInfoService tagInfoService;

    /**
     * 分页获取贴子模块
     *
     * @param page    分页对象
     * @param tagInfo 贴子模块
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<TagInfo> page, TagInfo tagInfo) {
        return R.ok(tagInfoService.selectTagPage(page, tagInfo));
    }

    /**
     * 查询贴子模块 详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(tagInfoService.getById(id));
    }

    /**
     * 查询贴子模块 列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(tagInfoService.list());
    }

    /**
     * 新增贴子模块
     *
     * @param tagInfo 贴子模块
     * @return 结果
     */
    @PostMapping
    public R save(TagInfo tagInfo) {
        return R.ok(tagInfoService.save(tagInfo));
    }

    /**
     * 修改贴子模块
     *
     * @param tagInfo 贴子模块
     * @return 结果
     */
    @PutMapping
    public R edit(TagInfo tagInfo) {
        return R.ok(tagInfoService.updateById(tagInfo));
    }

    /**
     * 删除贴子模块
     *
     * @param ids ids
     * @return 贴子模块
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(tagInfoService.removeByIds(ids));
    }
}
