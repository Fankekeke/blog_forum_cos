package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MessageInfo;
import cc.mrbird.febs.cos.service.IMessageInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统消息 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/message-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageInfoController {

    private final IMessageInfoService messageInfoService;

    /**
     * 分页获取系统消息
     *
     * @param page        分页对象
     * @param messageInfo 系统消息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MessageInfo> page, MessageInfo messageInfo) {
        return R.ok(messageInfoService.selectMessagePage(page, messageInfo));
    }

    /**
     * 查询系统消息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(messageInfoService.getById(id));
    }

    /**
     * 查询系统消息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(messageInfoService.list());
    }

    /**
     * 新增系统消息
     *
     * @param messageInfo 系统消息
     * @return 结果
     */
    @PostMapping
    public R save(MessageInfo messageInfo) {
        return R.ok(messageInfoService.save(messageInfo));
    }

    /**
     * 修改系统消息
     *
     * @param messageInfo 系统消息
     * @return 结果
     */
    @PutMapping
    public R edit(MessageInfo messageInfo) {
        return R.ok(messageInfoService.updateById(messageInfo));
    }

    /**
     * 删除系统消息
     *
     * @param ids ids
     * @return 系统消息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(messageInfoService.removeByIds(ids));
    }
}
