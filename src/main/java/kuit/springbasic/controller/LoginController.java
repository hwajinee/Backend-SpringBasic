package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.jfr.Frequency;
import kuit.springbasic.db.MemoryUserRepository;
import kuit.springbasic.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final MemoryUserRepository memoryUserRepository;

    /**
     * TODO: showLoginForm
     * /user/login 으로 포워딩
     */
    @RequestMapping("/loginForm")
    public String showLoginForm(){
        log.info("LoginController.showLoginForm");

        return "/user/login";
    }

    /**
     * TODO: showLoginFailed
     */
    @RequestMapping("/loginFailed")
    public String showLoginFailed(){
        log.info("LoginController.showLoginFailed");

        return "/user/loginFailed";
    }

    /**
     * TODO: login
     * loginV1 : @RequestParam("")
     * loginV2 : @RequestParam 생략(비추천)
     * loginV3 : @ModelAttribute
     */
    @RequestMapping("/login")
    public String loginV1(@RequestParam("userId")String userId, @RequestParam("password")String password,
                        HttpServletRequest request) {
        log.info("LoginController.loginV1");

        User loginUser = new User(userId, password);
        User user = memoryUserRepository.findByUserId(userId);

        if (user != null && user.getUserId().equals(loginUser.getUserId()) && user.getPassword().equals(loginUser.getPassword()) && user.getName().equals(loginUser.getName()) && user.getEmail().equals(loginUser.getEmail())) { // 회원가입한 유저인 경우,
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // 세션 정보 바인딩
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/login")
    public String loginV2(String userId, String password, HttpServletRequest request) {
        log.info("LoginController.loginV2");

        User loginUser = new User(userId, password);
        User user = memoryUserRepository.findByUserId(userId);

        if (user != null && user.getUserId().equals(loginUser.getUserId()) && user.getPassword().equals(loginUser.getPassword()) && user.getName().equals(loginUser.getName()) && user.getEmail().equals(loginUser.getEmail())) { // 회원가입한 유저인 경우,
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // 세션 정보 바인딩
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    //V1, V2에서 String 객체를 하나하나씩 매개변수로 받았다면, 이를 한번에 객체로 받을 수도 있음.
    //@ModelAttribute를 사용하면, loginUser를 직접 생성할 필요 없이, 자동으로 생성됨.
    @RequestMapping("/login")
    public String loginV3(@ModelAttribute User loginUser, HttpServletRequest request) {
        log.info("LoginController.loginV3");

        User user = memoryUserRepository.findByUserId(loginUser.getUserId());

        if (user != null && user.getUserId().equals(loginUser.getUserId()) && user.getPassword().equals(loginUser.getPassword()) && user.getName().equals(loginUser.getName()) && user.getEmail().equals(loginUser.getEmail())) { // 회원가입한 유저인 경우,
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // 세션 정보 바인딩
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }

    /**
     * TODO: logout
     */

}
