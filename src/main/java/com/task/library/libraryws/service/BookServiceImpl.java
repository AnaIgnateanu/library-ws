package com.task.library.libraryws.service;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.task.library.libraryws.domain_author.GetAuthorRequest;
import com.task.library.libraryws.domain_author.GetAuthorResponse;
import com.task.library.libraryws.domain_book.AddBookRequest;
import com.task.library.libraryws.domain_book.AddBookResponse;
import com.task.library.libraryws.domain_book.Book;
import com.task.library.libraryws.domain_book.GetBookRequest;
import com.task.library.libraryws.domain_book.GetBookResponse;
import com.task.library.libraryws.domain_book.ServiceStatus;
import com.task.library.libraryws.repository.AuthorRepository;
import com.task.library.libraryws.repository.BookRepository;

@Endpoint
@WebService
public class BookServiceImpl implements IBookService {
    private static final String NAMESPACE_URI = "http://library.task.com";
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(bookRepository.findBookById(request.getId()));

        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorRequest")
    @ResponsePayload
    public GetAuthorResponse getAuthor(@RequestPayload GetAuthorRequest request) {
        GetAuthorResponse response = new GetAuthorResponse();
        response.setAuthor(authorRepository.findAuthorById(request.getId()));
        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
    @ResponsePayload
    public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
        AddBookResponse response = new AddBookResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());

        book.setAuthor(authorRepository.findAuthorIdByName(request.getAuthor()).getId());
        Book added = bookRepository.save(book);

        boolean flag = added.getIsbn().equals(request.getIsbn()) && added.getTitle().equals(request.getTitle());
        if (!flag) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");
            response.setResponse(serviceStatus);
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setResponse(serviceStatus);
        }
        return response;
    }

	@Override
	public GetBookResponse getAllBooks(GetBookRequest request) {
		 GetBookResponse response = new GetBookResponse();
	        //response.setBooks(bookRepository.findAllBooks();

	        return response;
	}
}
