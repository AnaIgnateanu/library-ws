package com.task.library.libraryws.service;

import com.task.library.libraryws.domain_author.Author;
import com.task.library.libraryws.domain_author.GetAuthorRequest;
import com.task.library.libraryws.domain_author.GetAuthorResponse;
import com.task.library.libraryws.domain_book.*;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IBookService {
    @WebMethod GetBookResponse getBook(@RequestPayload GetBookRequest request);
    
    @WebMethod GetBookResponse getAllBooks(@RequestPayload GetBookRequest request);

    @WebMethod GetAuthorResponse getAuthor(@RequestPayload GetAuthorRequest request);

    @WebMethod
    AddBookResponse addBook(@RequestPayload AddBookRequest request);
}
