package com.jyhmm.cmp.slidesetting;

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
public class SlideSettingController {

    private final SlideSettingService slideSettingService;

    @GetMapping("/slide-settings")
    public ResponseEntity<?> getAll(@PageableDefault Pageable pageable) {
        PageDTO pageDTO = slideSettingService.getAll(pageable);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/slide-setting/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        SlideSettingDTO slideSettingDTO = slideSettingService.getById(id);
        return ResponseEntity.ok(slideSettingDTO);
    }

    @PostMapping("/slide-setting")
    public ResponseEntity<?> register(@RequestBody SlideSettingDTO slideSettingDTO) {
        SlideSetting slideSetting = slideSettingService.register(slideSettingDTO);
        URI location = Utils.buildUriWithId(slideSetting.getId());
        return ResponseEntity.created(location).body(MsgConst.REGISTERED);
    }

    @PutMapping("/slide-setting")
    public ResponseEntity<?> update(@RequestBody SlideSettingDTO slideSettingDTO) {
        slideSettingService.update(slideSettingDTO);
        return ResponseEntity.ok(MsgConst.UPDATED);
    }
}
