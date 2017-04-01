package Lesson06;

import java.util.Map;
import javax.servlet.http.HttpSession;

import Lesson05.Member;
import Lesson05.MemberDao;

public class MemberLoginController implements Controller {

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        HttpSession session = (HttpSession)model.get("session");
        
        if (model.get("email") == null){
            
            return "/Lesson05/LogInForm.jsp";
        }
        else {
            
            MemberDao memberDao = (MemberDao)model.get("memberDao");
            
            Member member = memberDao.exist(
                (String)model.get("email"), (String)model.get("password"));
            
            if (member != null) {
                session.setAttribute("member", member);
                
                return "redirect:../member/list.do";
            }
            else {
                return "/Lesson05/LogInFail.jsp";
            }
        }
    }
}
