package Lesson06;

import java.util.Map;

import Lesson05.MemberDao;

public class MemberDeleteController implements Controller {

	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        int memberNo = Integer.parseInt((String)model.get("no"));
        int result = memberDao.delete(memberNo);
        
        return "redirect:list.do";
    }
}