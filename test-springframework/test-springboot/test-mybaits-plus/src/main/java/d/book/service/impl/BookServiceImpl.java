package d.book.service.impl;

import d.book.entity.Book;
import d.book.mapper.BookMapper;
import d.book.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  鏈嶅姟瀹炵幇绫�
 * </p>
 *
 * @author zwq
 * @since 2020-11-13
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
