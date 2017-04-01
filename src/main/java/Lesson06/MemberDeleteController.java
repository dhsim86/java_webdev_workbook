package Lesson06;

import java.util.Map;

import Lesson05.MemberDao;

public class MemberDeleteController implements Controller {

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        MemberDao memberDao = (MemberDao)model.get("memberDao");
        
        int memberNo = Integer.parseInt((String)model.get("no"));
        int result = memberDao.delete(memberNo);
        
        return "redirect:list.do";
    }
}