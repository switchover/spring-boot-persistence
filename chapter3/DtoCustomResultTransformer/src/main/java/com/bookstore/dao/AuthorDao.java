package com.bookstore.dao;

import com.bookstore.dto.AuthorDto;

import java.util.List;

public interface AuthorDao {
    List<AuthorDto> fetchAuthorWithBook();
}