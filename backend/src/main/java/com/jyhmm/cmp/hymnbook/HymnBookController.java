package com.jyhmm.cmp.hymnbook;

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
public class HymnBookController {
    private final HymnBookService hymnBookService;

    @GetMapping("/hymn-books")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
        PageDTO pageDTO = hymnBookService.getAll(pageable);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/hymn-book/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        HymnBookDTO hymnBook = hymnBookService.getById(id);
        return ResponseEntity.ok(hymnBook);
    }

    @PostMapping("/hymn-book")
    public ResponseEntity<?> register(@RequestBody HymnBookDTO hymnBookDTO) {
        HymnBook hymnBook = hymnBookService.register(hymnBookDTO);
        URI location = Utils.buildUriWithId(hymnBook.getId());
        return ResponseEntity.created(location).body(MsgConst.REGISTERED);
    }

    @PutMapping("/hymn-book")
    public ResponseEntity<?> update(@RequestBody HymnBookDTO hymnBookDTO) {
        hymnBookService.update(hymnBookDTO);
        return ResponseEntity.ok(MsgConst.UPDATED);
    }
}
