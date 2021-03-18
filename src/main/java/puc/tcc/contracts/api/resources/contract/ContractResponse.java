package puc.tcc.contracts.api.resources.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import puc.tcc.contracts.api.resources.supplier.SupplierResponse;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {
    private Long id;
    private String title;
    @JsonProperty(value = "dat_start")
    private String datStart;
    @JsonProperty(value = "dat_end")
    private String datEnd;
    private BigDecimal value;
    private String description;
    private SupplierResponse supplier;
}
