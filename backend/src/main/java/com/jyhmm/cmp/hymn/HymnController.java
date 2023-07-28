package com.jyhmm.cmp.hymn;

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
public class HymnController {

    private final HymnService hymnService;

    @GetMapping("/hymns")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
        PageDTO pageDTO = hymnService.getAll(pageable);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/hymn/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        HymnDTO hymnDTO = hymnService.getById(id);
        return ResponseEntity.ok(hymnDTO);
    }

    @PostMapping("/hymn")
    public ResponseEntity<?> register(@RequestBody HymnDTO hymnDTO) {
        Hymn hymn = hymnService.register(hymnDTO);
        URI location = Utils.buildUriWithId(hymn.getId());
        return ResponseEntity.created(location).body(MsgConst.REGISTERED);
    }

    @PutMapping("/hymn")
    public ResponseEntity<?> update(@RequestBody HymnDTO hymnDTO) {
        hymnService.update(hymnDTO);
        return ResponseEntity.ok(MsgConst.UPDATED);
    }
}
