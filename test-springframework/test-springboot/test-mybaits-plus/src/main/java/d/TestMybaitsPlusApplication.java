package d;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import d.book.entity.Book;
import d.book.service.IBookService;

@SpringBootApplication
@MapperScan("d.*.mapper")
public class TestMybaitsPlusApplication implements CommandLineRunner{
	@Autowired
	private IBookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(TestMybaitsPlusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner run...");

		Book book = new Book();
		book.setId(1);
		book.setbId(2);
		bookService.update(book);
		
		System.out.println(bookService.getList());
	}
}
