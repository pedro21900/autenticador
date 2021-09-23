package manager.excel.rest;

import manager.excel.domain.Company;
import manager.excel.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/api/company")
@RestController
public class CompanyRestController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{idcompany}")
    public ResponseEntity<Company> findAccessLevel(@PathVariable() Long idcompany) throws Exception {
        return ResponseEntity.ok(companyService.findByIdCompany(idcompany)
                .orElseThrow(() -> new NoSuchElementException("Empresa não encontrado")));
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAccessLevel() {
        return ResponseEntity.ok(companyService.findAllCompany());
    }
    @PostMapping
    public Company createrCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PutMapping
    public Company updateAccessLevel(@RequestBody Company company) {
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/{idcompany}")
    public void deleteCompany(@PathVariable() Long idcompany) {
        companyService.deleteCompany(idcompany);

    }

}
