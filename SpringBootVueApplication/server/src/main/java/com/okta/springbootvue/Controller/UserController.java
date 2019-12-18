package com.okta.springbootvue.Controller;


import com.okta.springbootvue.Entity.Country;
import com.okta.springbootvue.Entity.User;
import com.okta.springbootvue.Entity.Gender;
import com.okta.springbootvue.Entity.Jobs;
import com.okta.springbootvue.Repository.CountryRepository;
import com.okta.springbootvue.Repository.UserRepository;
import com.okta.springbootvue.Repository.JobsRepository;
import com.okta.springbootvue.Repository.GenderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private JobsRepository jobsRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public Collection<User> user() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/user/{username/{password}/{name}/{age}/{gender_id}/{job_id}/{email}/{country_id}/{tel}")
    public User newUser(User newUser,
                        @PathVariable String username,
                        @PathVariable String password,
                        @PathVariable String name,
                        @PathVariable String age,
                        @PathVariable long gender_id,
                        @PathVariable long job_id,
                        @PathVariable String email,
                        @PathVariable long country_id,
                            @PathVariable String tel) {

                                Gender gender = genderRepository.findById(gender_id);
                                Country country = countryRepository.findById(country_id);
                                Jobs jobs = jobsRepository.findById(job_id);
                                User user = new User();
                                user.setUsername(username);
                                user.setPassword(password);
                                user.setName(name);
                                user.setAge(age);
                                user.setGender(gender);
                                user.setJobs(jobs);
                                user.setEmail(email);
                                user.setCountry(country);
                                user.setTel(tel);
                                userRepository.save(user);
                        
                        
                        
                        
                                return userRepository.save(user);
    }
}