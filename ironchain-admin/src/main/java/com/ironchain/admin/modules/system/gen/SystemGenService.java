package com.ironchain.admin.modules.system.gen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ironchain.common.dao.gen.MysqlTableDao;
import com.ironchain.common.kits.GenKit;

@Service
public class SystemGenService {

	@Autowired
	private MysqlTableDao mysqlTableDao;

	public Page<Map<String, Object>> findAllTable(Pageable pageable) {
		return mysqlTableDao.findAllTable(pageable);
	}

	public Map<String, Object> queryTable(String tableName) {
		return mysqlTableDao.findTableByName(tableName);
	}

	public List<Map<String, Object>> queryColumns(String tableName) {
		return mysqlTableDao.findColumnByTableName(tableName);
	}

	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (ZipOutputStream zip = new ZipOutputStream(outputStream)) {
			for (String tableName : tableNames) {
				// 查询表信息
				Map<String, Object> table = queryTable(tableName);
				// 查询列信息
				List<Map<String, Object>> columns = queryColumns(tableName);
				// 生成代码
				GenKit.generatorCode(table, columns, zip);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}

}
