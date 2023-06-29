package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.constants.MsgConst;
import com.jyhmm.cmp.common.utils.UriUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ResponseEntity<?> getHymnBooks(@ModelAttribute HymnBookDTO params, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(hymnBookService.getAllByFilter(params, pageable));
    }

    @GetMapping("/hymn-book/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hymnBookService.getById(id));
    }

    @PostMapping("/hymn-book")
    public ResponseEntity<?> register(@RequestBody HymnBookDTO hymnBookDTO) {
        hymnBookDTO.validate();
        HymnBook hymnBook = hymnBookService.register(hymnBookDTO);
        URI location = UriUtils.getUriWithId(hymnBook.getId());
        return ResponseEntity.created(location).body(MsgConst.REGISTERED);
    }

    @PutMapping("/hymn-book")
    public ResponseEntity<?> update(@RequestBody HymnBookDTO hymnBookDTO) {
        hymnBookDTO.validate();
        hymnBookService.update(hymnBookDTO);
        return ResponseEntity.ok(MsgConst.UPDATED);
    }
}
