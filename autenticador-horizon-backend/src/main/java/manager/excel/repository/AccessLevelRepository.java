package manager.excel.repository;

import manager.excel.domain.AccessLevel;
import manager.excel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel,Long>{
    Optional<AccessLevel> findByDescription(String description);
}
