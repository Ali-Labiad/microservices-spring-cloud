package com.ibm.bootcamp.spring.datalake.mapping;

import com.ibm.bootcamp.spring.datalake.FromDto;
import com.ibm.bootcamp.spring.datalake.model.Address;
import com.ibm.bootcamp.spring.datalake.model.Company;
import com.ibm.bootcamp.spring.datalake.model.Geo;
import com.ibm.bootcamp.spring.datalake.model.UserDTO.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends FromDto<User, com.ibm.bootcamp.spring.datalake.model.User> {

    @Mapping(
            target = "geo", source = "address.geo"
    )
    com.ibm.bootcamp.spring.datalake.model.User fromDto(User source);

    Address fromDto(com.ibm.bootcamp.spring.datalake.model.UserDTO.Address source);

    Company fromDto(com.ibm.bootcamp.spring.datalake.model.UserDTO.Company source);

    Geo fromDto(com.ibm.bootcamp.spring.datalake.model.UserDTO.Geo source);

    @AfterMapping
    default void syncId(@MappingTarget com.ibm.bootcamp.spring.datalake.model.User target) {
        if (target.getGeo() != null)
            target.getGeo().setUserId(target.getId());
        if (target.getAddress() != null)
            target.getAddress().setUserId(target.getId());
        if (target.getCompany() != null)
            target.getCompany().setUserId(target.getId());
    }
}
