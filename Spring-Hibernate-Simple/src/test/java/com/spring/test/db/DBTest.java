package com.spring.test.db;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.test.dao.PersonDaoImpl;
import com.spring.test.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTest {

    /**
     * Not a great test as I am using test ordering but it's simple and to the point...
     */
	
	@Autowired
	private PersonDaoImpl personDao;
	
	private static Person person1;
	private static Person person2;
	
	@BeforeClass
	public static void initPerson(){
		person1 = new Person(1, "Joe", "New York");
        person2 = new Person(2, "James", "London");
	}
	
	@Test
	public void testASavePerson() {

        personDao.save(person1);
        personDao.save(person2);       
        assertTrue(personDao.getAll().size() == 2);
		
	}
	
	@Test
	public void testDeletePerson(){
		
		personDao.deletePerson(person1);
		
		List<Person> getPeople = personDao.getAll();
		assertTrue(getPeople.size() == 1);
		
		for (Person person : getPeople ) {
			assertTrue(person.getName().equals("James"));
		}
		
	}
	
	@Test
	public void testBRetrievePerson(){
		
		Person newPerson = personDao.retrievePerson(2);
		assertTrue(newPerson.getId() == 2);
		assertTrue(newPerson.getName().equals("James"));
		
	}


}
