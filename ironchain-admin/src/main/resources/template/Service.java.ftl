package com.ironchain.admin.modules.${packageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironchain.common.base.BaseService;
import com.ironchain.common.dao.${className}Dao;

/**
 * ${tableComment!}
 * 
 * @author ${author}
<#if email??>
 * @email ${email}
</#if>
 */
@Service
public class ${className}Service extends BaseService {
	
	@Autowired
	private ${className}Dao ${modelName}Dao;
}
