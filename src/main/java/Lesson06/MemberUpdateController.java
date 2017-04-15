package Lesson06;

import java.util.Map;

import Lesson05.Member;
import Lesson05.MemberDao;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	
	MemberDao memberDao;
    
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"member", Lesson05.Member.class,
			"no", Integer.class
		};
	}
	
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        Member member = (Member)model.get("member");
    	
        if (member.getEmail() == null) {
            
            int memberNo = (Integer)model.get("no");
            model.put("member", memberDao.selectOne(memberNo));
            
            return "/Lesson05/MemberUpdate.jsp";
        }
        else {
            
            int result = memberDao.update(member);
            
            return "redirect:list.do";
        }
    }
}
