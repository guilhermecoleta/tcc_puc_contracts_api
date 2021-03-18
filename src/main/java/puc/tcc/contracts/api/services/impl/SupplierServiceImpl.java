package puc.tcc.contracts.api.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.mapper.SupplierMapper;
import puc.tcc.contracts.api.persistence.domain.SupplierEntity;
import puc.tcc.contracts.api.persistence.repositories.SupplierRepository;
import puc.tcc.contracts.api.resources.supplier.SupplierRequest;
import puc.tcc.contracts.api.resources.supplier.SupplierResponse;
import puc.tcc.contracts.api.services.SupplierService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    @Transactional
    public SupplierResponse saveOrUpdate(final SupplierRequest supplierRequest) throws ContractsApiException {
        var model = supplierMapper.toModel(supplierRequest);
        model = supplierRepository.save(model);
        log.info("Supplier saved/updated id={}", model.getId());
        return supplierMapper.toResponse(model);
    }

    @Override
    public Page<SupplierEntity> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return supplierRepository.search(pageRequest);
    }

    @Override
    public Optional<SupplierResponse> findById(final Long id){
        var supplier = supplierRepository.findById(id);
        return supplier.map(supplierMapper::toResponse);
    }

    @Override
    public List<SupplierResponse> findAll() {
        List<SupplierResponse> items = new ArrayList<>();
        var suppliers = supplierRepository.findAll();
        suppliers.forEach(item -> items.add(supplierMapper.toResponse(item)));
        return items;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws ContractsApiException {
        var supplier = supplierRepository.findById(id);
        if(supplier.isEmpty()){
            throw new ContractsApiException(HttpStatus.NOT_FOUND, "supplier", "Fornecedor não existe!");
        }
        supplierRepository.deleteById(id);
    }
}
