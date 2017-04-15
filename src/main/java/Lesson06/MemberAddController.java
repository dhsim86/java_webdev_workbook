package Lesson06;

import java.util.Map;

import Lesson05.Member;
import Lesson05.MemberDao;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
    
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"member", Lesson05.Member.class,
		};
	}
	
    @Override
    public String execute(Map<String, Object> model) throws Exception {

        Member member = (Member)model.get("member");
        
        if (member.getEmail() == null) {
            return "/Lesson05/MemberAdd.jsp";
        }
        else {
            memberDao.insert(member);
            return "redirect:list.do";
        }
    }
}
