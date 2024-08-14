package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.FocusInfo;
import cc.mrbird.febs.cos.service.IFocusInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关注用户 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/focus-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FocusInfoController {

    private final IFocusInfoService focusInfoService;

    /**
     * 分页获取关注用户
     *
     * @param page      分页对象
     * @param focusInfo 关注用户
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<FocusInfo> page, FocusInfo focusInfo) {
        return R.ok(focusInfoService.selectFocusPage(page, focusInfo));
    }

    /**
     * 查询关注用户详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(focusInfoService.getById(id));
    }

    /**
     * 查询关注用户列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(focusInfoService.list());
    }

    /**
     * 新增关注用户
     *
     * @param focusInfo 关注用户
     * @return 结果
     */
    @PostMapping
    public R save(FocusInfo focusInfo) {
        return R.ok(focusInfoService.save(focusInfo));
    }

    /**
     * 修改关注用户
     *
     * @param focusInfo 关注用户
     * @return 结果
     */
    @PutMapping
    public R edit(FocusInfo focusInfo) {
        return R.ok(focusInfoService.updateById(focusInfo));
    }

    /**
     * 删除关注用户
     *
     * @param ids ids
     * @return 关注用户
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(focusInfoService.removeByIds(ids));
    }
}
