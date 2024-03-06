package com.jsclub.fptuclub.Service;
import java.util.List;
import com.jsclub.fptuclub.Entity.ListPerson;
import com.jsclub.fptuclub.Entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class PersonService {
	public List<Person> findAll(){
		Person a = new Person();
		return  a.init();
	}
}
