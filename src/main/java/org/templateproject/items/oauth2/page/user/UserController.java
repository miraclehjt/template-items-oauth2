package org.templateproject.items.oauth2.page.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuanqi on 2017/7/19/019.
 */

@Controller
@RequestMapping("oauth2/user")
public class UserController {

    @RequestMapping("treeDept")
    public ModelAndView deptTree() {
        return new ModelAndView("router/user/deptTree");
    }

    @RequestMapping("treeRole")
    public ModelAndView roleTree() {
        return new ModelAndView("router/user/roleTree");
    }
}
