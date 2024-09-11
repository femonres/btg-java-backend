package com.btg_pactual.app.controllers;

import org.springframework.web.bind.annotation.*;

import com.btg_pactual.application.dto.ClientDTO;
import com.btg_pactual.application.dto.TransactionDTO;
import com.btg_pactual.application.dto.UpdateProfileClientRequest;
import com.btg_pactual.application.usecases.FetchClientUsecase;
import com.btg_pactual.application.usecases.GetProfileUsecase;
import com.btg_pactual.application.usecases.UpdateProfileUsecase;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import com.btg_pactual.application.usecases.ResetBalanceUsecase;
import com.btg_pactual.application.usecases.FetchTransactionHistoryUsecase;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    @Operation(summary = "Listar todos los usuarios")
    public List<ClientDTO> fetchUsers() {
        return fetchClientUsecase.execute(null)
            .stream()
            .sorted(Comparator.comparingInt(ClientDTO::getId))
            .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Obtener un usuario por su ID")
    public ClientDTO getUserById(@PathVariable int userId) {
        return getProfileUsecase.execute(userId);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Actualizar un usuario por su ID")
    public ClientDTO updateUserProfile(@PathVariable int userId, @Valid @RequestBody UpdateProfileClientRequest profileClientDTO) {
        profileClientDTO.setUserId(userId);
        return updateProfileUsecase.execute(profileClientDTO);
    }

    @PutMapping("/{userId}/reset")
    @Operation(summary = "Resetear el balance de un usuario por su ID")
    public ClientDTO resetBalance(@PathVariable int userId) {
        return resetBalanceUsecase.execute(userId);
    }

    @GetMapping("/{userId}/history")
    @Operation(summary = "Obtener el historial de transacciones de un usuario por su ID")
    public List<TransactionDTO> fetchTransactionHistory(@PathVariable int userId) {
        return fetchTransactionHistoryUsecase.execute(userId)
            .stream()
            .sorted((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()))
            .collect(Collectors.toList());
    }
}
