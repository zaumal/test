package d.book.service;

import d.book.entity.Book;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

public interface IBookService extends IService<Book> {
	List<Book> getList();
	void update(Book book);
}
