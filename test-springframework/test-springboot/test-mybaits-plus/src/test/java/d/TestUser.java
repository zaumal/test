package d;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import d.book.entity.Book;
import d.book.mapper.BookMapper;
import d.user.entity.User;
import d.user.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMybaitsPlusApplication.class)
public class TestUser {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BookMapper bookMapper;
	
	@Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        System.out.println(("----- selectAll method test ------"));
        List<Book> books = bookMapper.selectList(null);
        books.forEach(System.out::println);
    }
}
