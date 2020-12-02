package com.xw.project.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xw.common.entity.BasePlusEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xw.core.constant.DocConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * <p>
 *
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dis_disaster_publish")
public class DisDisasterPublish extends BasePlusEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 上报组织
     */
    @NotEmpty
    private String publishOrg;

    /**
     * 上报编号
     */
    @NotEmpty
    private String publishCode;

    /**
     * 接收组织
     */
    @NotEmpty
    private String receiveOrgId;

    /**
     * 灾害类型
     */
    @NotEmpty
    private String disasterType;


    /**
     * 开始时间
     */
    @Past
    @NotNull
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date startTime;
    /**
     * 结束时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endTime;

    /**
     * 区域数量(地)
     */
    private BigDecimal areaPrefectural;

    /**
     * 区域数量(县)
     */
    private BigDecimal areaCounty;

    /**
     * 区域数量(乡)
     */
    private BigDecimal areaCountryside;

    /**
     * 区域数量(村)
     */
    private BigDecimal areaVillage;

    /**
     * 受灾人口
     */
    private BigDecimal disasterPopulation;

    /**
     * 死亡人口
     */
    private BigDecimal deathPopulation;

    /**
     * 失踪人口
     */
    private BigDecimal missingPopulation;

    /**
     * 受伤人口
     */
    private BigDecimal injuredPopulation;

    /**
     * 转移人口
     */
    private BigDecimal transferPopulation;

    /**
     * 需救助人口
     */
    private BigDecimal relievePopulation;

    /**
     * 房屋坍塌数
     */
    private BigDecimal housingCollapse;

    /**
     * 房屋损害数
     */
    private BigDecimal housingDamage;

    /**
     * 农田受损（公顷）
     */
    private BigDecimal farmlandDamage;

    /**
     * 农作物/草场受灾面积（公顷)
     */
    private BigDecimal cropsDamage;

    /**
     * 直接经济损失
     */
    private BigDecimal directLoss;

    /**
     * 间接经济损失
     */
    @TableField(" Indirect_loss")
    private BigDecimal IndirectLoss;

    /**
     * 灾情描述
     */
    private String disasterDescribe;

    /**
     * 当地救助行动
     */
    private String rescueAction;

    /**
     * 当地媒体报道
     */
    private String mediaReport;

    /**
     * 备注
     */
    private String remarks;




    public static final String REPORT_ORG = "report_org";

    public static final String REPORT_CODE = "report_code";

    public static final String DISASTER_TYPE = "disaster_type";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String AREA_PREFECTURAL = "area_prefectural";

    public static final String AREA_COUNTY = "area_county";

    public static final String AREA_COUNTRYSIDE = "area_countryside";

    public static final String AREA_VILLAGE = "area_village";

    public static final String DISASTER_POPULATION = "disaster_population";

    public static final String DEATH_POPULATION = "death_population";

    public static final String MISSING_POPULATION = "missing_population";

    public static final String INJURED_POPULATION = "injured_population";

    public static final String TRANSFER_POPULATION = "transfer_population";

    public static final String RELIEVE_POPULATION = "relieve_population";

    public static final String HOUSING_COLLAPSE = "housing_collapse";

    public static final String HOUSING_DAMAGE = "housing_damage";

    public static final String FARMLAND_DAMAGE = "farmland_damage";

    public static final String CROPS_DAMAGE = "crops_damage";

    public static final String DIRECT_LOSS = "direct_loss";

    public static final String INDIRECT_LOSS = "Indirect_loss";

    public static final String DISASTER_DESCRIBE = "disaster_describe";

    public static final String RESCUE_ACTION = "rescue_action";

    public static final String MEDIA_REPORT = "media_report";

    public static final String REMARKS = "remarks";

    @Override
    public String toString() {
        return "DisDisasterPublish{" +
                "reportOrg=" + publishOrg +
                ", reportCode=" + publishCode +
                ", disasterType=" + disasterType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", areaPrefectural=" + areaPrefectural +
                ", areaCounty=" + areaCounty +
                ", areaCountryside=" + areaCountryside +
                ", areaVillage=" + areaVillage +
                ", disasterPopulation=" + disasterPopulation +
                ", deathPopulation=" + deathPopulation +
                ", missingPopulation=" + missingPopulation +
                ", injuredPopulation=" + injuredPopulation +
                ", transferPopulation=" + transferPopulation +
                ", relievePopulation=" + relievePopulation +
                ", housingCollapse=" + housingCollapse +
                ", housingDamage=" + housingDamage +
                ", farmlandDamage=" + farmlandDamage +
                ", cropsDamage=" + cropsDamage +
                ", directLoss=" + directLoss +
                ", IndirectLoss=" + IndirectLoss +
                ", disasterDescribe=" + disasterDescribe +
                ", rescueAction=" + rescueAction +
                ", mediaReport=" + mediaReport +
                ", remarks=" + remarks +
                "}";
    }
}
