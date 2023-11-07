package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.db.MemoryUserRepository;
import kuit.springbasic.domain.User;
import kuit.springbasic.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MemoryUserRepository memoryUserRepository;

    /**
     * TODO: showUserForm
     */
    @RequestMapping("/form")
    public String showUserForm(){
        log.info("UserController.showUserForm");

        return "user/form";
    }

    /**
     * TODO: createUser
     * createUserV1 : @RequestParam
     * createUserV2 : @ModelAttribute
     */
    @RequestMapping("/user/signup")
    public String createUserV1(@RequestParam String userId, @RequestParam String password,
                               @RequestParam String name, @RequestParam String email) {
        log.info("UserController.createUserV1");

        User user = new User(userId,password,name,email);
        memoryUserRepository.insert(user);

        return "redirect:/";
    }

    @RequestMapping("/user/signup")
    public String createUserV2(@ModelAttribute User SignupUser) {
        log.info("UserController.createUserV1");

        memoryUserRepository.insert(SignupUser);

        return "redirect:/";
    }

    /**
     * TODO: showUserList
     */
    @RequestMapping("/user/list")
    public String showUserList(HttpServletRequest request){
        log.info("UserController.showUserList");

        return "redirect:/user/list";
    }

    /**
     * TODO: showUserUpdateForm
     */
    @RequestMapping("/user/updateForm")
    public String showUserUpdateForm(HttpSession session, HttpServletRequest request){
        log.info("UserController.showUserUpdateForm");

        request.setAttribute("user", session.getAttribute("user"));

        return "user/updateForm";
    }


    /**
     * TODO: updateUser
     * updateUserV1 : @RequestParam
     * updateUserV2 : @ModelAttribute
     */
    @RequestMapping("/user/update")
    public String updateUserV1(HttpSession session, @RequestParam String password,
                               @RequestParam String name, @RequestParam String email){
        log.info("UserController.updateUserV1");

        User user = new User((String)session.getAttribute("userId"), password, name, email);
        memoryUserRepository.update(user);

        return "redirect:/";
    }
    @RequestMapping("/user/update")
    public String updateUserV2(@ModelAttribute User updateUser){
        log.info("UserController.updateUserV1");

        memoryUserRepository.update(updateUser);
        return "redirect:/";
    }

}
