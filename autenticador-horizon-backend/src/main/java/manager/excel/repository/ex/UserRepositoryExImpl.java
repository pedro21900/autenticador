package manager.excel.repository.ex;


import manager.excel.core.repository.AbstractRepositoryEx;
import manager.excel.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryExImpl extends AbstractRepositoryEx<User, Long> implements UserRepositoryEx {
}
