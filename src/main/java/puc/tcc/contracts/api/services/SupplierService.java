package puc.tcc.contracts.api.services;


import org.springframework.data.domain.Page;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.persistence.domain.SupplierEntity;
import puc.tcc.contracts.api.resources.supplier.SupplierRequest;
import puc.tcc.contracts.api.resources.supplier.SupplierResponse;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    SupplierResponse saveOrUpdate(final SupplierRequest supplierRequest) throws ContractsApiException;

    Page<SupplierEntity> findAll(int page, int size);

    Optional<SupplierResponse> findById(final Long id);

    List<SupplierResponse> findAll();

    void delete(final Long id) throws ContractsApiException;
}
