package puc.tcc.contracts.api.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import puc.tcc.contracts.api.persistence.domain.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {

    @Query("FROM ContractEntity d")
    Page<ContractEntity> search(Pageable pageable);
}
