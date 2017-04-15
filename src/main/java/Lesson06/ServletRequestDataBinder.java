package Lesson06;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletRequestDataBinder {
	
	public static Object bind(
		ServletRequest request, Class<?> dataType, String dataName)
		throws Exception {
		
		if (isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		if (isSessionType(dataType) && request instanceof HttpServletRequest) {
			return ((HttpServletRequest)request).getSession();
		}
		
		Set<String> paramNameSet = request.getParameterMap().keySet();
		Object dataObject = dataType.newInstance();
		Method m = null;
		
		for (String paramName : paramNameSet) {
			
			m = findSetter(dataType, paramName);
			
			if (m != null) {
				m.invoke(dataObject, 
					createValueObject(m.getParameterTypes()[0], 
					request.getParameter(paramName)));
			}
		}
		
		return dataObject;
	}
	
	private static boolean isPrimitiveType(Class<?> type) {
		
		if (type.getName().equals("int") || type == Integer.class ||
			type.getName().equals("long") || type == Long.class ||
			type.getName().equals("float") || type == Float.class ||
			type.getName().equals("double") || type == Double.class ||
			type.getName().equals("boolean") || type == Boolean.class ||
			type == Date.class || type == String.class) {
			
			return true;
		}
		return false;
	}
	
	private static boolean isSessionType(Class<?> type) {

		if (type == HttpSession.class) {
			return true;
		}
		return false;
	}
	
	private static Object createValueObject(Class<?> type, String value) {
		
		if (type.getName().equals("int") || type == Integer.class) {
			return new Integer(value);
		}
		else if (type.getName().equals("long") || type == Long.class) {
			return new Long(value);
		}
		else if (type.getName().equals("float") || type == Float.class) {
			return new Float(value);
		}
		else if (type.getName().equals("double") || type == Double.class) {
			return new Double(value);
		}
		else if (type.getName().equals("boolean") || type == Boolean.class) {
			return new Boolean(value);
		}
		else if (type == Date.class) {
			return java.sql.Date.valueOf(value);
		}
		else {
			return value;
		}
	}
	
	private static Method findSetter(Class<?> type, String name) {
		
		Method[] methods = type.getMethods();
		
		String propName = null;
		
		for (Method m : methods) {
			
			if (!m.getName().startsWith("set")) continue;
			
			propName = m.getName().substring(3);
			if (propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}
}
