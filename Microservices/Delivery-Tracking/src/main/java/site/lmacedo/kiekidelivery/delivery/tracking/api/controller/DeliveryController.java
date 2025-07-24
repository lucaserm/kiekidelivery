package site.lmacedo.kiekidelivery.delivery.tracking.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import site.lmacedo.kiekidelivery.delivery.tracking.api.model.CourierIdInput;
import site.lmacedo.kiekidelivery.delivery.tracking.api.model.DeliveryInput;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.exception.DomainException;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.Delivery;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.repository.DeliveryRepository;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.service.DeliveryCheckpointService;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.service.DeliveryPreparationService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService deliveryPreparationService;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryCheckpointService deliveryCheckpointService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput deliveryInput) {
        return deliveryPreparationService.draft(deliveryInput);
    }

    @PutMapping("/{deliveryId}")
    public Delivery draft(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryInput deliveryInput) {
        return deliveryPreparationService.edit(deliveryId, deliveryInput);
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable) {
        return new PagedModel<>(deliveryRepository.findAll(pageable));
    }

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{deliveryId}/placement")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void place(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.place(deliveryId);
    }

    @PostMapping("/{deliveryId}/pickups")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickup(@PathVariable UUID deliveryId, @RequestBody @Valid CourierIdInput input) {
        deliveryCheckpointService.pickUp(deliveryId, input);
    }

    @PostMapping("/{deliveryId}/completion")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickup(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.complete(deliveryId);
    }


}
