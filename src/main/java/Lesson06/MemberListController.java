package Lesson06;

import java.util.Map;

import Lesson05.MemberDao;

public class MemberListController implements Controller {
    
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        MemberDao memberDao = (MemberDao)model.get("memberDao");
        model.put("memberList",  memberDao.selectList());
        
        return "/Lesson05/MemberList.jsp";
    }
}
