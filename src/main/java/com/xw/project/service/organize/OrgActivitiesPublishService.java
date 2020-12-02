package com.xw.project.service.organize;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.entity.OrgActivitiesPublish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dy
 * @since 2020-01-06
 */
public interface OrgActivitiesPublishService extends IService<OrgActivitiesPublish> {
    IPage<OrgActivitiesPublish> listActivitiesForPage();

    OrgActivitiesPublish getActivity(String id);

    boolean saveActivity(OrgActivitiesPublish orgActivitiesPublish);

    boolean removeActivity(String id);

    boolean editActivity(OrgActivitiesPublish orgActivitiesPublish);
}
