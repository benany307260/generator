package com.bentest.generator;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author coderyao
 * @version IndexServiceImpl, v 0.1 2018/7/24 上午11:18 coderyao Exp
 */
public class MavenUtil  {
    //private static final Logger LOGGER                 = LoggerFactory.getLogger(IndexServiceImpl.class);

    private static final String        WEB_INF_PATH           = MavenUtil.class.getClassLoader().getResource("../").getPath();
    private static final String        COMPILE_PATH           = WEB_INF_PATH + "sourceCode/";
    private static final String        EMBEDDED_MAVEN_PATH    = WEB_INF_PATH + "maven/";
    private static final String        EMBEDDED_MAVEN_COMMAND = EMBEDDED_MAVEN_PATH + "bin/mvn";

    public static void main(String[] args) {
    	try {
			MavenUtil.MavenPackage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void MavenPackage() throws IOException {
        ProcessBuilder pb = new ProcessBuilder();
        //构建命令
        //若maven的路径在jar包中这里拿到的EMBEDDED_MAVEN_COMMAND是不能被执行的，因为是以file://打头的
        pb.command(EMBEDDED_MAVEN_COMMAND, "-DskipTests=true", "clean", "package", "-f", MavenUtil.class.getClassLoader().getResource("../sourceCode/abcdemo/pom.xml").getPath());
        //执行命令
        Process process = pb.start();
        InputStream inputStream = process.getInputStream();
        byte[] buffer = new byte[512];
        int readSize;
        while ((readSize = inputStream.read(buffer)) > 0) {
            System.out.write(buffer, 0, readSize);
        }
    }

    /**
     * 修改权限（这里粗犷的修改为777，如有精细化的权限控制，自己调整一下）
     * 因为一些原因，虽然线上默认的执行用户是root，并且权限为rwx，依然会报权限不足的错误
     * 如果有大神指导原因请指点一二
     * @throws Exception
     */
    public static void afterPropertiesSet() throws Exception {
        //修改maven目录权限
        Process chmodMaven = new ProcessBuilder("chmod", "-R", "777", EMBEDDED_MAVEN_PATH).start();
        //等待完成
        chmodMaven.waitFor();
        //LOGGER.info("修改权限完成：{}", EMBEDDED_MAVEN_PATH);

        //修改编译目录权限
        Process chmodCompile = new ProcessBuilder("chmod", "-R", "777", COMPILE_PATH).start();
        chmodCompile.waitFor();
        //LOGGER.info("修改权限完成：{}", COMPILE_PATH);
    }
}