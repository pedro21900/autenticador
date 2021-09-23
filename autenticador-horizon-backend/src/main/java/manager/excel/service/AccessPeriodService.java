package manager.excel.service;

import manager.excel.domain.AccessLevel;
import manager.excel.domain.AccessPeriod;
import manager.excel.repository.AccessLevelRepository;
import manager.excel.repository.AccessPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccessPeriodService {
    @Autowired
    private AccessPeriodRepository accessPeriodRepository;

    public AccessPeriod saveAccessPeriod(AccessPeriod accessPeriod) {
        return accessPeriodRepository.save(accessPeriod);
    }

    public List<AccessPeriod> findAllAccessPeriod() throws EntityNotFoundException {
        return accessPeriodRepository.findAll();
    }

    public Optional<AccessPeriod> findByIdAccessPeriod(Long id) throws EntityNotFoundException {
        return accessPeriodRepository.findById(id);
    }
}
