package com.mike.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mike.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.mike.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{

/**
 * All methods without @Query are using keyWords from CrudRepository library
 * @return
 * 
 */

    @Query("select p from Person p where p.id not in ?1")
    List<Person> getPersonsByIds(List<Long> ids);

    @Query("select p.name ,length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShortestName();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegister();
    

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunctions();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLenght();
 
    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinIdPerson();

    @Query("select max(p.id) from Person p")
    Long getMaxaxIdPerson();

    List<Person> findAllByOrderByNameDesc();
    
    @Query("select p from Person p order by p.name desc")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByIdDesc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDesc(String name1, String name2);
    
    @Query("select p from Person p where p.id between ?1 and ?2 order by p.id desc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("select p.id, upper(p.name), lower(p.lastName), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("select upper(p.name || ' ' ||p.lastName) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(CONCAT(p.name, ' ', p.lastName)) from Person p")
    List<String> findAllFullNameConcatLower();

    // @Query("select CONCAT(p.name, ' ', p.lastName) from Person p")
    @Query("select p.name || '' ||p.lastName from Person p")
    List<String> findAllFullNameConcat();


    @Query("select p.name from Person p")
    List<String>findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String>findAllNamesDistinct();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String>findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();
    
    @Query("select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select new com.mike.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ', p.lastName) as fullName from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);
    
    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findLikeName(String name);
    
    
    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p from Person p where p.programmingLanguage =?1")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage);
    
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p where p.id=?1")
    Object obtenerPersonDataFullById(Long id);
    
    @Query("select p.name, p.programmingLanguage from Person p where p.name =?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage =?1 and p.name =?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);
}
