package com.example.prototypeai.auth.mapper;

import com.example.prototypeai.auth.dto.RegisterRequestDto;
import com.example.prototypeai.user.entity.AiUser;
import com.example.prototypeai.util.trimvalue.TrimUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TrimUtil.class)
public interface AiUserMapper {

    @Mapping(target = "email", source = "email", qualifiedByName = "TrimUtil")
    @Mapping(target = "motDePasseHash", ignore = true)
    @Mapping(target = "role", ignore = true)
    AiUser toEntity(RegisterRequestDto registerRequestDto);

}
