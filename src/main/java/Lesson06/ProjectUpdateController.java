package Lesson06;

import java.util.Map;

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
	
	ProjectDao projectDao;
	
	public ProjectUpdateController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"project", Lesson06.Project.class,
			"no", Integer.class,
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Project project = (Project)model.get("project");
		
		if (project.getTitle() == null) {
			
			int projectNo = (Integer)model.get("no");
			model.put("project", projectDao.selectOne(projectNo));
			
			return "/Lesson06/ProjectUpdate.jsp";
		}
		else {
			
			int result = projectDao.update(project);
			
			return "redirect:list.do";
		}
	}
}
