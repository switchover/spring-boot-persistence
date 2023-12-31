package com.bookstore.dto;

import org.springframework.beans.factory.annotation.Value;

public interface VirtualBookDto {
    String getTitle(); // of book

    @Value("#{@authorMapper.buildAuthorDto(target.name, target.genre)}")
    AuthorClassDto getAuthor();
}