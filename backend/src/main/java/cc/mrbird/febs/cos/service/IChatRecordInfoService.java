package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ChatRecordInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 聊天记录 service层
 *
 * @author FanK
 */
public interface IChatRecordInfoService extends IService<ChatRecordInfo> {

    /**
     * 分页获取聊天记录
     *
     * @param page           分页对象
     * @param chatRecordInfo 聊天记录
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectChatRecordPage(Page<ChatRecordInfo> page, ChatRecordInfo chatRecordInfo);
}
