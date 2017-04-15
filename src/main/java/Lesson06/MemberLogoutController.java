package Lesson06;

import java.util.Map;

import javax.servlet.http.HttpSession;

@Component("/auth/logout.do")
public class MemberLogoutController implements Controller, DataBinding {

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"httpSession", HttpSession.class,
		};
	}
	
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        HttpSession session = (HttpSession)model.get("httpSession");
        session.invalidate();
        
        return "redirect:login.do";
    }
}
