package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CollectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 帖子收藏 mapper层
 *
 * @author FanK
 */
public interface CollectInfoMapper extends BaseMapper<CollectInfo> {

    /**
     * 分页获取帖子收藏
     *
     * @param page        分页对象
     * @param collectInfo 帖子收藏
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectCollectPage(Page<CollectInfo> page, @Param("collectInfo") CollectInfo collectInfo);

    /**
     * 根据用户获取收藏列表
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectCollectByUser(Integer userId);
}
