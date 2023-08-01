package com.jyhmm.cmp.mass;

import com.jyhmm.cmp.common.exception.EntityNotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.utils.ObjectValidator;
import com.jyhmm.cmp.hymn.Hymn;
import com.jyhmm.cmp.hymn.HymnService;
import com.jyhmm.cmp.kyriale.Kyriale;
import com.jyhmm.cmp.kyriale.KyrialeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MassService {

    private final ObjectValidator objectValidator;
    private final MassRepository massRepository;
    private final MassDetailRepository massDetailRepository;
    private final HymnService hymnService;
    private final KyrialeService kyrialeService;

    public PageDTO getAll(Pageable pageable) {
        Page<Mass> page = massRepository.findAll(pageable);
        List<MassDTO> massDTOList = page.getContent().stream().map(MassDTO::new).collect(Collectors.toList());
        return new PageDTO(massDTOList, page);
    }

    public MassDTO getById(Long id) {
        Mass mass = findMassById(id);
        List<MassDetailDTO> massDetailDTOList = mass.getMassDetails()
                .stream()
                .map(MassDetailDTO::new)
                .collect(Collectors.toList());
        MassDTO massDTO = new MassDTO(mass);
        massDTO.setMassDetailList(massDetailDTOList);
        return massDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Mass register(MassDTO massDTO) {
        objectValidator.validate(massDTO);
        Mass mass = new Mass();
        mass.setValuesFrom(massDTO);

        for (MassDetailDTO massDetailDTO : massDTO.getMassDetailList()) {
            MassDetail massDetail = createNewMassDetail(mass, massDetailDTO);
            mass.getMassDetails().add(massDetail);
        }
        return massRepository.save(mass);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(MassDTO massDTO) {
        objectValidator.validate(massDTO);
        Mass mass = findMassById(massDTO.getId());
        mass.setValuesFrom(massDTO);

        for (MassDetailDTO massDetailDTO : massDTO.getMassDetailList()) {
            if (massDetailDTO.getId() == null) {
                MassDetail massDetail = createNewMassDetail(mass, massDetailDTO);
                mass.getMassDetails().add(massDetail);
            } else {
                updateMassDetail(massDetailDTO);
            }
        }
        massRepository.save(mass);

        if (massDTO.getDeleteMassDetailIds() != null) {
            massDetailRepository.findByIdIn(massDTO.getDeleteMassDetailIds())
                    .ifPresent(massDetailRepository::deleteAllInBatch);
        }
    }

    private MassDetail createNewMassDetail(Mass mass, MassDetailDTO massDetailDTO) {
        Hymn hymn = hymnService.findHymnById(massDetailDTO.getHymnId());
        Kyriale kyriale = kyrialeService.findKyrialeById(massDetailDTO.getKyrialeId());

        MassDetail massDetail = new MassDetail();
        massDetail.setMass(mass);
        massDetail.setValuesFrom(massDetailDTO);
        massDetail.setHymn(hymn);
        massDetail.setKyriale(kyriale);
        return massDetail;
    }

    private void updateMassDetail(MassDetailDTO massDetailDTO) {
        Hymn hymn = hymnService.findHymnById(massDetailDTO.getHymnId());
        Kyriale kyriale = kyrialeService.findKyrialeById(massDetailDTO.getKyrialeId());

        MassDetail massDetail = findMassDetailById(massDetailDTO.getId());
        massDetail.setValuesFrom(massDetailDTO);
        massDetail.setHymn(hymn);
        massDetail.setKyriale(kyriale);
    }

    public Mass findMassById(Long id) {
        return massRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mass with id (" + id + ") not found."));
    }

    public MassDetail findMassDetailById(Long id) {
        return massDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mass Detail with id (" + id + ") not found."));
    }
}
