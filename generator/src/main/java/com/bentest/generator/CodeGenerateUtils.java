package com.bentest.generator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Template;

/**
 * 描述：代码生成器
 * Created by Ay on 2017/5/1.
 */
public class CodeGenerateUtils {

    private final String AUTHOR = "ben";
    private final String CURRENT_DATE = "2018/11/09";
    private final String tableName = "sys_user";
    private final String packageName = "com.evada.pm.process.manage";
    private final String tableAnnotation = "操作员";
    private final String URL = "jdbc:mysql://192.169.3.101:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "123456";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String diskPath = "F:\\temp\\new\\";
    private final String changeTableName = replaceUnderLineAndUpperCase(tableName);
    
    /**
     * 组织
     */
    private final String group = "com.huang";
    
    /**
     * 项目目录/名称
     */
    private final String artifact = "autodemo";

    public Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        //codeGenerateUtils.generate();
        codeGenerateUtils.generateSpringbootProject(codeGenerateUtils.diskPath, codeGenerateUtils.group, codeGenerateUtils.artifact);
    }

    public void generate() throws Exception{
        try {
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null,"%", tableName,"%");
            //生成Mapper文件
            generateMapperFile(resultSet);
            //生成Dao文件
            //generateDaoFile(resultSet);
            //生成Repository文件
            generateRepositoryFile(resultSet);
            //生成服务层接口文件
            generateServiceInterfaceFile(resultSet);
            //生成服务实现层文件
            generateServiceImplFile(resultSet);
            //生成Controller层文件
            generateControllerFile(resultSet);
            //生成DTO文件
            generateDTOFile(resultSet);
            //生成Model文件
            generateModelFile(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }
    }
    
    /**
     * 创建springboot项目目录
     * @param diskPath 存放路径
     * @param group 组织，如com.example
     * @param artifact 工作目录，如demo
     * @throws Exception
     */
    private void generateSpringbootProject(String diskPath, String group, String artifact) throws Exception
    {
    	/*
    	 * 1.创建项目文件夹
    	 */
    	// 项目文件夹路径
    	String projectFolderPath = diskPath + artifact;
    	File projectFolder = new File(projectFolderPath);
    	// 如果路径不存在,则创建  
    	if (!projectFolder.getParentFile().exists()) {  
    		projectFolder.getParentFile().mkdirs();  
    	}
    	//如果文件夹不存在
    	if(!projectFolder.exists()){
    		//创建文件夹
    		projectFolder.mkdir();
    	}
    	
    	/*
    	 * 2.创建源码文件夹
    	 */
    	// 源码文件夹路径
    	String srcFolderPath = diskPath + artifact + "\\src\\main\\java\\" + group.replace(".", "\\");
    	File srcFolder = new File(srcFolderPath);
    	// 如果路径不存在,则创建  
    	if (!srcFolder.getParentFile().exists()) {  
    		srcFolder.getParentFile().mkdirs();  
    	}
    	//如果文件夹不存在
    	if(!srcFolder.exists()){
    		//创建文件夹
    		srcFolder.mkdir();
    	}
    	
    	/*
    	 * 3.创建资源文件夹
    	 */
    	// 资源文件夹路径
    	String resourceFolderPath = diskPath + artifact + "\\src\\main\\resources\\static";
    	File resourceFolder = new File(resourceFolderPath);
    	// 如果路径不存在,则创建  
    	if (!resourceFolder.getParentFile().exists()) {  
    		resourceFolder.getParentFile().mkdirs();  
    	}
    	//如果文件夹不存在
    	if(!resourceFolder.exists()){
    		//创建文件夹
    		resourceFolder.mkdir();
    	}
    }
    
    private void generatePomFile(String diskPath, String artifact, ResultSet resultSet) throws Exception{
        final String suffix = "pom.xml";
        final String path = diskPath + artifact + "\\" + suffix;
        final String templateName = "pom.ftl";
        File pomFile = new File(path);
        
        
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name_small",tableName);
        dataMap.put("table_name",changeTableName);
        dataMap.put("author",AUTHOR);
        dataMap.put("date",CURRENT_DATE);
        dataMap.put("package_name",packageName);
        dataMap.put("table_annotation",tableAnnotation);
        
        FileOutputStream fos = new FileOutputStream(pomFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
        
        generateFileByTemplate(templateName, pomFile, dataMap);

    }

    private void generateModelFile(ResultSet resultSet) throws Exception{

        final String suffix = ".java";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        List<ColumnClass> columnClassList = new ArrayList<>();
        ColumnClass columnClass = null;
        while(resultSet.next()){
            //id字段略过
            if(resultSet.getString("COLUMN_NAME").equals("id")) continue;
            columnClass = new ColumnClass();
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            //字段在数据库的注释
            columnClass.setColumnComment(resultSet.getString("REMARKS"));
            columnClassList.add(columnClass);
        }
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column",columnClassList);
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateDTOFile(ResultSet resultSet) throws Exception{
        final String suffix = "DTO.java";
        final String path = "D://" + changeTableName + suffix;
        final String templateName = "DTO.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateControllerFile(ResultSet resultSet) throws Exception{
        final String suffix = "Controller.java";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceImplFile(ResultSet resultSet) throws Exception{
        final String suffix = "ServiceImpl.java";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception{
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = diskPath + prefix + changeTableName + suffix;
        final String templateName = "ServiceInterface.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateRepositoryFile(ResultSet resultSet) throws Exception{
        final String suffix = "Repository.java";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "Repository.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateDaoFile(ResultSet resultSet) throws Exception{
        final String suffix = "DAO.java";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "DAO.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateMapperFile(ResultSet resultSet) throws Exception{
        final String suffix = "Mapper.xml";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "Mapper.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small",tableName);
        dataMap.put("table_name",changeTableName);
        dataMap.put("author",AUTHOR);
        dataMap.put("date",CURRENT_DATE);
        dataMap.put("package_name",packageName);
        dataMap.put("table_annotation",tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

    public String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.capitalize(result);
    }

}