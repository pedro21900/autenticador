package manager.excel.service;

import manager.excel.domain.AccessLevel;
import manager.excel.domain.Company;
import manager.excel.repository.AccessLevelRepository;
import manager.excel.repository.CompanyRepository;
import org.hibernate.query.criteria.internal.expression.CoalesceExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public List<Company> findAllCompany() throws EntityNotFoundException {
        return companyRepository.findAll();
    }

    public Optional<Company> findByIdCompany(Long id) throws EntityNotFoundException {
        return companyRepository.findById(id);
    }
}
