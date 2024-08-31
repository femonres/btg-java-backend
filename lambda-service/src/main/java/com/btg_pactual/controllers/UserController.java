package com.btg_pactual.controllers;

import com.example.dto.ClientDTO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ClientDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public ClientDTO getUserById(@PathVariable("user_id") int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{user_id}")
    public void updateUserProfile(@PathVariable("user_id") int userId, @RequestBody ClientDTO clientDTO) {
        userService.updateUserProfile(userId, clientDTO);
    }

    @PutMapping("/{user_id}/reset")
    public void resetBalance(@PathVariable("user_id") int userId) {
        userService.resetBalance(userId);
    }

    @GetMapping("/{user_id}/history")
    public List<TransactionDTO> getTransactionHistory(@PathVariable("user_id") int userId) {
        return userService.getTransactionHistory(userId);
    }
}
