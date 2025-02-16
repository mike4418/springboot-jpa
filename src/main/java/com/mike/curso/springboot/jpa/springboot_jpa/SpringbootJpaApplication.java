package com.mike.curso.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.mike.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.mike.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.mike.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements  CommandLineRunner{


	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		update();
	}


	@Transactional(readOnly=true)
	public void whereIn() {
	
		System.out.println("======================= Consulta where in =======================");
		List<Person> optPerson = repository.getPersonsByIds(Arrays.asList(1L,3L,5L,7L));
		optPerson.forEach(System.out::println);

	}
	@Transactional(readOnly=true)
	public void subQueries() {

		System.out.println("======================= Consulta por el nombre mas corto y su largo =======================");
		List<Object[]> regs = repository.getShortestName();
		regs.forEach(reg -> System.out.println("Name: " + reg[0] + " Lenght: "  + reg[1]));


		System.out.println("======================= Consulta el ultimo registro =======================");
		Optional<Person> optPerson = repository.getLastRegister();
		optPerson.ifPresent(System.out::println);


	}


	@Transactional(readOnly=true)
	public void personalizedQueriesAggregation(){
	
		System.out.println("======================= Consulta con el total de registros de la tabla Person =======================");
		Long count  = repository.getTotalPerson();
		System.out.println("Total: " +  count);

		System.out.println("======================= Consulta con el valor minimo del ID =======================");
		Long min  = repository.getMinIdPerson();
		System.out.println("Min: " +  min);

		System.out.println("======================= Consulta con el valor Maximo del ID =======================");
		Long max  = repository.getMaxaxIdPerson();
		System.out.println("Max: " +  max);

		System.out.println("======================= Consulta con el nombre y su largo =======================");
		List<Object[]> regs = repository.getPersonNameLenght();
		regs.forEach(reg -> System.out.println("Name: " + reg[0] + " Lenght: "  + reg[1]));

		System.out.println("======================= Consulta con el nombre mas corto =======================");
		Integer minName  = repository.getMinLengthName();
		System.out.println("Min length name: " +  minName);

		System.out.println("======================= Consulta con el nombre mas largo =======================");
		Integer maxName  = repository.getMaxLengthName();
		System.out.println("MAx length name: " +  maxName);


		System.out.println("======================= Consulta cresumen de funciones de agregacion min, max, sum, avg, count =======================");
		Object[] resumeArg = (Object[]) repository.getResumeAggregationFunctions();
		System.out.println("Min: " +  resumeArg[0] + 
							 " Max: " +  resumeArg[1] +
							 " Sum: " +  resumeArg[2] + 
							 " Avg: " +  resumeArg[3] + 
							 " Count: " +  resumeArg[4]);
		
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesBetween(){

		System.out.println("======================= Consulta todo order by desc =======================");
		List<Person> allPersons = repository.findAllByOrderByNameDesc();
		allPersons.forEach(System.out::println);

		System.out.println("======================= Consulta por rangos de id =======================");
		List<Person> persons = repository.findByIdBetweenOrderByIdDesc(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("======================= Consulta por rangos de nombre =======================");
		List<Person> personsByName = repository.findByNameBetweenOrderByNameDesc("J", "Q");
		personsByName.forEach(System.out::println);

	}

	@Transactional(readOnly=true)
	public void personalizedQueriesConcatUpperAndLowerCase(){
		System.out.println("======================= Consulta nombres y apellidos de personas =======================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("======================= Consulta nombres y apellidos mayuscula =======================");
		List<String> namesUpper = repository.findAllFullNameConcatUpper();
		namesUpper.forEach(System.out::println);

		System.out.println("======================= Consulta nombres y apellidos minuscula =======================");
		List<String> namesLower = repository.findAllFullNameConcatLower();
		namesLower.forEach(System.out::println);

		System.out.println("======================= Consulta nombres y apellidos mayuscula y minuscula =======================");
		List<Object[]> namesReg = repository.findAllPersonDataListCase();
		namesReg.forEach(personReg -> System.out.println("Persona consultada: ID: " + personReg[0] + " Name: "  + personReg[1] + " last name: " + personReg[2] + " Programing language: " + personReg[3]));
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesDistinct(){
		System.out.println("======================= Consulta lon nombres de personas =======================");

		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("======================= Consulta lon nombres de personas distinct=======================");

		List<String> namesDistinct = repository.findAllNamesDistinct();
		namesDistinct.forEach(System.out::println);

		System.out.println("======================= Consulta los lenguajes de programacion distinct=======================");

		List<String> programmingLanguagesDistinct = repository.findAllProgrammingLanguageDistinct();
		programmingLanguagesDistinct.forEach(System.out::println);

		System.out.println("======================= Consulta la cantidad de los lenguajes de programacion distinct=======================");

		Long totalLanguage = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println("Total lenguajes de programacion: " + totalLanguage);
	}


	@Transactional(readOnly=true)
	public void personalizedQueries2(){
			System.out.println("======================= Consulta por objecto persona y lenguaje de programacion =======================");
			List<Object[]> personRegs = repository.findAllMixPerson();

			personRegs.forEach(reg -> {
				System.out.println("Programming language= " + reg[1] + ", Person = " + reg[0]);
			});
	
			System.out.println("======================= Consulta que puebla y devuelve objeto entity de una clase personalizada =======================");
			List<Person> persons = repository.findAllObjectPersonPersonalized();

			persons.forEach(System.out::println);

			System.out.println("======================= Consulta que puebla y devuelve objeto DTO de na clase personalizada =======================");
			List<PersonDto> personsDto = repository.findAllPersonDto();

			personsDto.forEach(System.out::println);
	}

	@Transactional(readOnly=true)
	public void personalizedQueries(){

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("======================= Consulta el nombre por el id =======================");
                System.out.println("Ingrese el Id de la persona: ");
                Long id = scanner.nextLong();
                
                String name = repository.getNameById(id);
                
                System.out.println("Nombre del id: " + name);
				scanner.close();

				System.out.println("======================= Consulta el id por el id =======================");
               
                
                Long idRetrieved = repository.getIdById(id);
                
                System.out.println("Id Consultado: " + idRetrieved);

				System.out.println("======================= Consulta el full name por el id =======================");
               
                
                String fullName = repository.getFullNameById(id);
                
                System.out.println("full Name: " + fullName);

				System.out.println("======================= Consulta por campos personalizados por el id =======================");

				Object[] personReg = (Object[])repository.obtenerPersonDataFullById(id);
				System.out.println("Persona consultada: ID: " + personReg[0] + " Name: "  + personReg[1] + " last name: " + personReg[2] + " Programing language: " + personReg[3]);

				System.out.println("======================= Consulta por campos personalizados Lista =======================");
				List<Object[]> regs = repository.obtenerPersonDataList();

				regs.forEach(reg -> System.out.println("Persona consultada: ID: " + personReg[0] + " Name: "  + personReg[1] + " last name: " + personReg[2] + " Programing language: " + personReg[3]));


				scanner.close();
            }
			
	}

	@Transactional
	public void delete(){

		repository.findAll().forEach(System.out::println);
		
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Ingrese el Id a eliminar: ");
                Long id = scanner.nextLong();
                
                repository.deleteById(id);
                
                repository.findAll().forEach(System.out::println);
				scanner.close();
            }
	}

	@Transactional
	public void delete2(){

		repository.findAll().forEach(System.out::println);
		
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Ingrese el Id a eliminar: ");
                Long id = scanner.nextLong();
                
                Optional<Person> optionalPerson = repository.findById(id);
                
                optionalPerson.ifPresentOrElse(repository::delete,
                        () -> System.out.println("Lo sentimos el usuario con este id ingresado no existe!"));
                
                
                repository.findAll().forEach(System.out::println);
            }
	}

	@Transactional
	public void update(){

		Long id;

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Ingrese el Id: ");
                id = scanner.nextLong();
                
                Optional<Person> optionalPerson = repository.findById(id);
                
                optionalPerson.ifPresent(person -> {
                    
                    System.out.println(person);
                    System.out.println("Ingrese el Lenguaje a actualizar: ");
                    String language = scanner.next();
                    person.setProgrammingLanguage(language);
                    Person personUpdated = repository.save(person);
                    System.out.println(personUpdated);
                }) ;
            }
	}


	@Transactional
	public void create(){

            String name;
            String lastName;
            String programmingLanguage;
            try (Scanner scanner = new Scanner(System.in)) 
			{
                System.out.println("Ingrese el nombre: ");
				name = scanner.next();
				System.out.println("Ingrese el Apellido: ");
                lastName = scanner.next();
				System.out.println("Ingrese el Lenguaje de programacionLo: ");
                programmingLanguage = scanner.next();

				scanner.close();
            }

		Person person = new Person(null, name, lastName, programmingLanguage);

		Person personNew = repository.save(person);

		// System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly=true)
	public void findOne(){

		// Person person = null;

		// Optional<Person> optionalPerson = repository.findById(1L);

		// if(optionalPerson.isPresent()){
		// 	person = optionalPerson.get();
		// }

		// System.out.println(person);

		repository.findByNameContaining("ri").ifPresent(System.out::println);

	}

	
	@Transactional(readOnly=true)
	public void list(){

		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");
		// List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");

		persons.stream().forEach(p -> System.out.println(p));

		List<Object[]> personValues = repository.obtenerPersonData("Java","Andres");

		personValues.stream().forEach(p -> {
			System.out.println(p[0] + " es experto en " + p[1]);
		});
	}

}
