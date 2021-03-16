package puc.tcc.contracts.api.services;


import org.springframework.data.domain.Page;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.resources.document.DocumentRequest;
import puc.tcc.contracts.api.persistence.domain.DocumentEntity;
import puc.tcc.contracts.api.resources.document.DocumentResponse;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    DocumentResponse saveOrUpdate(final DocumentRequest documentRequest) throws ContractsApiException;

    Page<DocumentEntity> findAll(int page, int size);

    Optional<DocumentResponse> findById(final Long id);

    List<DocumentResponse> findAll();

    void delete(final Long id) throws ContractsApiException;
}
