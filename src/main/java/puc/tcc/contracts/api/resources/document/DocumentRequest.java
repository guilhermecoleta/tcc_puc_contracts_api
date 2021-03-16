package puc.tcc.contracts.api.resources.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {
    private Long id;
    @Length(max = 50, message = "O campo deve ter no máximo 500 caracteres")
    @NotBlank(message = "O campo nome deve ser preenchido")
    private String name;
    @Length(max = 30000, message = "O campo deve ter no máximo 14 caracteres")
    @NotBlank(message = "O campo texto deve ser preenchido")
    private String text;
}
