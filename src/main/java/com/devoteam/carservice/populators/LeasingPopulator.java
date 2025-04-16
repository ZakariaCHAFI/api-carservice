package com.devoteam.carservice.populators;

import com.devoteam.carservice.dto.LeasingDto;
import com.devoteam.carservice.models.LeasingModel;
import com.devoteam.carservice.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class LeasingPopulator {

    private final CustomerPopulator customerPopulator;

    public LeasingPopulator(CustomerPopulator customerPopulator) {
        this.customerPopulator = customerPopulator;
    }

    public LeasingModel toModel(LeasingDto leasingDto) {
        if (leasingDto == null) {
            return null;
        }

        LeasingModel.LeasingModelBuilder leasingModel = LeasingModel.builder();
        leasingModel.matricule(leasingDto.getMatricule());
        leasingModel.customer(customerPopulator.toModel(leasingDto.getCustomerDto()));
        leasingModel.quantity(leasingDto.getQuantity());
        leasingModel.initialKM(leasingDto.getInitialKM());
        leasingModel.lastKM(leasingDto.getLastKM());
        leasingModel.startDate(DateUtils.convertDate(leasingDto.getStartDate()));
        leasingModel.endDate(DateUtils.convertDate(leasingDto.getEndDate()));
        return leasingModel.build();
    }

    public LeasingDto toDto(LeasingModel leasingModel) {
        if (leasingModel == null) {
            return null;
        }
        LeasingDto.LeasingDtoBuilder leasing = LeasingDto.builder();
        leasing.id(leasingModel.getId());
        leasing.matricule(leasingModel.getMatricule());
        leasing.customerDto(customerPopulator.toDto(leasingModel.getCustomer()));
        leasing.quantity(leasingModel.getQuantity());
        leasing.initialKM(leasingModel.getInitialKM());
        leasing.lastKM(leasingModel.getLastKM());
        leasing.price(leasingModel.getPrice());
        leasing.total(leasingModel.getTotal());
        leasing.startDate(leasingModel.getStartDate().toString());
        leasing.endDate(leasingModel.getEndDate().toString());
        return leasing.build();
    }

    public void copy(LeasingDto leasingDto, LeasingModel leasingModel) {
        // TODO impl
    }
}
