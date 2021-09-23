package manager.excel.rest;

import manager.excel.domain.AccessLevel;
import manager.excel.service.AccessLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/api/accesslevel")
@RestController
public class AcessLevelRestController {

    @Autowired
    private AccessLevelService accessLevelService;

    @GetMapping("/{idaccesslevel}")
    public ResponseEntity<AccessLevel> findAccessLevel(@PathVariable() Long idAccesslevel) throws Exception {
        return ResponseEntity.ok(accessLevelService
                .findByIdAccessLevel(idAccesslevel)
                .orElseThrow(() -> new NoSuchElementException("Nivel de acesso não encontrado")));
    }

    @GetMapping
    public ResponseEntity<List<AccessLevel>> findAccessLevel() {
        return ResponseEntity.ok(accessLevelService.findAllAccessLevel());
    }


    @PutMapping
    public AccessLevel updateAccessLevel(@RequestBody AccessLevel accessLevel) {
        return accessLevelService.saveAccessLevel(accessLevel);
    }

    @DeleteMapping("/{idaccesslevel}")
    public void deleteAccessLevel(@PathVariable() Long idAccesslevel) {
        accessLevelService.deleteAccessLevel(idAccesslevel);

    }

    @PostMapping
    public AccessLevel createrAccessLevel(@RequestBody AccessLevel accessLevel) {
        return accessLevelService.saveAccessLevel(accessLevel);
    }
}
