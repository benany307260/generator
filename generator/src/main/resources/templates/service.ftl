package ${group}.${artifact}.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${group}.${artifact}.entity.${entity_name};
import ${group}.${artifact}.jpa.repository.${entity_name}Repository;

/**
 * ${table_description!''}service
 *
 */
@Service
public class ${entity_name}Service {
	
	private final static Logger logger = LoggerFactory.getLogger(${entity_name}Service.class);
	
    @Autowired
    private ${entity_name}Repository ${entity_name?uncap_first}Repository;
    
    /**
     * 保存${table_description!''}
     * @param ${entity_name?uncap_first} ${table_description!''}
     * @return
     */
    public boolean addUserInfo(${entity_name} ${entity_name?uncap_first}){
    	${entity_name?uncap_first}Repository.save(${entity_name?uncap_first});
        return true;
    }
    
    /**
     * 查询所有${table_description!''}
     * @return ${table_description!''}集合
     */
    public List<${entity_name}> findAll${entity_name}(){
    	return ${entity_name?uncap_first}Repository.findAll();
    }
}