package Lesson06;

import java.util.Map;

import Lesson05.MemberDao;

public class MemberDeleteController implements Controller, DataBinding {

	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class,
		};
	}
	
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        
        int memberNo = (Integer)model.get("no");
        int result = memberDao.delete(memberNo);
        
        return "redirect:list.do";
    }
}