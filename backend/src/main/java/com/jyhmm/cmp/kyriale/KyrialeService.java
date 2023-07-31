package com.jyhmm.cmp.kyriale;

import com.jyhmm.cmp.common.exception.EntityNotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.utils.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KyrialeService {

    private final ObjectValidator objectValidator;
    private final KyrialeRepository kyrialeRepository;
    private final KyrialeDetailRepository kyrialeDetailRepository;

    public PageDTO getAll(Pageable pageable) {
        Page<Kyriale> page = kyrialeRepository.findAll(pageable);
        List<KyrialeDTO> kyrialeDTOList = page.getContent().stream().map(KyrialeDTO::new).collect(Collectors.toList());
        return new PageDTO(kyrialeDTOList, page);
    }

    public KyrialeDTO getById(Long id) {
        Kyriale kyriale = findKyrialeById(id);
        List<KyrialeDetailDTO> kyrialeDetailDTOList = kyriale.getKyrialeDetails()
                .stream()
                .map(KyrialeDetailDTO::new)
                .collect(Collectors.toList());
        KyrialeDTO kyrialeDTO = new KyrialeDTO(kyriale);
        kyrialeDTO.setKyrialeDetailList(kyrialeDetailDTOList);
        return kyrialeDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Kyriale register(KyrialeDTO kyrialeDTO) {
        objectValidator.validate(kyrialeDTO);
        Kyriale kyriale = new Kyriale();
        kyriale.setValuesFrom(kyrialeDTO);

        for (KyrialeDetailDTO kyrialeDetailDTO : kyrialeDTO.getKyrialeDetailList()) {
            KyrialeDetail kyrialeDetail = createNewKyrialeDetail(kyriale, kyrialeDetailDTO);
            kyriale.getKyrialeDetails().add(kyrialeDetail);
        }
        return kyrialeRepository.save(kyriale);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(KyrialeDTO kyrialeDTO) {
        objectValidator.validate(kyrialeDTO);
        Kyriale kyriale = findKyrialeById(kyrialeDTO.getId());
        kyriale.setValuesFrom(kyrialeDTO);

        for (KyrialeDetailDTO kyrialeDetailDTO : kyrialeDTO.getKyrialeDetailList()) {
            if (kyrialeDetailDTO.getId() == null) {
                KyrialeDetail kyrialeDetail = createNewKyrialeDetail(kyriale, kyrialeDetailDTO);
                kyriale.getKyrialeDetails().add(kyrialeDetail);
            } else {
                KyrialeDetail kyrialeDetail = findKyrialeDetailById(kyrialeDetailDTO.getId());
                kyrialeDetail.setValuesFrom(kyrialeDetailDTO);
            }
        }
        kyrialeRepository.save(kyriale);

        if (kyrialeDTO.getDeleteKyrialeDetailIds() != null) {
            kyrialeDetailRepository.findByIdIn(kyrialeDTO.getDeleteKyrialeDetailIds())
                    .ifPresent(kyrialeDetailRepository::deleteAllInBatch);
        }
    }

    private KyrialeDetail createNewKyrialeDetail(Kyriale kyriale, KyrialeDetailDTO kyrialeDetailDTO) {
        objectValidator.validate(kyrialeDetailDTO);
        KyrialeDetail kyrialeDetail = new KyrialeDetail();
        kyrialeDetail.setKyriale(kyriale);
        kyrialeDetail.setValuesFrom(kyrialeDetailDTO);
        return kyrialeDetail;
    }

    public Kyriale findKyrialeById(Long id) {
        return kyrialeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kyriale with id (" + id + ") not found."));
    }

    public KyrialeDetail findKyrialeDetailById(Long id) {
        return kyrialeDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kyriale Detail with id (" + id + ") not found."));
    }
}
