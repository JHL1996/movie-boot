package com.wsk.movie.controller.book;

import com.wsk.movie.book.entity.BookEntity;
import com.wsk.movie.book.service.SearchBookService;
import com.wsk.movie.controller.UserInformationController;
import com.wsk.movie.pojo.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @DESCRIPTION : 图书控制中心
 * @AUTHOR : WuShukai1103
 * @TIME : 2018/2/26  23:02
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {

    private final UserInformationController userController;

    private final SearchBookService searchBookService;

    @Autowired
    public BookController(UserInformationController userInformationController, SearchBookService searchBookService) {
        this.userController = userInformationController;
        this.searchBookService = searchBookService;
    }

    // 初始化图书信息
   @RequestMapping(value = "/init")
    public String bookInit(HttpServletRequest request, Model model){
       UserInformation userInformation = (UserInformation) request.getSession().getAttribute("userInformation");
       List<BookEntity> entities = searchBookService.randBook();
       model.addAttribute("entity", entities);
       model.addAttribute("myFriends", userController.getMyFriends(userInformation.getId()));
       model.addAttribute("userInformation", userInformation);
       model.addAttribute("username", userInformation.getName());
       model.addAttribute("autograph", userInformation.getAutograph());
       model.addAttribute("action", 6);
       userController.getUserCounts(model, userInformation.getId());
        return "book/init";
    }
}
