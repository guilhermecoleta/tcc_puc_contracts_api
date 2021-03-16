package puc.tcc.contracts.api.resources.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {
    private Long id;
    private String name;
    @JsonProperty(value = "dat_updated")
    private String datUpdated;
    private Integer version;
    private String text;
}
