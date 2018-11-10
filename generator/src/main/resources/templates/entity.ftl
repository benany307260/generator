package ${group}.${artifact}.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="${table_name}")
public class ${entity_name} implements Serializable {

	private static final long serialVersionUID = 1L;
	
<#if model_column?exists>
	<#list model_column as model>
    /**
    * ${model.columnComment!''}
    */
    <#if (model.columnIsId = true)>
	@Id
    @GeneratedValue
    </#if>
    @Column(name = "${model.columnName}")
    <#if (model.columnType = 'varchar' || model.columnType = 'text')>
    private String ${model.changeColumnName?uncap_first};
	<#elseif model.columnType = 'int' || model.columnType = 'tinyint' || model.columnType = 'smallint' >
    private Integer ${model.changeColumnName?uncap_first};
    <#elseif model.columnType = 'bigint' >
    private Long ${model.changeColumnName?uncap_first};
	<#elseif model.columnType = 'timestamp' >
    private Timestamp ${model.changeColumnName?uncap_first};
	<#elseif model.columnType = 'date' >
    private Date ${model.changeColumnName?uncap_first};
    <#else>
    private String ${model.changeColumnName?uncap_first};
    </#if>
    
	</#list>
</#if>
	
	
<#if model_column?exists>
	<#list model_column as model>
	<#if (model.columnType = 'varchar' || model.columnType = 'text')>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    <#elseif model.columnType = 'int' || model.columnType = 'tinyint' || model.columnType = 'smallint' >
    public Integer get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Integer ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    <#elseif model.columnType = 'bigint' >
	public Long get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

	public void set${model.changeColumnName}(Long ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
	<#elseif model.columnType = 'timestamp' >
	public Timestamp get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

	public void set${model.changeColumnName}(Timestamp ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
	<#elseif model.columnType = 'date' >
	public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

	public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    <#else>
    public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
	</#if>

	</#list>
</#if>
}