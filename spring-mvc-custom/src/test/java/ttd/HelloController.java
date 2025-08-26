package ttd;

import com.ttd.annotation.MyRequestMapping;
import com.ttd.domain.User;

public class HelloController {

    @MyRequestMapping(path = "/hello", method = "GET")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView("hello.html");
        mv.addObject("user", new User("Dung", "dung@example.com"));
        return mv;
    }
}
