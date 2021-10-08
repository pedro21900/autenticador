package manager.excel.repository;

import manager.excel.domain.User;
import manager.excel.repository.ex.UserRepositoryEx;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
    public interface UserRepository extends  JpaRepository<User,Long> ,JpaSpecificationExecutor<User>,UserRepositoryEx{
    Optional<User> findByUsername(String username);
}
