package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.FocusInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 关注用户 service层
 *
 * @author FanK
 */
public interface IFocusInfoService extends IService<FocusInfo> {

    /**
     * 分页获取关注用户
     *
     * @param page      分页对象
     * @param focusInfo 关注用户
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectFocusPage(Page<FocusInfo> page, FocusInfo focusInfo);

    /**
     * 根据用户获取关注用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectFocusByUser(Integer userId);

    /**
     * 关注/取关 用户
     *
     * @param userId      用户ID
     * @param focusUserId 关注用户ID
     * @param type        操作 1.关注 2.取关
     * @return 结果
     */
    Boolean focusUser(Integer userId, Integer focusUserId, Integer type);
}
