package ru.acceleration.store.abstraction;

import org.springframework.data.domain.PageRequest;
import ru.acceleration.store.model.enums.ModelSort;

public abstract class PageRequestUtil {
    protected PageRequest createPageRequest(int from, int size) {
        return PageRequest.of(from, size);
    }

    protected PageRequest createPageRequest(int from, int size, ModelSort sort) {
        return PageRequest.of(from, size, sort.getSortValue());
    }
}
