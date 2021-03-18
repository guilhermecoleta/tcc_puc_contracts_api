package puc.tcc.contracts.api.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import puc.tcc.contracts.api.SampleBaseTestCase;
import puc.tcc.contracts.api.exception.ContractsApiException;
import puc.tcc.contracts.api.mapper.ContractMapper;
import puc.tcc.contracts.api.persistence.domain.ContractEntity;
import puc.tcc.contracts.api.persistence.domain.SupplierEntity;
import puc.tcc.contracts.api.persistence.repositories.ContractRepository;
import puc.tcc.contracts.api.persistence.repositories.SupplierRepository;
import puc.tcc.contracts.api.resources.contract.ContractRequest;
import puc.tcc.contracts.api.resources.contract.ContractResponse;
import puc.tcc.contracts.api.services.impl.ContractServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest extends SampleBaseTestCase {

    @Mock
    private ContractMapper contractMapper;

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private final ContractService contractService = new ContractServiceImpl();

    @Mock
    private SupplierRepository supplierRepository;

    @Test
    @DisplayName("Salvar contrato com sucesso")
    void save() throws ContractsApiException {
        when(contractMapper.toModel(any())).thenReturn(getContractSaveEntity());
        when(supplierRepository.findById(anyLong())).thenReturn(getSupplier());

        contractService.saveOrUpdate(getContractSaveRequest());
        ArgumentCaptor<ContractEntity> captor = ArgumentCaptor.forClass(ContractEntity.class);
        verify(contractRepository).save(captor.capture());

        var contractEntity = captor.getValue();
        assertSaveContract(getContractSaveRequest(), contractEntity);
    }

    private ContractEntity getContractSaveEntity(){
        return ContractEntity.builder()
                .title("Title")
                .datStart(LocalDate.of(2021, 4, 10))
                .datEnd(LocalDate.of(2021, 4, 30))
                .description("description")
                .value(BigDecimal.valueOf(100))
                .supplier(SupplierEntity.builder().id(1L).build())
                .build();
    }

    private ContractRequest getContractSaveRequest(){
        return ContractRequest.builder()
                .title("Title")
                .datStart(LocalDate.of(2021, 4, 10))
                .datEnd(LocalDate.of(2021, 4, 30))
                .description("description")
                .value(BigDecimal.valueOf(100))
                .supplierId(1L)
                .build();
    }


    private void assertSaveContract(ContractRequest expected, ContractEntity actual){
        assertEquals(expected.getDatEnd(), actual.getDatEnd());
        assertEquals(expected.getDatStart(), actual.getDatStart());
        assertEquals(expected.getValue(), actual.getValue());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    @DisplayName("Atualizar contrato com sucesso")
    void update() throws ContractsApiException {
        when(contractMapper.toModel(any())).thenReturn(getContractUpdateEntity());
        when(supplierRepository.findById(anyLong())).thenReturn(getSupplier());

        contractService.saveOrUpdate(getContractUpdateRequest());
        ArgumentCaptor<ContractEntity> captor = ArgumentCaptor.forClass(ContractEntity.class);
        verify(contractRepository).save(captor.capture());

        var contractEntity = captor.getValue();
        assertUpdateContract(getContractUpdateRequest(), contractEntity);
    }

    private ContractEntity getContractUpdateEntity(){
        return ContractEntity.builder()
                .id(1L)
                .title("Title 2")
                .datStart(LocalDate.of(2021, 4, 10))
                .datEnd(LocalDate.of(2021, 4, 29))
                .description("description 2")
                .value(BigDecimal.valueOf(100))
                .supplier(SupplierEntity.builder().id(1L).build())
                .build();
    }

    private ContractRequest getContractUpdateRequest(){
        return ContractRequest.builder()
                .id(1L)
                .title("Title 2")
                .datStart(LocalDate.of(2021, 4, 10))
                .datEnd(LocalDate.of(2021, 4, 29))
                .description("description 2")
                .value(BigDecimal.valueOf(100))
                .supplierId(1L)
                .build();
    }

    private void assertUpdateContract(ContractRequest expected, ContractEntity actual){
        assertEquals(expected.getId(), actual.getId());
        assertSaveContract(expected, actual);
    }


    @Test
    @DisplayName("Fornecedor não existe")
    void saveWithoutSupplier(){
        when(contractMapper.toModel(any())).thenReturn(getContractSaveEntity());
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ContractsApiException.class, () -> {
            contractService.saveOrUpdate(getContractSaveRequest());
        });

        String expectedMessage = "Fornecedor não existe";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Optional<SupplierEntity> getSupplier() {
        return Optional.of(SupplierEntity.builder().id(1L).build());
    }


}
