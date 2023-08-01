package com.jyhmm.cmp.mass;

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
public class MassController {

    private final MassService massService;

    @GetMapping("/all-mass")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
        PageDTO pageDTO = massService.getAll(pageable);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/mass/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        MassDTO massDTO = massService.getById(id);
        return ResponseEntity.ok(massDTO);
    }

    @PostMapping("/mass")
    public ResponseEntity<?> register(@RequestBody MassDTO massDTO) {
        Mass mass = massService.register(massDTO);
        URI location = Utils.buildUriWithId(mass.getId());
        return ResponseEntity.created(location).body(MsgConst.REGISTERED);
    }

    @PutMapping("/mass")
    public ResponseEntity<?> update(@RequestBody MassDTO massDTO) {
        massService.update(massDTO);
        return ResponseEntity.ok(MsgConst.UPDATED);
    }
}
