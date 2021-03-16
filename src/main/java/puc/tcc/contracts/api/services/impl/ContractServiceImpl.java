package puc.tcc.contracts.api.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.mapper.ContractMapper;
import puc.tcc.contracts.api.persistence.domain.ContractEntity;
import puc.tcc.contracts.api.persistence.repositories.ContractRepository;
import puc.tcc.contracts.api.resources.contract.ContractRequest;
import puc.tcc.contracts.api.resources.contract.ContractResponse;
import puc.tcc.contracts.api.services.ContractService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private ContractRepository contractRepository;

    @Override
    @Transactional
    public ContractResponse saveOrUpdate(final ContractRequest contractRequest) {
        var model = contractMapper.toModel(contractRequest);
        model = contractRepository.save(model);
        log.info("contract saved/updated contract={}", model);
        return contractMapper.toResponse(model);
    }

    @Override
    public Page<ContractEntity> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return contractRepository.search(pageRequest);
    }

    @Override
    public Optional<ContractResponse> findById(final Long id){
        var contract = contractRepository.findById(id);
        return contract.map(contractMapper::toResponse);
    }

    @Override
    public List<ContractResponse> findAll() {
        List<ContractResponse> items = new ArrayList<>();
        var contracts = contractRepository.findAll();
        contracts.forEach(item -> items.add(contractMapper.toResponse(item)));
        return items;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws ContractsApiException {
        var contract = contractRepository.findById(id);
        if(contract.isEmpty()){
            throw new ContractsApiException(HttpStatus.NOT_FOUND, "contract", "Contrato não existe!");
        }
        contractRepository.deleteById(id);
    }
}
