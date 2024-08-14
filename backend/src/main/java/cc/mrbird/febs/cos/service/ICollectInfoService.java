package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.CollectInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 帖子收藏 service层
 *
 * @author FanK
 */
public interface ICollectInfoService extends IService<CollectInfo> {

    /**
     * 分页获取帖子收藏
     *
     * @param page        分页对象
     * @param collectInfo 帖子收藏
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectCollectPage(Page<CollectInfo> page, CollectInfo collectInfo);
}
