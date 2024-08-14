package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.TagInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 贴子模块 service层
 *
 * @author FanK
 */
public interface ITagInfoService extends IService<TagInfo> {

    /**
     * 分页获取贴子模块
     *
     * @param page    分页对象
     * @param tagInfo 贴子模块
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectTagPage(Page<TagInfo> page, TagInfo tagInfo);
}
