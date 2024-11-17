package com.opzero.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.opzero.core.dto.CommonResponse;
import com.opzero.user.dto.request.ClientCreateRequest;
import com.opzero.user.dto.request.ClientUpdateRequest;
import com.opzero.user.dto.response.ClientDetailResponse;
import com.opzero.user.dto.response.ClientSummaryResponse;
import com.opzero.user.service.ClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientApi {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<CommonResponse<Boolean>> create(@RequestBody ClientCreateRequest request) {
        return ResponseEntity.ok(new CommonResponse<>(clientService.create(request)));
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<ClientSummaryResponse>>> getAllClients() {
        return ResponseEntity.ok(new CommonResponse<>(clientService.getAll(), "Clients listed successfully", 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ClientDetailResponse>> getClientById(@PathVariable(name = "id") String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(clientService.getById(id), "Client details listed successfully", 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Boolean>> updateClient(@PathVariable String id,
            @RequestBody ClientUpdateRequest request) {
        return ResponseEntity
                .ok(new CommonResponse<>(clientService.update(id, request), "Client updated successfully", 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Boolean>> deleteClient(@PathVariable String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(clientService.delete(id), "Client deleted successfully", 200));
    }
    // activate
    // deactivate
}
