package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SensitiveInfo;
import cc.mrbird.febs.cos.service.ISensitiveInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 敏感词管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/sensitive-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SensitiveInfoController {

    private final ISensitiveInfoService sensitiveInfoService;

    /**
     * 分页获取敏感词管理
     *
     * @param page          分页对象
     * @param sensitiveInfo 敏感词管理
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SensitiveInfo> page, SensitiveInfo sensitiveInfo) {
        return R.ok(sensitiveInfoService.selectSensitivePage(page, sensitiveInfo));
    }

    /**
     * 查询敏感词管理 详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(sensitiveInfoService.getById(id));
    }

    /**
     * 查询敏感词管理 列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(sensitiveInfoService.list());
    }

    /**
     * 新增敏感词管理
     *
     * @param sensitiveInfo 敏感词管理
     * @return 结果
     */
    @PostMapping
    public R save(SensitiveInfo sensitiveInfo) {
        return R.ok(sensitiveInfoService.save(sensitiveInfo));
    }

    /**
     * 修改敏感词管理
     *
     * @param sensitiveInfo 敏感词管理
     * @return 结果
     */
    @PutMapping
    public R edit(SensitiveInfo sensitiveInfo) {
        return R.ok(sensitiveInfoService.updateById(sensitiveInfo));
    }

    /**
     * 删除敏感词管理
     *
     * @param ids ids
     * @return 敏感词管理
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(sensitiveInfoService.removeByIds(ids));
    }
}
