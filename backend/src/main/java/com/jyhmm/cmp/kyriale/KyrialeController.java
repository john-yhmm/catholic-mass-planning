package com.jyhmm.cmp.kyriale;

import com.jyhmm.cmp.common.constants.MsgConst;
import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class KyrialeController {

    private final KyrialeService kyrialeService;

    @GetMapping("/kyriales")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
        PageDTO pageDTO = kyrialeService.getAll(pageable);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/kyriale/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        KyrialeDTO kyrialeDTO = kyrialeService.getById(id);
        return ResponseEntity.ok(kyrialeDTO);
    }

    @PostMapping("/kyriale")
    public ResponseEntity<?> register(@RequestBody KyrialeDTO kyrialeDTO) {
        Kyriale kyriale = kyrialeService.register(kyrialeDTO);
        URI location = Utils.buildUriWithId(kyriale.getId());
        return ResponseEntity.created(location).body(MsgConst.REGISTERED);
    }

    @PutMapping("/kyriale")
    public ResponseEntity<?> update(@RequestBody KyrialeDTO kyrialeDTO) {
        kyrialeService.update(kyrialeDTO);
        return ResponseEntity.ok(MsgConst.UPDATED);
    }
}
