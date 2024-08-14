package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CollectInfo;
import cc.mrbird.febs.cos.service.ICollectInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子收藏 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/collect-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollectInfoController {

    private final ICollectInfoService collectInfoService;

    /**
     * 分页获取帖子收藏
     *
     * @param page        分页对象
     * @param collectInfo 帖子收藏
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CollectInfo> page, CollectInfo collectInfo) {
        return R.ok(collectInfoService.selectCollectPage(page, collectInfo));
    }

    /**
     * 查询帖子收藏详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(collectInfoService.getById(id));
    }

    /**
     * 查询帖子收藏列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(collectInfoService.list());
    }

    /**
     * 新增帖子收藏
     *
     * @param collectInfo 帖子收藏
     * @return 结果
     */
    @PostMapping
    public R save(CollectInfo collectInfo) {
        return R.ok(collectInfoService.save(collectInfo));
    }

    /**
     * 修改帖子收藏
     *
     * @param collectInfo 帖子收藏
     * @return 结果
     */
    @PutMapping
    public R edit(CollectInfo collectInfo) {
        return R.ok(collectInfoService.updateById(collectInfo));
    }

    /**
     * 删除帖子收藏
     *
     * @param ids ids
     * @return 帖子收藏
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(collectInfoService.removeByIds(ids));
    }
}
