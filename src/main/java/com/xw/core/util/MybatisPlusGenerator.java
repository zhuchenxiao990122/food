package com.xw.core.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.xw.common.entity.BasePlusEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisPlusGenerator {
    private final static Map<String, Boolean> GEN_FLAG = new HashMap<String, Boolean>();

    private final static Map<String, Boolean> OVERWRITE_FLAG = new HashMap<String, Boolean>();

    //是否自动生成标记
    static {
        GEN_FLAG.put("controller", true);
        GEN_FLAG.put("service", true);
        GEN_FLAG.put("serviceImpl", true);
        GEN_FLAG.put("mapper", true);
        GEN_FLAG.put("xml", true);
        GEN_FLAG.put("dto", true);

        //是否覆盖标记
        OVERWRITE_FLAG.put("controller", false);
        OVERWRITE_FLAG.put("service", false);
        OVERWRITE_FLAG.put("serviceImpl", false);
        OVERWRITE_FLAG.put("mapper", false);
        OVERWRITE_FLAG.put("xml", false);
        OVERWRITE_FLAG.put("dto", false);
    }

    //项目模块路径
    private static String baseCommPath = System.getProperty("user.dir");
    //生成路径
    private static String baseProjectPath = baseCommPath + "/src/main/java/" ;
    //基本包名
    private static String baseProjectPackage = "com.xw.project" ;
    //实体类生成地址
    private static String baseCommPackage = "com.xw.project.entity" ;

    /*
     *需要自己配置的
     */
    //模块名
    private static String projectName = "disaster" ;
    //作者
    private static String authorName = "yuli" ;
    //要生成的表名
    private static String[] tables = {"dis_fund_allocation"};

    //数据库配置四要素
    private static String driverName = "com.mysql.cj.jdbc.Driver" ;
    private static String url = "jdbc:mysql://hsdb.bhmes.cn:3306/dev_zdrc?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowMultiQueries=true" ;
    private static String username = "dev" ;
    private static String password = "dev" ;


    private static void generator(Boolean flag) {

        AutoGenerator gen = new AutoGenerator();
        /**
         * 数据库配置
         */
        gen.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
        );

        /**
         * 全局配置
         */
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(baseCommPath + "/src/main/java")//输出目录
                .setFileOverride(flag)// 是否覆盖文件
                .setActiveRecord(false)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setOpen(false)//生成后打开文件夹
                .setAuthor(authorName)

                .setEntityName("%s")
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController");

        gen.setGlobalConfig(gc);

        /**
         * 策略配置
         */
        gen.setStrategy(new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setInclude(tables) // 需要生成的表
                .setRestControllerStyle(true)
                // 自定义实体父类
                .setSuperEntityClass(BasePlusEntity.class)
                // 自定义实体，公共字段
                .setSuperEntityColumns(new String[]{"id", "remarks", "del_flag", "create_date", "create_by", "update_date", "update_by"})
                .setSuperControllerClass("com.xw.common.controller.BaseController")
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(true)
        );
        /**
         * 包配置
         */
        gen.setPackageInfo(new PackageConfig()
                .setParent(baseProjectPackage)// 自定义包路径
                .setController("controller." + projectName)// 这里是控制器包名，默认 web
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service." + projectName)
                .setServiceImpl("service." + projectName + ".impl")
                .setXml("mapper")

        );

        //屏蔽默认的生成
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity(null);
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        tc.setMapper(null);
        tc.setXml(null);
        gen.setTemplate(tc);

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        initFileOutConfig(flag, focList);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>();
                map.put("projectName", projectName);
                this.setMap(map);
            }
        };
        cfg.setFileOutConfigList(focList);

        gen.setCfg(cfg);
        // 执行一次覆盖生成生成
        gen.execute();
    }

    private static void initFileOutConfig(Boolean flag, List<FileOutConfig> focList) {
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseCommPackage.replaceAll("\\.", "/") + "/" + tableInfo.getEntityName() + ".java" ;
            }
        });
        FileOutConfig entityConfig = new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseCommPackage.replaceAll("\\.", "/") + "/" + tableInfo.getEntityName() + ".java" ;
            }
        };
        FileOutConfig serviceConfig = new FileOutConfig("/templates/service.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseProjectPackage.replaceAll("\\.", "/") + "/service/" + projectName + "/" + tableInfo.getEntityName() + "Service.java" ;
            }
        };
        FileOutConfig serviceImplConfig = new FileOutConfig("/templates/serviceImpl.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseProjectPackage.replaceAll("\\.", "/") + "/service/" + projectName + "/impl/" + tableInfo.getEntityName() + "ServiceImpl.java" ;
            }
        };
        FileOutConfig controllerConfig = new FileOutConfig("/templates/controller.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseProjectPackage.replaceAll("\\.", "/") + "/controller/" + projectName + "/" + tableInfo.getEntityName() + "Controller.java" ;
            }
        };
        FileOutConfig xmlConfig = new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseCommPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml" ;
            }
        };
        FileOutConfig mapperConfig = new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseProjectPackage.replaceAll("\\.", "/") + "/mapper/" + tableInfo.getEntityName() + "Mapper.java" ;
            }
        };
        FileOutConfig dtoConfig = new FileOutConfig("/templates/dto.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseProjectPath + baseProjectPackage.replaceAll("\\.", "/") + "/dto/" + tableInfo.getEntityName() + "Dto.java" ;
            }
        };
        focList.add(entityConfig);
        if (flag) {
            if (GEN_FLAG.get("service") && OVERWRITE_FLAG.get("service")) {
                focList.add(serviceConfig);
            }
            if (GEN_FLAG.get("serviceImpl") && OVERWRITE_FLAG.get("serviceImpl")) {
                focList.add(serviceImplConfig);
            }
            if ((GEN_FLAG.get("controller") && OVERWRITE_FLAG.get("controller"))) {
                focList.add(controllerConfig);
            }
            if (GEN_FLAG.get("xml") && OVERWRITE_FLAG.get("xml")) {
                focList.add(xmlConfig);
            }
            if (GEN_FLAG.get("mapper") && OVERWRITE_FLAG.get("mapper")) {
                focList.add(mapperConfig);
            }
            if (GEN_FLAG.get("dto") && OVERWRITE_FLAG.get("dto")) {
                focList.add(dtoConfig);
            }
        } else {
            if (GEN_FLAG.get("service")) {
                focList.add(serviceConfig);
            }
            if (GEN_FLAG.get("serviceImpl")) {
                focList.add(serviceImplConfig);
            }
            if (GEN_FLAG.get("controller")) {
                focList.add(controllerConfig);
            }
            if (GEN_FLAG.get("xml")) {
                focList.add(xmlConfig);
            }
            if (GEN_FLAG.get("mapper")) {
                focList.add(mapperConfig);
            }
            if (GEN_FLAG.get("dto")) {
                focList.add(dtoConfig);
            }
        }

    }

    public static void main(String[] args) {
        generator(true);
        generator(false);

    }

}
