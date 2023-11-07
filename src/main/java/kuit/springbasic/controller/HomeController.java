package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kuit.springbasic.db.MemoryQuestionRepository;
import kuit.springbasic.domain.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemoryQuestionRepository memoryQuestionRepository;

    //localhost:8080/
    //@RequestMapping("/")
    //public ModelAndView showHome1(HttpServletRequest requset, HttpServletResponse response) {
    public ModelAndView showHome1() { // 매개변수 필요 없으면 생략.
        log.info("HomeController.showHome1");

        List<Question> questions = memoryQuestionRepository.findAll();
        ModelAndView mav = new ModelAndView("home");

        mav.addObject("questions", questions);

        return mav;
    }

    @RequestMapping("/")
    public String showHome2(Model model){
        log.info("HomeController.showHome2");

        List<Question> questions = memoryQuestionRepository.findAll();
        model.addAttribute("questions", questions);

        return "home";
    }

    // showHome1) req,resp를 받아서, 직접 modelAndView를 생성하고 이를 반환했음.
    // showHome2*) model을 받아서 속성값을 넣고 viewName을 리턴하면, SpringMVC는 자동으로 이를 modelAndView를 반환해준다.

}
