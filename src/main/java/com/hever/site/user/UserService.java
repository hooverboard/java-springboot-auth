package com.hever.site.user;

import com.hever.site.user.dto.request.LoginRequest;
import com.hever.site.user.dto.request.RegisterRequest;
import com.hever.site.user.dto.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // register
    public AuthResponse register(RegisterRequest request) {

        //check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        // create user entity
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // save user to database
        UserEntity saved = userRepository.save(user);

        return new AuthResponse(saved, "User registered successfully");
    }

    // login
    public AuthResponse login(LoginRequest request){

        Optional<UserEntity> opt = userRepository.findByEmail(request.getEmail());

        //check if user exists
        if (opt.isEmpty()){
            throw new RuntimeException("Invalid email");
        }

        UserEntity user = opt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("invalid password");
        }

        return new AuthResponse(user, "user logged in successfully");
    }
}
