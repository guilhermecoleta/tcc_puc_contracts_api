package puc.tcc.contracts.api.resources.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.persistence.domain.ContractEntity;
import puc.tcc.contracts.api.services.ContractService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContractResource {

    @Autowired
    private ContractService contractService;

    @PostMapping(value = "/contracts", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContractResponse> create(@Valid @RequestBody final ContractRequest contractRequest) throws ContractsApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.saveOrUpdate(contractRequest));
    }

    @PutMapping(value = "/contracts", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContractResponse> update(@Valid @RequestBody final ContractRequest contractRequest) throws ContractsApiException {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.saveOrUpdate(contractRequest));
    }

    @GetMapping(value = "/contracts/{id}")
    public ResponseEntity<ContractResponse> findById(@PathVariable("id") Long id){
        var response = contractService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/contracts")
    public ResponseEntity<List<ContractResponse>> findAll(){
        var response = contractService.findAll();
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/contracts/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ContractsApiException {
        contractService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/contracts/pageable")
    public ResponseEntity<Page<ContractEntity>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){

        var response = contractService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

}
