package Lesson06;

import java.util.Map;

import Lesson05.Member;
import Lesson05.MemberDao;

public class MemberUpdateController implements Controller {
    
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        MemberDao memberDao = (MemberDao)model.get("memberDao");
        
        if (model.get("member") == null) {
            
            int memberNo = Integer.parseInt((String)model.get("no"));
            model.put("member", memberDao.selectOne(memberNo));
            
            return "/Lesson05/MemberUpdate.jsp";
        }
        else {
            
            Member member = (Member)model.get("member");
            int result = memberDao.update(member);
            
            return "redirect:list.do";
        }
    }
}
