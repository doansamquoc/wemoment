package com.doansamquoc.wemoment.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.doansamquoc.wemoment.user.dto.UserCreationRequest;
import com.doansamquoc.wemoment.user.dto.UserResponse;
import com.doansamquoc.wemoment.user.entity.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "isActive", ignore = true)
    // @Mapping(target = "hashedPassword", ignore = true)
    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatarUrl", ignore = true)
    @Mapping(target = "bio", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "providers", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUserCreationRequest(UserCreationRequest request);
}
