package ru.acceleration.store.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum ModelSort {

    NAME(Sort.by(Sort.Direction.ASC, "name")),
    DESC_PRICE(Sort.by(Sort.Direction.DESC, "price")),
    ASC_PRICE(Sort.by(Sort.Direction.ASC, "price"));

    private final Sort sortValue;
}
