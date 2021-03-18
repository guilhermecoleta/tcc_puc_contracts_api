package puc.tcc.contracts.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import puc.tcc.contracts.api.persistence.domain.SupplierEntity;
import puc.tcc.contracts.api.resources.supplier.SupplierRequest;
import puc.tcc.contracts.api.resources.supplier.SupplierResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {

    @Mappings({
            @Mapping(source = "request.id", target = "id"),
            @Mapping(source = "request.cnpj", target = "cnpj"),
            @Mapping(source = "request.name", target = "name"),
            @Mapping(source = "request.phone", target = "phone"),
            @Mapping(source = "request.email", target = "email")
    })
    SupplierEntity toModel(SupplierRequest request);

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.cnpj", target = "cnpj"),
            @Mapping(source = "entity.name", target = "name"),
            @Mapping(source = "entity.phone", target = "phone"),
            @Mapping(source = "entity.email", target = "email")
    })
    SupplierResponse toResponse(SupplierEntity entity);

}
