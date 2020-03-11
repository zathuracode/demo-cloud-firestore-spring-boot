/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vobi.demo.cloud.firestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.demo.cloud.firestore.domain.User;
import com.vobi.demo.cloud.firestore.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Example controller demonstrating usage of Spring Data Repositories for Firestore.
 *
 * @author Diego Gomez
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private  UserRepository userRepository;

	@GetMapping
	private Flux<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/age/{age}")
	private Flux<User> getUsersByAge(@PathVariable("age") int age) {
		return userRepository.findByAge(age);
	}
	
	@GetMapping("/findById/{name}")
	private Mono<User> getUsersByAge(@PathVariable("name") String name) {
		return userRepository.findById(name);
	}
	
	@PostMapping("/saveUser")
	private Mono<User> saveUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/updateUser")
	private Mono<User> updateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping("/deleteUser/{name}")
	private Mono<Void> deleteUser(@PathVariable("name") String name) {
		return userRepository.deleteById(name);
	}
	
}
