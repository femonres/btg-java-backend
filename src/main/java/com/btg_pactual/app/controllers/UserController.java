package com.btg_pactual.app.controllers;

import org.springframework.web.bind.annotation.*;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.usecases.FetchClientUsecase;
import com.btg_pactual.application.usecases.GetProfileUsecase;
import com.btg_pactual.application.usecases.UpdateProfileUsecase;
import com.btg_pactual.application.usecases.ResetBalanceUsecase;
import com.btg_pactual.application.usecases.FetchTransactionHistoryUsecase;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final FetchClientUsecase fetchClientUsecase;
    private final GetProfileUsecase getProfileUsecase;
    private final UpdateProfileUsecase updateProfileUsecase;
    private final ResetBalanceUsecase resetBalanceUsecase;
    private final FetchTransactionHistoryUsecase fetchTransactionHistoryUsecase;

    @GetMapping
    public List<ClientDTO> getUsers() {
        return fetchClientUsecase.execute();
    }

    @GetMapping("/{user_id}")
    public ClientDTO getUserById(@PathVariable("user_id") int userId) {
        return getProfileUsecase.execute(userId);
    }

    @PutMapping("/{user_id}")
    public ClientDTO updateUserProfile(@PathVariable("user_id") int userId, @RequestBody ClientDTO dto) {
        return updateProfileUsecase.execute(userId, dto.getName(), dto.getEmail(), dto.getPhone(), dto.getNotification());
    }

    @PutMapping("/{user_id}/reset")
    public ClientDTO resetBalance(@PathVariable("user_id") int userId) {
        return resetBalanceUsecase.execute(userId);
    }

    @GetMapping("/{user_id}/history")
    public List<TransactionDTO> getTransactionHistory(@PathVariable("user_id") int userId) {
        return fetchTransactionHistoryUsecase.execute(userId);
    }
}
