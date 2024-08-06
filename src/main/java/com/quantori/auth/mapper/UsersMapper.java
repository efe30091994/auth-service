package com.quantori.auth.mapper;

import com.quantori.auth.model.dto.UsersResponse;
import com.quantori.auth.model.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    UsersResponse toUsersResponseDto(UsersEntity usersEntity);
}
