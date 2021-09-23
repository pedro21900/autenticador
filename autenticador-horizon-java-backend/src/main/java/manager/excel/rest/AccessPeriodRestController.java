package manager.excel.rest;


import manager.excel.domain.AccessPeriod;
import manager.excel.service.AccessPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/api/accessperiod")
@RestController
public class AccessPeriodRestController {

    @Autowired
    private AccessPeriodService accessPeriodService;

    @PostMapping
    public ResponseEntity<AccessPeriod> createAccessPeriod(@RequestBody AccessPeriod accessPeriod) {
        return ResponseEntity.ok(accessPeriodService.saveAccessPeriod(accessPeriod));
    }

    @GetMapping
    public ResponseEntity<List<AccessPeriod>> findAllAccessPeriod() {
        return ResponseEntity.ok(accessPeriodService.findAllAccessPeriod());
    }

    @GetMapping("/{idaccessperiod}")
    public ResponseEntity<AccessPeriod> findAccessLevel(@PathVariable() Long idaccessperiod) throws Exception {
        return ResponseEntity.ok(accessPeriodService
                .findByIdAccessPeriod(idaccessperiod)
                .orElseThrow(() -> new NoSuchElementException("Periodo de acesso  não encontrado")));
    }

}
