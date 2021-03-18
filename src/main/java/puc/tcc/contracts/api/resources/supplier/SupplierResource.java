package puc.tcc.contracts.api.resources.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.persistence.domain.SupplierEntity;
import puc.tcc.contracts.api.services.SupplierService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SupplierResource {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "/suppliers", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierResponse> create(@Valid @RequestBody final SupplierRequest supplierRequest) throws ContractsApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.saveOrUpdate(supplierRequest));
    }

    @PutMapping(value = "/suppliers", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierResponse> update(@Valid @RequestBody final SupplierRequest supplierRequest) throws ContractsApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.saveOrUpdate(supplierRequest));
    }

    @GetMapping(value = "/suppliers/{id}")
    public ResponseEntity<SupplierResponse> findById(@PathVariable("id") Long id){
        var response = supplierService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/suppliers")
    public ResponseEntity<List<SupplierResponse>> findAll(){
        var response = supplierService.findAll();
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/suppliers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ContractsApiException {
        supplierService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/suppliers/pageable")
    public ResponseEntity<Page<SupplierEntity>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){

        var response = supplierService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

}
