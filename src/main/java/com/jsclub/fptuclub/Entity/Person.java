package com.jsclub.fptuclub.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	private String name;
	private int age;
	private String username;
	private String password;
	public List<Person> init(){
		List<Person> list = new ArrayList<>();
		return list;
	}
}
