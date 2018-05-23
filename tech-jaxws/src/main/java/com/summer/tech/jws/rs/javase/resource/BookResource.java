package com.summer.tech.jws.rs.javase.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.summer.tech.jws.rs.javase.entity.Book;
import com.summer.tech.jws.rs.javase.entity.Books;
import com.summer.tech.jws.rs.javase.service.BookService;

@Path("books")
public class BookResource {
	private static final Logger LOGGER = Logger.getLogger(BookResource.class);
	// 关注点1：Service实例用于处理业务逻辑

	private BookService bookService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Books getBooks() {
		final Books books = bookService.getBooks();
		BookResource.LOGGER.debug(books);
		return books;
	}

	@Path("{bookId:[0-9]*}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book getBookByPath(@PathParam("bookId") final Long bookId) {
		final Book book = bookService.getBook(bookId);
		BookResource.LOGGER.debug(book);
		return book;
	}

	@Path("/book")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book getBookByQuery(@QueryParam("id") final Long bookId) {
		final Book book = bookService.getBook(bookId);
		BookResource.LOGGER.debug(book);
		return book;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.TEXT_XML })
	public Book saveBook(final Book book) {
		return bookService.saveBook(book);
	}

	@Path("{bookId:[0-9]*}")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.TEXT_XML })
	public Book updateBook(@PathParam("bookId") final Long bookId,
			final Book book) {
		if (book == null) {
			return null;
		}
		return bookService.updateBook(bookId, book);
	}

	@Path("/{bookId:[0-9]*}")
	@DELETE
	public String deleteBook(@PathParam("bookId") final Long bookId) {
		if (bookService.deleteBook(bookId)) {
			return "Deleted book id=" + bookId;
		} else {
			return "Deleted book failed id=" + bookId;
		}
	}
}