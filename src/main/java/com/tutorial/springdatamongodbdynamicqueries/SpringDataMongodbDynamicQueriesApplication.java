package com.tutorial.springdatamongodbdynamicqueries;

import com.tutorial.springdatamongodbdynamicqueries.domain.Department;
import com.tutorial.springdatamongodbdynamicqueries.domain.Employee;
import com.tutorial.springdatamongodbdynamicqueries.repository.EmployeeRepository;
import com.tutorial.springdatamongodbdynamicqueries.repository.support.ResourceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(repositoryBaseClass = ResourceRepositoryImpl.class)
public class SpringDataMongodbDynamicQueriesApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongodbDynamicQueriesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Department> departments = Arrays.asList(
                new Department("IT", "IT department"),
                new Department("RAD", "research and development team"),
                new Department("MK", "marketing"),
                new Department("TS", "technical support team"),
                new Department("AG", "Accounting")
        );

        employeeRepository.deleteAll();

        List<Employee> employees = Arrays.asList(
                new Employee("600f4997e3a11bc10091f786","Ferdinand","Wynne","Etiam.ligula.tortor@vestibulumMauris.com",departments.get(0)),
                new Employee("600f49a1a5bd0e51ceb6c2d3","Grant","Quinlan","lobortis.ultrices.Vivamus@diamvelarcu.org",departments.get(0)),
                new Employee("600f49b6ff49b4e466efb4c4","Brielle","Hanae","Cras.dictum.ultricies@Integeridmagna.edu",departments.get(0)),

                new Employee("600f49aee40e8fd42bbf3e8f","Morgan","Ivory","feugiat.metus@Duisa.edu",departments.get(1)),
                new Employee("600f49c16c3c8f51ff49d30e","Alexa","Colorado","mus.Proin@mollisvitaeposuere.net",departments.get(1)),
                new Employee("600f49cc2eabbc1a8b9b7ead","Mercedes","Zeph","eu.placerat.eget@lacuspedesagittis.net",departments.get(1)),
                new Employee("600f49d3b5d8765523b9a17e","Chancellor","Myra","velit.dui.semper@magnaNam.org",departments.get(1)),
                new Employee("600f49ddc441d3b63d2f3f15","Leroy","Dillon","risus.Donec.egestas@loremvitaeodio.edu",departments.get(1)),
                new Employee("600f49e6687e2ce48b81831a","Cole","Xander","lacus.Nulla@quistristique.org",departments.get(1)),

                new Employee("600f49efecb77b12d517b519","Eleanor","Paul","metus.Aenean@urnaNullamlobortis.edu",departments.get(2)),
                new Employee("600f49f9be0c3402ac8f7416","Katell","Sawyer","porttitor@non.org",departments.get(2)),

                new Employee("600f49f9be0c3402ac8f7416","TaShya","Stewart","fames.ac.turpis@dolor.edu",departments.get(3))
        );

        employeeRepository.saveAll(employees);
    }
}
