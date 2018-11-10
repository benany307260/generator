package ${group}.${artifact}.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${group}.${artifact}.bean.dto.ResponseBean;
import ${group}.${artifact}.entity.${entity_name};
import ${group}.${artifact}.service.${entity_name}Service;

/**
 * ${table_description!''}Controller
 *
 */
@RestController
public class ${entity_name}Controller {

	private final static Logger logger = LoggerFactory.getLogger(${entity_name}Controller.class);

	@Autowired
	private ${entity_name}Service ${entity_name?uncap_first}Service;

	/**
	 * 模块名称，用于记录日志
	 */
	private String menuName = "${table_description!''}";

	/**
	 * 查询
	 * 
	 * @param paramBean
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v1/find${entity_name}List")
	public ResponseBean r_${entity_name?uncap_first}_list(${entity_name} paramBean) 
	{
		try 
		{
			List<${entity_name}> dataList = ${entity_name?uncap_first}Service.findAll${entity_name}();
			return new ResponseBean().success(dataList);
		} 
		catch (Exception e) 
		{
			logger.error(menuName + "，查询列表，异常。", e);
			return new ResponseBean().failure();
		}
	}
}