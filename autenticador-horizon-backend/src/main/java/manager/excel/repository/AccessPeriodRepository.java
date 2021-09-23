package manager.excel.repository;

import manager.excel.domain.AccessPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessPeriodRepository extends JpaRepository<AccessPeriod, Long> {
}
