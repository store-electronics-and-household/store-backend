package ru.acceleration.store.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.acceleration.store.mapper.AddressMapper;
import ru.acceleration.store.mapper.UserMapper;

@Configuration
public class MapperBean {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public AddressMapper addressMapper() {
        return Mappers.getMapper(AddressMapper.class);
    }
}
