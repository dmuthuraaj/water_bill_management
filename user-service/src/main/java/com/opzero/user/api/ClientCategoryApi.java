package com.opzero.user.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opzero.core.dto.CommonResponse;
import com.opzero.user.dto.request.ClientCategoryCreateRequest;
import com.opzero.user.dto.request.ClientCategoryUpdateRequest;
import com.opzero.user.dto.response.ClientCategoryResponse;
import com.opzero.user.service.ClientCategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/client/category")
@AllArgsConstructor
public class ClientCategoryApi {

    private final ClientCategoryService clientCategoryService;

    @PostMapping
    public ResponseEntity<CommonResponse<Boolean>> create(@RequestBody ClientCategoryCreateRequest request) {
        return ResponseEntity.ok(new CommonResponse<>(true, "Client category created successfully", 200));
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<ClientCategoryResponse>>> getAll() {
        return ResponseEntity
                .ok(new CommonResponse<>(clientCategoryService.getAll(), "Clients listed successfully", 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ClientCategoryResponse>> get(@PathVariable(name = "id") String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(clientCategoryService.get(id), "Client category details listed successfully",
                        200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Boolean>> update(@PathVariable String id,
            @RequestBody ClientCategoryUpdateRequest request) {
        return ResponseEntity
                .ok(new CommonResponse<>(clientCategoryService.update(id, request),
                        "Client category updated successfully", 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Boolean>> delete(@PathVariable String id) {
        return ResponseEntity
                .ok(new CommonResponse<>(clientCategoryService.delete(id), "Client category deleted successfully",
                        200));
    }
}
