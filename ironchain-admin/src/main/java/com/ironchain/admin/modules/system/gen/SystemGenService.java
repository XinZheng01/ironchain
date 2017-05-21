package com.ironchain.admin.modules.system.gen;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ironchain.common.dao.gen.MysqlTableDao;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.common.kits.FormatKit;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SystemGenService {

	@Autowired
	private MysqlTableDao mysqlTableDao;
	
	/**
	 * 查询所有表
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findAllTable(Pageable pageable) {
		return mysqlTableDao.findAllTable(pageable);
	}

	/**
	 * 根据表名查询表
	 * @param tableName
	 * @return
	 */
	public Map<String, Object> queryTable(String tableName) {
		return mysqlTableDao.findTableByName(tableName);
	}

	/**
	 * 根据表名查询表字段
	 * @param tableName
	 * @return
	 */
	public List<Map<String, Object>> queryColumns(String tableName) {
		return mysqlTableDao.findColumnByTableName(tableName);
	}

	/**
	 * 生成代码
	 * @param tableNames
	 * @return
	 */
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (ZipOutputStream zip = new ZipOutputStream(outputStream)) {
			for (String tableName : tableNames) {
				// 查询表信息
				Map<String, Object> table = queryTable(tableName);
				// 查询列信息
				List<Map<String, Object>> columns = queryColumns(tableName);
				// 生成代码
				generator(table, columns, zip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}
	
	/**
	 * 生成代码
	 */
	private static void generator(Map<String, Object> table,
			List<Map<String, Object>> columns, ZipOutputStream zip){
//		//配置信息
		Properties config = getConfig();
		
		String javaType = null;
		for (Map<String, Object> column : columns) {
			//设置属性名
			column.put("attrName", StringUtils.uncapitalize(columnToJava(column.get("columnName").toString())));
			//数据库类型转java类型
			javaType = config.getProperty(column.get("dataType").toString(), "unknowType");
			column.put("javaType", javaType);
			
			if("Date".equals(javaType))
				table.put("hasDate", true);
			else if("BigDecimal".equals(javaType))
				table.put("hasBigDecimal", true);
		}
		
		Configuration cfg = getConfiguration(config);
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		String tableName = replacePrefix(table.get("tableName").toString(), config.getProperty("tablePrefix"));
		String className = columnToJava(tableName);
		String packageName = StringUtils.replace(tableName.toLowerCase(), "_", ".");
		String pathName = StringUtils.replace(tableName.toLowerCase(), "_", "/");
		map.putAll(table);
		map.put("className", className);//设置类名
		map.put("modelName", StringUtils.uncapitalize(className));//设置模型名
		map.put("packageName", packageName);//包路径
		map.put("pathName", pathName);//url路径
		map.put("columns", columns);//属性
		map.put("author", config.getProperty("author"));//作者
		map.put("email", config.getProperty("email"));//邮箱
		
        //获取模板列表
		Template template = null;
		for (String templateName : getTemplates(config.getProperty("tmplDir", "/"))) {
			try {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				template = cfg.getTemplate(templateName);
				template.process(map, new OutputStreamWriter(bout));
				zip.putNextEntry(new ZipEntry(getZipDir(config, templateName, className, pathName)));
				zip.write(bout.toByteArray());
			} catch (Exception e) {
				throw new ServiceException("渲染模板失败，表名：" + table.get("tableName"), e);
			}
		}
	}
	
	/**
	 * 获取classpath:/下的模板文件
	 * @param tmplDir
	 * @return
	 */
	private static List<String> getTemplates(String tmplDir){
		File path = new File(SystemGenService.class.getClassLoader().getResource(tmplDir).getFile());
		return Arrays.asList(path.list((dir, name) -> {return name.endsWith(".ftl");}));
	}
	
	public static Configuration cfg = null;
	
	public static Configuration getConfiguration(Properties config){
		if(cfg == null){
			Configuration configuration = new Configuration(Configuration.getVersion());
			Resource path = new DefaultResourceLoader().getResource(config.getProperty("tmplDir"));
			try {
				configuration.setDirectoryForTemplateLoading(path.getFile());
			} catch (IOException e) {
				throw new ServiceException("获取模板异常", e);
			}
			configuration.setDefaultEncoding("UTF-8");
			cfg = configuration;
		}
		return cfg;
	}
	
	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return StringUtils.replace(WordUtils.capitalizeFully(columnName, '_'), "_", "");
	}
	
	/**
	 * 替换前缀
	 */
	public static String replacePrefix(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return tableName;
	}
	
	/**
	 * 获取配置信息
	 */
	public static Properties getConfig(){
		try {
			return PropertiesLoaderUtils.loadProperties(new ClassPathResource("generator.properties"));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
//	public static String getOutputDir(Properties properties, String tmplName, String className){
//		String[] arr = StringUtils.split(tmplName, '.');
//		Map<String, Object> map = new HashMap<>();
//		map.put("className", className);
//		map.put("pathName", className.toLowerCase());
//		return FormatKit.formatString(properties.getProperty(arr[0] + "OutputDir"), map);
//	}
	
	/**
	 * 获取文件名
	 */
	public static String getZipDir(Properties properties, String tmplName, String className, String pathName){
		String[] arr = StringUtils.split(tmplName, '.');
		Map<String, Object> map = new HashMap<>();
		map.put("className", className);
		map.put("pathName", pathName);
		return FormatKit.formatString(properties.getProperty(arr[0] + "ZipDir"), map);
	}
}
