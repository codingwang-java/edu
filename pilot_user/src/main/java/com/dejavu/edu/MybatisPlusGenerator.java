package com.dejavu.edu;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author dejavu
 * @description
 * @create 2021-03-04 22:53
 **/
public class MybatisPlusGenerator {
        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局策略配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/pilot_user/src/main/java"); // 生成文件的输出目录,默认D根目录
            gc.setFileOverride(true); // 是否覆盖已有文件
            gc.setAuthor("dejavu");
            gc.setOpen(false); // 是否打开输出目录,默认true
            gc.setEnableCache(false); // 是否在xml中添加二级缓存配置,默认false
            gc.setSwagger2(false); // 开启 swagger2 模式,默认false

            gc.setEntityName("%s");
            gc.setMapperName("%sMapper");
            gc.setXmlName("%sMapper");
            gc.setServiceName("I%sService");
            gc.setServiceImplName("%sServiceImpl");
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/edu?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
            dsc.setSchemaName("edu");
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("root");
            mpg.setDataSource(dsc);


            // 包名配置
            PackageConfig pc = new PackageConfig();
            pc.setModuleName("edu");
            pc.setParent("com.dejavu");
            pc.setEntity("entity");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl";

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return projectPath + "/pilot_user/src/main/resources/mapper/"
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();
            // 配置自定义输出模板
            // templateConfig.setEntity();
            // templateConfig.setService();
            templateConfig.setController(null);
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
            strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
            //strategy.setSuperEntityClass("com.shiyu.BaseEntity"); //自定义继承的Entity类全称，带包名
            //strategy.setSuperEntityColumns(new String[] {"id","gmtCreate","gmtModified"});// 自定义基础的Entity类，公共字段
            strategy.setEntityLombokModel(true); // 是否为lombok模型
            strategy.setEntityBooleanColumnRemoveIsPrefix(true); // Boolean类型字段是否移除is前缀
            strategy.setRestControllerStyle(false); // 生成 @RestController 控制器，个人觉得有点多余
            //strategy.setSuperControllerClass("com.music.taosim.ant.common.BaseController");

            //startegy.setInclude("tableName");  当对某张表有所改动但只想重新生成这张表，可以这样设置

            strategy.setControllerMappingHyphenStyle(true); // 驼峰转连字符 如 umps_user 变为 upms/user
            // strategy.setTablePrefix(pc.getModuleName() + "_"); // 表前缀

            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine()); //设置模板引擎类型，默认为 velocity
            mpg.execute();
        }

}
