package com.fengwenyi.codegenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.mysql.cj.jdbc.Driver;
import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

/**
 * @author Erwin Feng
 * @since 2019-04-12 01:48
 */
public class CodeGeneratorTest {

    /**
     * 是否强制带上注解
     */
    private boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    private IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    private String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * <p>默认是以I开头</p>
     * <p>user表 -> IUserService, UserServiceImpl</p>
     */
    private boolean serviceClassNameStartWithI = true;

    /** author */
    private String author = "Erwin Feng";

    /** out path */
    private String outPath = "/download";

    @Test
    void generateCode() {
        String packageName = "com.baomidou.springboot";
//        enableTableFieldAnnotation = false;
//        tableIdType = null;
//        generateByTables(packageName + ".noannoidtype", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = null;
//        generateByTables(packageName + ".noidtype", "user");
//        enableTableFieldAnnotation = false;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".noanno", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".both", "user");

//        fieldPrefix = new String[]{"test"};
//        enableTableFieldAnnotation = false;
//        tableIdType = null;
//        generateByTables(packageName + ".noannoidtypewithprefix", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = null;
//        generateByTables(packageName + ".noidtypewithprefix", "user");
//        enableTableFieldAnnotation = false;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".noannowithprefix", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".withannoidtypeprefix", "user");

        serviceClassNameStartWithI = false;
        generateByTables(packageName, "t_erwin_user");
    }

    private void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/phone_list";
        String dbUsername = "root";
        String dbPassword = "123456";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(dbUsername)
                .setPassword(dbPassword)
                .setDriverName(Driver.class.getName());
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //.setEntityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setFieldPrefix(fieldPrefix)//test_id -> id, test_type -> type
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setIdType(tableIdType)
                .setAuthor(author)
                .setOutputDir(outPath)
                .setFileOverride(true);
        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

}
