package com.spring.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.model.Person;

@Transactional(propagation = Propagation.REQUIRED)
public class PersonDaoImpl {

	@PersistenceContext
	private EntityManager em;

	public int save(Person person) {
		em.persist(person);
		return person.getId();
	}

	public List<Person> getAll() {
		return em.createQuery("SELECT p FROM Person p", Person.class)
				.getResultList();
	}
	
	public Person retrievePerson(int idPerson) {
		return em.find(Person.class, idPerson);
	}
	
	public int deletePerson(Person person) {
		em.remove(em.contains(person) ? person : em.merge(person));
		 return person.getId();
	}

}
