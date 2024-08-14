package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ChatRecordInfo;
import cc.mrbird.febs.cos.service.IChatRecordInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 聊天记录 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/chat-record-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatRecordInfoController {

    private final IChatRecordInfoService chatRecordInfoService;

    /**
     * 分页获取聊天记录
     *
     * @param page           分页对象
     * @param chatRecordInfo 聊天记录
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ChatRecordInfo> page, ChatRecordInfo chatRecordInfo) {
        return R.ok(chatRecordInfoService.selectChatRecordPage(page, chatRecordInfo));
    }

    /**
     * 查询聊天记录详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(chatRecordInfoService.getById(id));
    }

    /**
     * 查询聊天记录列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(chatRecordInfoService.list());
    }

    /**
     * 新增聊天记录
     *
     * @param chatRecordInfo 聊天记录
     * @return 结果
     */
    @PostMapping
    public R save(ChatRecordInfo chatRecordInfo) {
        return R.ok(chatRecordInfoService.save(chatRecordInfo));
    }

    /**
     * 修改聊天记录
     *
     * @param chatRecordInfo 聊天记录
     * @return 结果
     */
    @PutMapping
    public R edit(ChatRecordInfo chatRecordInfo) {
        return R.ok(chatRecordInfoService.updateById(chatRecordInfo));
    }

    /**
     * 删除聊天记录
     *
     * @param ids ids
     * @return 聊天记录
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(chatRecordInfoService.removeByIds(ids));
    }
}
