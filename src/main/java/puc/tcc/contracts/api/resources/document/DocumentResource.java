package puc.tcc.contracts.api.resources.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.persistence.domain.DocumentEntity;
import puc.tcc.contracts.api.services.DocumentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DocumentResource {

    @Autowired
    private DocumentService documentService;

    @PostMapping(value = "/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentResponse> create(@Valid @RequestBody final DocumentRequest documentRequest) throws ContractsApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.saveOrUpdate(documentRequest));
    }

    @PutMapping(value = "/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentResponse> update(@Valid @RequestBody final DocumentRequest documentRequest) throws ContractsApiException {
        return ResponseEntity.status(HttpStatus.OK).body(documentService.saveOrUpdate(documentRequest));
    }

    @GetMapping(value = "/documents/{id}")
    public ResponseEntity<DocumentResponse> findById(@PathVariable("id") Long id){
        var response = documentService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/documents")
    public ResponseEntity<List<DocumentResponse>> findAll(){
        var response = documentService.findAll();
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/documents/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ContractsApiException {
        documentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/documents/pageable")
    public ResponseEntity<Page<DocumentEntity>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){

        var response = documentService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

}
