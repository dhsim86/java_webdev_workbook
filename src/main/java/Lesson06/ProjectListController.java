package Lesson06;

import java.util.Map;

@Component("/project/list.do")
public class ProjectListController implements Controller {

	ProjectDao projectDao;
	
	public ProjectListController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
    public String execute(Map<String, Object> model) throws Exception {
		
		model.put("projectList", projectDao.selectList());
		return "/Lesson06/ProjectList.jsp";
	}
}
