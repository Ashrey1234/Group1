package com.example.YoungDev.Service;

import com.example.YoungDev.Model.User;
import com.example.YoungDev.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User>getAllUsers(){return userRepository.findAll();}
    public Optional<User>getUserById(Long Id){return userRepository.findById(Id);}
    public Optional<User>getUserByEmail(String email){return userRepository.findByEmail(email);}
    public User createUser(User user){return userRepository.save(user);}
    public User updateUser(Long Id,User updateUser){ return userRepository.findById(Id).map(user -> {
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());

        user.setConfirmPassword(updateUser.getConfirmPassword());
        return userRepository.save(user);}).orElseThrow(()->new IllegalArgumentException("User not found"));}
    public void deleteUser(Long Id){userRepository.deleteById(Id);}
}
