package puc.tcc.contracts.api.resources.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {
    private Long id;
    @Length(max = 100, message = "O campo deve ter no máximo 500 caracteres")
    @NotBlank(message = "O campo titulo deve ser preenchido")
    private String title;
    @JsonProperty(value = "dat_start")
    @NotNull(message = "O campo data início deve ser preenchido")
    private LocalDate datStart;
    @JsonProperty(value = "dat_end")
    private LocalDate datEnd;
    @NotNull(message = "O campo valor deve ser preenchido")
    private BigDecimal value;
    @Length(max = 1000, message = "O campo deve ter no máximo 500 caracteres")
    @NotBlank(message = "O campo descricao deve ser preenchido")
    private String description;
}
