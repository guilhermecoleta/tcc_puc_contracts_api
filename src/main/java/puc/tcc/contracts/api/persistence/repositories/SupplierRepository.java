package puc.tcc.contracts.api.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import puc.tcc.contracts.api.persistence.domain.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {

    @Query("FROM SupplierEntity s")
    Page<SupplierEntity> search(Pageable pageable);
}
