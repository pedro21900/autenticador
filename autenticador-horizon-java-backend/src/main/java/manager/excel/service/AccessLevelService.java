package manager.excel.service;

import manager.excel.domain.AccessLevel;
import manager.excel.repository.AccessLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessLevelService {

    @Autowired
    private AccessLevelRepository accessLevelRepository;

    public AccessLevel saveAccessLevel(AccessLevel accessLevel) {
        return accessLevelRepository.save(accessLevel);
    }

    public AccessLevel updateAccessLevel(AccessLevel accessLevel) {
        return accessLevelRepository.save(accessLevel);
    }

    public void deleteAccessLevel(Long id) {
        accessLevelRepository.deleteById(id);
    }

    public List<AccessLevel> findAccessLevel() throws EntityNotFoundException {
        return accessLevelRepository.findAll().stream().collect(Collectors.toList());
    }
    public List<AccessLevel> findAllAccessLevel() throws EntityNotFoundException {
        return accessLevelRepository.findAll();
    }

    public Optional<AccessLevel> findByIdAccessLevel(Long id) throws EntityNotFoundException {
        return accessLevelRepository.findById(id);
    }
}
