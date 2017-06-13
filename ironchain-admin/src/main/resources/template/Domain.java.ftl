<#assign ignore = ["id", "createTime", "updateTime", "createBy", "updateBy"]>
<#assign bod = "">
<#list columns as column>
	
	<#if ignore?seq_contains(column.attrName)>
	<#assign bod = bod+"1">
	</#if>
</#list>
package com.ironchain.common.domain;

<#if hasBigDecimal??>
import java.math.BigDecimal;
</#if>
<#if hasDate??>
import java.util.Date;
</#if>
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

<#if bod == "11111">
import com.ironchain.common.base.DataModel;
<#else>
import com.ironchain.common.base.BaseModel;
</#if>

import lombok.Getter;
import lombok.Setter;
/**
 * ${tableComment!}
 * 
 * @author ${author}
<#if email??>
 * @email ${email}
</#if>
 */
@Setter
@Getter
@Entity
@Table(name="${tableName}")
public class ${className} extends ${(bod=="11111")?string("DataModel","BaseModel")} {

	private static final long serialVersionUID = 1L;
	
<#list columns as column>
	<#if bod == "11111" && ignore?seq_contains(column.attrName)>
	<#elseif column.attrName == "id">
	<#else>
	<#if column.columnComment??>/** ${column.columnComment}*/</#if>
	@Column(name="${column.columnName}")
	private ${column.javaType} ${column.attrName};
	
	</#if>
</#list>
}
