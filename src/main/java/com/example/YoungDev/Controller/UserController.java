package com.example.YoungDev.Controller;

import com.example.YoungDev.Model.User;
import com.example.YoungDev.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User>getAllUsers(){return userService.getAllUsers();}
    @GetMapping("/{Id}")
    public ResponseEntity<User>getUserById(@PathVariable Long Id){
        Optional<User>user=userService.getUserById(Id);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
    @PostMapping
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }
    @PutMapping("/{Id}")
    public ResponseEntity<User>updateUser(@PathVariable Long Id,@RequestBody User updatedUser){
        try {
            User user=userService.updateUser(Id,updatedUser);
            return ResponseEntity.ok(user);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }@DeleteMapping("/{Id}")
    public ResponseEntity<Void>deleteUser(@PathVariable Long Id){userService.deleteUser(Id);
    return ResponseEntity.noContent().build();}

}
