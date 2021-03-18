package puc.tcc.contracts.api.resources.supplier;

import lombok.Data;

@Data
public class SupplierResponse {
    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
}
