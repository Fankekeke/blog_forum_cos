package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.SensitiveInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 敏感词管理 service层
 *
 * @author FanK
 */
public interface ISensitiveInfoService extends IService<SensitiveInfo> {

    /**
     * 分页获取敏感词管理
     *
     * @param page          分页对象
     * @param sensitiveInfo 敏感词管理
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectSensitivePage(Page<SensitiveInfo> page, SensitiveInfo sensitiveInfo);
}
