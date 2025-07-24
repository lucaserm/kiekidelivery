package site.lmacedo.kiekidelivery.courier.management.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lmacedo.kiekidelivery.courier.management.api.model.CourierInput;
import site.lmacedo.kiekidelivery.courier.management.domain.model.Courier;
import site.lmacedo.kiekidelivery.courier.management.domain.repository.CourierRepository;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CourierRegistrationService {
    private final CourierRepository courierRepository;

    public Courier create(CourierInput input) {
        Courier courier = Courier.brandNew(input.name(), input.phone());
        return courierRepository.saveAndFlush(courier);
    }

    public Courier update(UUID courierId, @Valid CourierInput input) {
        Courier courier = courierRepository.findById(courierId).orElseThrow();
        courier.setName(input.name());
        courier.setPhone(input.phone());
        return courierRepository.saveAndFlush(courier);
    }
}
