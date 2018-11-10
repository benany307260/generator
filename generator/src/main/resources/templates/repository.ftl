package ${group}.${artifact}.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ${group}.${artifact}.entity.${entity_name};

/**
 *	${table_description!''}JpaRepository
 */
public interface ${entity_name}Repository extends JpaRepository<${entity_name},Long> {

}