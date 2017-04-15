package Lesson06;

import java.util.Map;

import Lesson05.MemberDao;

public class MemberListController implements Controller {
    
	MemberDao memberDao;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        model.put("memberList",  memberDao.selectList());
        
        return "/Lesson05/MemberList.jsp";
    }
}
