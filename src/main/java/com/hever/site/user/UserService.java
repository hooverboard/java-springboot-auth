package com.hever.site.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.hever.site.user.dto.UserResponse;
import com.hever.site.user.dto.LoginRequest;
import com.hever.site.user.dto.SignupRequest;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //singup method
    public User signup(SignupRequest request){

        //check if username already exists
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        //check if email already exists
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        //create new user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        //save user to db
        return userRepository.save(user);
    }

    //login method
    public User login(String username, String password){

        //find user by username
        Optional<User> userOptional = userRepository.findByUsername(username);

        //check if user exists
        if (!userOptional.isPresent()){
            throw new RuntimeException("Invalid username or password");
        }

        User user = userOptional.get();

        //check if pw matches
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }
}











































