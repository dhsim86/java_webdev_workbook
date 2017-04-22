package Lesson06;

import java.util.Map;

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {
	
	ProjectDao projectDao;
	
	public ProjectDeleteController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
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
		
		int projectNo = (Integer)model.get("no");
		int result = projectDao.delete(projectNo);
		
		return "redirect:list.do";
	}
}
