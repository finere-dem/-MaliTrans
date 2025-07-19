package com.malitrans.transport.mapper;

import com.malitrans.transport.dto.NoteDTO;
import com.malitrans.transport.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    @Mapping(source = "fromUser.id", target = "fromUserId")
    @Mapping(source = "toUser.id", target = "toUserId")
    NoteDTO toDto(Note note);

    @Mapping(target = "fromUser.id", source = "fromUserId")
    @Mapping(target = "toUser.id", source = "toUserId")
    Note toEntity(NoteDTO dto);
}
