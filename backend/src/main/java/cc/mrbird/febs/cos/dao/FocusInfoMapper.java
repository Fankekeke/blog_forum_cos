package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.FocusInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 关注用户 mapper层
 *
 * @author FanK
 */
public interface FocusInfoMapper extends BaseMapper<FocusInfo> {

    /**
     * 分页获取关注用户
     *
     * @param page      分页对象
     * @param focusInfo 关注用户
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectFocusPage(Page<FocusInfo> page, @Param("focusInfo") FocusInfo focusInfo);
}
