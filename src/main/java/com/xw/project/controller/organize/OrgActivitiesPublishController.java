package com.xw.project.controller.organize;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.project.entity.OrgActivitiesPublish;
import com.xw.project.service.organize.OrgActivitiesPublishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dy
 * @since 2020-01-06
 */
@Slf4j
@RestController
@RequestMapping("/orgActivitiesPublish")
@Api(value = "活动发布")
public class OrgActivitiesPublishController {
    @Autowired
    private OrgActivitiesPublishService orgActivitiesPublishService;

    @GetMapping("page")
    @ApiOperation(value = "活动发布分页查询")
    public RestResponse<IPage<OrgActivitiesPublish>> page(){
        log.info("开始分页查询活动发布信息");
        try {
            return ResultGenerator.genSuccessResult(orgActivitiesPublishService.listActivitiesForPage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    @GetMapping
    @ApiOperation(value = "根据id获取一个活动信息")
    public RestResponse<OrgActivitiesPublish> getActivity(@RequestParam(value = "id") String id){
        log.info("根据id获取一个活动信息");
        try {
            return ResultGenerator.genSuccessResult(orgActivitiesPublishService.getActivity(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("获取活动信息失败");
        }
    }

    @PostMapping
    @ApiOperation(value = "添加活动信息")
    public RestResponse<Boolean> save(@RequestBody OrgActivitiesPublish orgActivitiesPublish){
        try {
            return ResultGenerator.genSuccessResult(orgActivitiesPublishService.saveActivity(orgActivitiesPublish));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("添加活动信息失败");
        }
    }

    @DeleteMapping
    @ApiOperation(value = "删除活动信息")
    public RestResponse<Boolean> remove(@RequestParam("id") String id){
        try {
            return ResultGenerator.genSuccessResult(orgActivitiesPublishService.removeActivity(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("删除活动信息失败");
        }
    }

    @PutMapping
    @ApiOperation(value = "修改活动信息")
    public RestResponse<Boolean> edit(@RequestBody OrgActivitiesPublish orgActivitiesPublish){
        try {
            return ResultGenerator.genSuccessResult(orgActivitiesPublishService.editActivity(orgActivitiesPublish));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("修改活动信息失败");
        }
    }
}
