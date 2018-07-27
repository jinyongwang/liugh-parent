package com.liugh.config;

import com.liugh.annotation.Pass;
import com.liugh.base.Constant;
import com.liugh.util.ComUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author liugh
 * @Since 2018-05-10
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);

	@Value("${controller.scanPackage}")
	private String scanPackage;

	@Override
	public void run(String... args) throws Exception {
		doScanner(scanPackage);
		Set<String> urlAndMethodSet  =new HashSet<>();
		for (String aClassName:Constant.METHOD_URL_SET) {
			Class<?> clazz = Class.forName(aClassName);
			String baseUrl = "";
			String[] classUrl ={};
			if(!ComUtil.isEmpty(clazz.getAnnotation(RequestMapping.class))){
				classUrl=clazz.getAnnotation(RequestMapping.class).value();
			}
			Method[] methods = clazz.getMethods();
			for (Method method:methods) {
				if(method.isAnnotationPresent(Pass.class)){
					String [] methodUrl = null;
					StringBuilder sb  =new StringBuilder();
					if(!ComUtil.isEmpty(method.getAnnotation(PostMapping.class))){
						methodUrl=method.getAnnotation(PostMapping.class).value();
						if(ComUtil.isEmpty(methodUrl)){
							methodUrl=method.getAnnotation(PostMapping.class).path();
						}
						baseUrl=getRequestUrl(classUrl, methodUrl, sb,"POST");
					}else if(!ComUtil.isEmpty(method.getAnnotation(GetMapping.class))){
						methodUrl=method.getAnnotation(GetMapping.class).value();
						if(ComUtil.isEmpty(methodUrl)){
							methodUrl=method.getAnnotation(GetMapping.class).path();
						}
						baseUrl=getRequestUrl(classUrl, methodUrl, sb,"GET");
					}else if(!ComUtil.isEmpty(method.getAnnotation(DeleteMapping.class))){
						methodUrl=method.getAnnotation(DeleteMapping.class).value();
						if(ComUtil.isEmpty(methodUrl)){
							methodUrl=method.getAnnotation(DeleteMapping.class).path();
						}
						baseUrl=getRequestUrl(classUrl, methodUrl, sb,"DELETE");
					}else if(!ComUtil.isEmpty(method.getAnnotation(PutMapping.class))){
						methodUrl=method.getAnnotation(PutMapping.class).value();
						if(ComUtil.isEmpty(methodUrl)){
							methodUrl=method.getAnnotation(PutMapping.class).path();
						}
						baseUrl=getRequestUrl(classUrl, methodUrl, sb,"PUT");
					}else {
						methodUrl=method.getAnnotation(RequestMapping.class).value();
						baseUrl=getRequestUrl(classUrl, methodUrl, sb,RequestMapping.class.getSimpleName());
					}
					if(!ComUtil.isEmpty(baseUrl)){
						urlAndMethodSet.add(baseUrl);
					}
				}
			}
		}
		Constant.METHOD_URL_SET=urlAndMethodSet;
		logger.info("@Pass:"+urlAndMethodSet);
	}

	private String  getRequestUrl(String[] classUrl, String[] methodUrl, StringBuilder sb,String requestName) {
		sb.append("/api/v1");
		if(!ComUtil.isEmpty(classUrl)){
            for (String url:classUrl) {
                sb.append(url+"/");
            }
        }
		for (String url:methodUrl) {
            sb.append(url);
        }
        if(sb.toString().endsWith("/")){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString().replaceAll("/+", "/")+":--:"+requestName;
	}

	private void doScanner(String packageName) {
		//把所有的.替换成/
		URL url  =this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
		// 是否循环迭代
		if(StringUtils.countMatches(url.getFile(), ".jar")>0){
			boolean recursive=true;
			JarFile jar;
			// 获取jar
			try {
				jar = ((JarURLConnection) url.openConnection())
						.getJarFile();
				// 从此jar包 得到一个枚举类
				Enumeration<JarEntry> entries = jar.entries();
				while (entries.hasMoreElements()) {
					// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
					JarEntry entry = entries.nextElement();
					String name = entry.getName();
					// 如果是以/开头的
					if (name.charAt(0) == '/') {
						// 获取后面的字符串
						name = name.substring(1);
					}
					// 如果前半部分和定义的包名相同
					if (name.startsWith(packageName.replaceAll("\\.","/"))) {
						int idx = name.lastIndexOf('/');
						// 如果以"/"结尾 是一个包
						if (idx != -1) {
							// 获取包名 把"/"替换成"."
							packageName = name.substring(0, idx)
									.replace('/', '.');
						}
						// 如果可以迭代下去 并且是一个包
						if ((idx != -1) || recursive) {
							// 如果是一个.class文件 而且不是目录
							if (name.endsWith(".class")
									&& !entry.isDirectory()) {
								// 去掉后面的".class" 获取真正的类名
								String className = name.substring(
										packageName.length() + 1, name
												.length() - 6);
								try {
									// 添加到classes
									Constant.METHOD_URL_SET.add(Class
											.forName(packageName + '.'
													+ className).getName());
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File dir = new File(url.getFile());
		for (File file : dir.listFiles()) {
			if(file.isDirectory()){
				//递归读取包
				doScanner(packageName+"."+file.getName());
			}else{
				String className =packageName +"." +file.getName().replace(".class", "");
				Constant.METHOD_URL_SET.add(className);
			}
		}
	}

}
