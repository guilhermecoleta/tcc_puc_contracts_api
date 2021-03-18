package puc.tcc.contracts.api.persistence.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContractEntity implements Serializable {
    private static final String SEQ_CONTRACT_GEN = "SEQ_CONTRACT_GEN";
    private static final String SEQ_CONTRACT = "SEQ_CONTRACT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_CONTRACT_GEN)
    @SequenceGenerator(name = SEQ_CONTRACT_GEN, sequenceName = SEQ_CONTRACT, allocationSize = 1)
    private Long id;
    private String title;
    @Column(name = "dat_start")
    private LocalDate datStart;
    @Column(name = "dat_end")
    private LocalDate datEnd;
    @Column(name = "contract_value")
    private BigDecimal value;
    private String description;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}
