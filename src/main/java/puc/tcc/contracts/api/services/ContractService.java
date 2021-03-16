package puc.tcc.contracts.api.services;


import org.springframework.data.domain.Page;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.persistence.domain.ContractEntity;
import puc.tcc.contracts.api.resources.contract.ContractRequest;
import puc.tcc.contracts.api.resources.contract.ContractResponse;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    ContractResponse saveOrUpdate(final ContractRequest contractRequest) throws ContractsApiException;

    Page<ContractEntity> findAll(int page, int size);

    Optional<ContractResponse> findById(final Long id);

    List<ContractResponse> findAll();

    void delete(final Long id) throws ContractsApiException;
}
