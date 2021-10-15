package manager.excel.repository;

import manager.excel.domain.User;
import manager.excel.domain.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenReposiroty extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);

}
