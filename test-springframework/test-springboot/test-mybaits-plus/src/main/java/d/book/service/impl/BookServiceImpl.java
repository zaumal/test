package d.book.service.impl;

import d.book.entity.Book;
import d.book.mapper.BookMapper;
import d.book.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<Book> getList() {
		return bookMapper.selectList(null);
	}

	@Override
	public void update(Book book) {
		bookMapper.update(book);
	}
}
