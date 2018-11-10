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
 * 代码生成器
 *
 */
public class CodeGenerateUtils {

    private final String AUTHOR = "ben";
    private final String CURRENT_DATE = "2018/11/09";
    private final String tableName = "sys_user";
    private final String packageName = "com.evada.pm.process.manage";
    private final String tableAnnotation = "操作员";
    private final String URL = "jdbc:mysql://192.169.6.137:3406/mytest?characterEncoding=UTF-8&serverTimezone=UTC";
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
    
    /**
     * 项目版本号
     */
    private final String version = "0.0.1-SNAPSHOT";
    
    /**
     * 项目名称
     */
    private final String projectName = "测试项目";

    /**
     * 项目描述
     */
    private final String projectDescription = "测试项目";
    
    /**
     * 项目运行监听端口
     */
    private final String serverPort = "9292";
    
    /**
     * 数据源driver class
     */
    private final String dsDriverClass = "com.mysql.cj.jdbc.Driver";
    
    /**
     * 数据源连接url
     */
    private final String dsConnectUrl = "jdbc:mysql://192.169.6.137:3406/mytest?characterEncoding=UTF-8&serverTimezone=UTC";
    
    /**
     * 数据源连接用户名
     */
    private final String dsUsername = "root";
    
    /**
     * 数据源连接密码
     */
    private final String dsPassword = "123456";
    
    /**
     * 数据源最大活跃连接数
     */
    private final String dsMaxActive = "20";
    
    /**
     * 数据源最小空闲连接数
     */
    private final String dsMinIdle = "5";
    
    /**
     * 数据源初始化连接数
     */
    private final String dsInitialSize = "5";
    
    /**
     * 日志文件目录
     */
    private final String loggingFile = "/usr/local/odc-admin/logs/application.log";
    
    
    
    
    
    public Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        //codeGenerateUtils.generate();
        
        // 项目配置文件配置参数
        Map<String,String> configParamMap = new HashMap<>();
        configParamMap.put("server_port", codeGenerateUtils.serverPort);
        configParamMap.put("ds_driver_class", codeGenerateUtils.dsDriverClass);
        configParamMap.put("ds_url", codeGenerateUtils.dsConnectUrl);
        configParamMap.put("ds_username", codeGenerateUtils.dsUsername);
        configParamMap.put("ds_password", codeGenerateUtils.dsPassword);
        configParamMap.put("ds_max_active", codeGenerateUtils.dsMaxActive);
        configParamMap.put("ds_min_idle", codeGenerateUtils.dsMinIdle);
        configParamMap.put("ds_initial_size", codeGenerateUtils.dsInitialSize);
        configParamMap.put("logging_file", codeGenerateUtils.loggingFile);
        
        codeGenerateUtils.generateSpringbootProject(codeGenerateUtils.diskPath, codeGenerateUtils.group, codeGenerateUtils.artifact, 
        		codeGenerateUtils.version, codeGenerateUtils.projectName, codeGenerateUtils.projectDescription, configParamMap);
        
