package ru.telanors.rest.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.telanors.rest.DTO.DocumentDTO;
import ru.telanors.rest.entity.Document;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentMapper extends BaseMapper<Document, DocumentDTO> {
    @Override
    DocumentDTO toDTO(Document entity);

    @Override
    Document toEntity(DocumentDTO dto);
}
