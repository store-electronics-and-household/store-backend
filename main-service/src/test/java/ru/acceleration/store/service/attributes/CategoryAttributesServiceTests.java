package ru.acceleration.store.service.attributes;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acceleration.store.mapper.CategoryAttributeMapper;
import ru.acceleration.store.repository.CategoryAttributeRepository;
import ru.acceleration.store.service.attribute.CategoryAttributesServiceImpl;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAttributesServiceTests {

    @InjectMocks
    CategoryAttributesServiceImpl categoryAttributesService;

    @Mock
    CategoryAttributeRepository categoryAttributeRepository;

    @Spy
    @InjectMocks
    CategoryAttributeMapper mapper = Mappers.getMapper(CategoryAttributeMapper.class);


}
