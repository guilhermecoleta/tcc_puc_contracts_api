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
import puc.tcc.contracts.api.persistence.repositories.ContractRepository;
import puc.tcc.contracts.api.resources.contract.ContractRequest;
import puc.tcc.contracts.api.resources.contract.ContractResponse;
import puc.tcc.contracts.api.services.impl.ContractServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest extends SampleBaseTestCase {

    @Mock
    private ContractMapper contractMapper;

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService = new ContractServiceImpl();

    @Test
    @DisplayName("Salvar contrato com sucesso")
    void save() throws ContractsApiException {
        when(contractMapper.toModel(any())).thenReturn(getContractSaveEntity());

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
                .build();
    }

    private ContractRequest getContractSaveRequest(){
        return ContractRequest.builder()
                .title("Title")
                .datStart(LocalDate.of(2021, 4, 10))
                .datEnd(LocalDate.of(2021, 4, 30))
                .description("description")
                .value(BigDecimal.valueOf(100))
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
        when(contractMapper.toResponse(any())).thenReturn(getContractUpdateResponse());

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
                .build();
    }

    private ContractResponse getContractUpdateResponse(){
        return ContractResponse.builder()
                .id(1L)
                .title("Title")
                .datStart("10/04/2021")
                .datEnd("29/04/2021")
                .description("description")
                .value(BigDecimal.valueOf(100))
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
                .build();
    }

    private void assertUpdateContract(ContractRequest expected, ContractEntity actual){
        assertEquals(expected.getId(), actual.getId());
        assertSaveContract(expected, actual);
    }



}
