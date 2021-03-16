package puc.tcc.contracts.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import puc.tcc.contracts.api.persistence.domain.ContractEntity;
import puc.tcc.contracts.api.resources.contract.ContractRequest;
import puc.tcc.contracts.api.resources.contract.ContractResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractMapper {

    @Mappings({
            @Mapping(source = "request.id", target = "id"),
            @Mapping(source = "request.title", target = "title"),
            @Mapping(source = "request.datStart", target = "datStart"),
            @Mapping(source = "request.datEnd", target = "datEnd"),
            @Mapping(source = "request.description", target = "description"),
            @Mapping(source = "request.value", target = "value")
    })
    ContractEntity toModel(ContractRequest request);

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.title", target = "title"),
            @Mapping(source = "entity.datStart", target = "datStart", dateFormat = "dd/MM/yyyy"),
            @Mapping(source = "entity.datEnd", target = "datEnd", dateFormat = "dd/MM/yyyy"),
            @Mapping(source = "entity.description", target = "description"),
            @Mapping(source = "entity.value", target = "value")
    })
    ContractResponse toResponse(ContractEntity entity);

}
