package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.TagInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 贴子模块 mapper层
 *
 * @author FanK
 */
public interface TagInfoMapper extends BaseMapper<TagInfo> {

    /**
     * 分页获取贴子模块
     *
     * @param page    分页对象
     * @param tagInfo 贴子模块
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectTagPage(Page<TagInfo> page, @Param("tagInfo") TagInfo tagInfo);
}
