package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.dto.DisMaterialDonationRequireDto;
import com.xw.project.dto.DisMaterialDonationRequireMaterialDto;
import com.xw.project.entity.DisMaterialDonationRequire;

public interface DisMaterialDonationRequireService {

    IPage<DisMaterialDonationRequire> page(DisMaterialDonationRequireDto disMaterialDonationRequireDto);

    void update(DisMaterialDonationRequire disMaterialDonationRequire);

    void delete(String id);

    void save(DisMaterialDonationRequireMaterialDto disMaterialDonationRequireMaterialDto);
}