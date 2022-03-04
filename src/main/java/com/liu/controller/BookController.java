package com.liu.controller;

import com.liu.pojo.Books;
import com.liu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> books = bookService.queryAllBook();
        model.addAttribute("list", books);
        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddBook() {
        return "addBook";
    }

    //添加书籍
    @RequestMapping("/addBook")
    public String addBook(Books books) {
        int result = bookService.addBook(books);
        if (result > 0) {
            System.out.println("添加书籍成功");
        }
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdateBook")
    public String toUpdate(int bookId, Model model) {
        Books books = bookService.queryBookById(bookId);
        model.addAttribute("book", books);
        return "updateBook";
    }

    /*
        没有提交事务操作，更新会失败
     */
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        int result = bookService.updateBook(books);
        if (result > 0) {
            System.out.println("修改书籍成功");
        }
        return "redirect:/book/allBook";
    }

    /*
        删除书籍，回顾RestFul风格
     */
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId) {
        int result = bookService.deleteBookById(bookId);
        if (result > 0) {
            System.out.println("删除书籍成功");
        }
        return "redirect:/book/allBook";
    }

}

