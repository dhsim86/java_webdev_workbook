package Lesson06;

import java.util.Map;
import javax.servlet.http.HttpSession;

import Lesson05.Member;
import Lesson05.MemberDao;

public class MemberLoginController implements Controller, DataBinding {
	
	MemberDao memberDao;
	
	public MemberLoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"httpSession", HttpSession.class,
			"member", Lesson05.Member.class,
		};
	}

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        HttpSession session = (HttpSession)model.get("httpSession");
        Member member = (Member)model.get("member");
        
        if (member.getEmail() == null){
            
            return "/Lesson05/LogInForm.jsp";
        }
        else {
            
            Member memberExist = memberDao.exist(
                member.getEmail(), member.getPassword());
            
            if (memberExist != null) {
                session.setAttribute("member", memberExist);
                
                return "redirect:../member/list.do";
            }
            else {
                return "/Lesson05/LogInFail.jsp";
            }
        }
    }
}
