package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SensitiveInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 敏感词管理 mapper层
 *
 * @author FanK
 */
public interface SensitiveInfoMapper extends BaseMapper<SensitiveInfo> {

    /**
     * 分页获取敏感词管理
     *
     * @param page          分页对象
     * @param sensitiveInfo 敏感词管理
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSensitivePage(Page<SensitiveInfo> page, @Param("sensitiveInfo") SensitiveInfo sensitiveInfo);
}
