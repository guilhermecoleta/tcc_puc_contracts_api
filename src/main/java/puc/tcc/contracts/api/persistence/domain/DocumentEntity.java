package puc.tcc.contracts.api.persistence.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "document")
@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentEntity implements Serializable {
    private static final String SEQ_DOCUMENT_GEN = "SEQ_DOCUMENT_GEN";
    private static final String SQ_DOCUMENT = "SEQ_DOCUMENT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_DOCUMENT_GEN)
    @SequenceGenerator(name = SEQ_DOCUMENT_GEN, sequenceName = SQ_DOCUMENT, allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "dat_updated")
    private LocalDateTime datUpdated;
    private Integer version;
    private String text;
}
