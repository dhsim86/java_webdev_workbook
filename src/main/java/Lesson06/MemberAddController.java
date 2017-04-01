package Lesson06;

import java.util.Map;

import Lesson05.Member;
import Lesson05.MemberDao;

public class MemberAddController implements Controller {
    
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        if (model.get("member") == null) {
            return "/Lesson05/MemberAdd.jsp";
        }
        else {
            
            MemberDao memberDao = (MemberDao)model.get("memberDao");
            
            Member member = (Member)model.get("member");
            memberDao.insert(member);
            
            return "redirect:list.do";
        }
    }
}
