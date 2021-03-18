package puc.tcc.contracts.api.resources.supplier;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SupplierRequest {
    private Long id;

    @Length(max = 500, message = "O campo deve ter no máximo 500 caracteres")
    @NotBlank(message = "O campo nome deve ser preenchido")
    private String name;
    @Length(max = 14, message = "O campo deve ter no máximo 14 caracteres")
    @NotBlank(message = "O campo cnpj deve ser preenchido")
    @CNPJ
    private String cnpj;
    @Length(max = 256, message = "O campo deve ter no máximo 256 caracteres")
    @NotBlank(message = "O campo email deve ser preenchido")
    @Email(message = "O campo email não é valido")
    private String email;
    @Length(max = 11, message = "O campo deve ter no máximo 11 caracteres")
    @NotBlank(message = "O campo phone deve ser preenchido")
    private String phone;
}