        String tableName = "user_info";
        String table_description = "用户";
        codeGenerateUtils.generate(codeGenerateUtils.diskPath, codeGenerateUtils.group, codeGenerateUtils.artifact, tableName, table_description);
    }
    
    public void generate(String diskPath, String group, String artifact, String tableName, String table_description) throws Exception{
        try {
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null,"%", tableName,"%");
            
            // 源码目录相对路径
            final String srcFolderPath = getSpringbootSrcFolderPath(group, artifact);
            // 实体类名称
            final String entityName = replaceUnderLineAndUpperCase(tableName);
            
            // 生成entity
            generateEntityFile(diskPath, srcFolderPath, group, artifact, tableName, entityName, resultSet);
            
            // 生成Repository
            generateRepositoryFile(diskPath, srcFolderPath, group, artifact, entityName, table_description);
            
            // 生成Service
            generateServiceFile(diskPath, srcFolderPath, group, artifact, entityName, table_description);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }
    }
    
    /**
     * 获取springboot项目源码目录相对路径
     * @param group
     * @param artifact
     * @return
     */
    private String getSpringbootSrcFolderPath(String group, String artifact)
    {
    	// 源码目录相对路径
        final String srcFolderPath = artifact + "\\src\\main\\java\\" + group.replace(".", "\\") + "\\" +artifact + "\\";
        return srcFolderPath;
    }
    
    /**
     * 获取springboot项目源码目录相对路径
     * @param group
     * @param artifact
     * @return
     */
    private String getSpringbootResourceFolderPath(String artifact)
    {
    	// 资源目录相对路径
        final String resourceFolderPath = artifact + "\\src\\main\\resources\\";
        return resourceFolderPath;
    }
    
    /**
     * 生成springboot项目
     * @param diskPath
     * @param group
     * @param artifact
     * @param version
     * @param projectName
     * @param projectDescription
     * @throws Exception
     */
    private void generateSpringbootProject(String diskPath, String group, String artifact, 
    		String version, String projectName, String projectDescription, Map<String,String> configParamMap) throws Exception
    {
    	// 源码目录相对路径
        final String srcFolderPath = getSpringbootSrcFolderPath(group, artifact);
    	
        // 资源目录相对路径
        final String resourceFolderPath = getSpringbootResourceFolderPath(artifact);
        
        // 创建项目目录
        generateSpringbootFolder(diskPath, srcFolderPath);
        
        // 创建pom文件
        generatePomFile(diskPath, group, artifact, version, artifact, artifact);
        
        // 创建启动程序入口文件
        generateApplicationFile(diskPath, srcFolderPath, group, artifact);
        
        // 创建配置文件
        generatePropertiesFile(diskPath, resourceFolderPath, group, artifact, configParamMap);
    }

    /**
     * 创建springboot项目目录
     * @param diskPath 存放路径
     * @param group 组织，如com.example
     * @param artifact 工作目录，如demo
     * @throws Exception
     */
    private void generateSpringbootFolder(String diskPath, String srcFolderPath) throws Exception
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
    	String srcFolderAbsolutePath = diskPath + srcFolderPath;
    	File srcFolder = new File(srcFolderAbsolutePath);
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
    
    /**
     * 创建POM文件
     * @param diskPath
     * @param group
     * @param artifact
     * @param version
     * @param projectName
     * @param projectDescription
     * @throws Exception
     */
    private void generatePomFile(String diskPath, String group, String artifact, String version, String projectName, String projectDescription) throws Exception{
        final String suffix = "pom.xml";
        final String path = diskPath + artifact + "\\" + suffix;
        final String templateName = "pom.ftl";
        File pomFile = new File(path);
        if (!pomFile.getParentFile().exists()) {  
        	pomFile.getParentFile().mkdirs();  
    	}
        
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("group", group);
        dataMap.put("artifact", artifact);
        dataMap.put("version", version);
        dataMap.put("project_name", projectName);
        dataMap.put("project_description", projectDescription);
        
        FileOutputStream fos = new FileOutputStream(pomFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
    }
    
    /**
     * 创建程序入口
     * @param diskPath
     * @param srcFolderPath
     * @param group
     * @param artifact
     * @throws Exception
     */
    private void generateApplicationFile(String diskPath, String srcFolderPath, String group, String artifact) throws Exception{
    	
    	final String name = StringUtils.capitalize(artifact);
        final String suffix = name + "Application.java";
        final String path = diskPath + srcFolderPath + suffix;
        final String templateName = "Application.ftl";
        File pomFile = new File(path);
        if (!pomFile.getParentFile().exists()) {  
        	pomFile.getParentFile().mkdirs();  
    	}
        
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("group", group);
        dataMap.put("artifact", artifact);
        
        FileOutputStream fos = new FileOutputStream(pomFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
    }
    
    /**
     * 创建配置文件
     * @param diskPath
     * @param resourceFolderPath
     * @param group
     * @param artifact
     * @param configParamMap
     * @throws Exception
     */
    private void generatePropertiesFile(String diskPath, String resourceFolderPath, String group, String artifact, Map<String,String> configParamMap) throws Exception{
        final String suffix = "application.properties";
        final String path = diskPath + resourceFolderPath + suffix;
        final String templateName = "properties.ftl";
        File pomFile = new File(path);
        if (!pomFile.getParentFile().exists()) {  
        	pomFile.getParentFile().mkdirs();  
    	}
        
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("group", group);
        dataMap.put("artifact", artifact);
        dataMap.putAll(configParamMap);
        
        FileOutputStream fos = new FileOutputStream(pomFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
    }
    
    /**
     * 创建实体类
     * @param diskPath
     * @param srcFolderPath
     * @param group
     * @param artifact
     * @param tableName
     * @param entityName
     * @param resultSet
     * @throws Exception
     */
    private void generateEntityFile(String diskPath, String srcFolderPath, String group, String artifact, String tableName, String entityName, ResultSet resultSet) throws Exception{
        final String suffix = ".java";
        final String path = diskPath + srcFolderPath + "\\entity\\" + entityName + suffix;
        final String templateName = "entity.ftl";
        File file = new File(path);
        if (!file.getParentFile().exists()) {  
        	file.getParentFile().mkdirs();  
    	}
        
        List<ColumnClass> columnClassList = new ArrayList<>();
        while(resultSet.next()){
        	ColumnClass columnClass = new ColumnClass();
            // id字段
            if(resultSet.getString("COLUMN_NAME").equals("id")) {
            	columnClass.setColumnIsId(true);
            }
            else
            {
            	columnClass.setColumnIsId(false);
            }
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME").toLowerCase()));
            //字段在数据库的注释
            columnClass.setColumnComment(resultSet.getString("REMARKS"));
            columnClassList.add(columnClass);
        }

        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", columnClassList);
        dataMap.put("group", group);
        dataMap.put("artifact", artifact);
        dataMap.put("table_name", tableName);
        dataMap.put("entity_name", entityName);
        
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
    }
    
    /**
     * 创建实体的Repository
     * @param diskPath
     * @param srcFolderPath
     * @param group
     * @param artifact
     * @param entityName
     * @param table_description
     * @throws Exception
     */
    private void generateRepositoryFile(String diskPath, String srcFolderPath, String group, String artifact, String entityName, String table_description) throws Exception{
        final String suffix = "Repository.java";
        final String path = diskPath + srcFolderPath + "\\jpa\\repository\\" + entityName + suffix;
        final String templateName = "repository.ftl";
        File file = new File(path);
        if (!file.getParentFile().exists()) {  
        	file.getParentFile().mkdirs();  
    	}
        
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("group", group);
        dataMap.put("artifact", artifact);
        dataMap.put("entity_name", entityName);
        dataMap.put("table_description", table_description);
        
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
    }
    
    
    private void generateServiceFile(String diskPath, String srcFolderPath, String group, String artifact, String entityName, String table_description) throws Exception{
        final String suffix = "Service.java";
        final String path = diskPath + srcFolderPath + "\\service\\" + entityName + suffix;
        final String templateName = "service.ftl";
        File file = new File(path);
        if (!file.getParentFile().exists()) {  
        	file.getParentFile().mkdirs();  
    	}
        
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("group", group);
        dataMap.put("artifact", artifact);
        dataMap.put("entity_name", entityName);
        dataMap.put("table_description", table_description);
        
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        template.process(dataMap,out);
    }
    
    
    
/*    public void generate() throws Exception{
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
*/
    /**
     * 首字母转大写
     * @param string
     * @return
     */
	/*public static String toUpperFristChar(String string) {
		try {
			char[] charArray = string.toCharArray();
			charArray[0] -= 32;
			return String.valueOf(charArray);
		} catch (Exception e) {
			return string;
		}
	}*/
	
	/**
     * 替换下划线和转大写
     * @param str
     * @return
     */
	public String replaceUnderLineAndUpperCase(String str){
		
		// 以下划线分割字符串
		final String[] stringArray = str.split("_");
		
		StringBuffer sb = new StringBuffer();
		
		for(String temp : stringArray)
		{
			sb.append(StringUtils.capitalize(temp));
		}
        
        return sb.toString();
    }
	
    /**
     * 替换下划线和转大写
     * @param str
     * @return
     */
/*    public String replaceUnderLineAndUpperCase(String str){
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
    }*/

}