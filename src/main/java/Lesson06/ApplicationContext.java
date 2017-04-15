package Lesson06;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

public class ApplicationContext {

	Hashtable<String, Object> objTable = new Hashtable<>();
	
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	public ApplicationContext(String propertiesPath) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
		prepareObjects(props);
		prepareAnnotationObjects();
		injectDependency();
	}
	
	private void prepareObjects(Properties props) throws Exception {
		Context context = new InitialContext();
		
		String key = null;
		String value = null;
		
		for (Object item : props.keySet()) {
			
			key = (String)item;
			value = props.getProperty(key);
			
			if (key.startsWith("jndi.")) {
				objTable.put(key, context.lookup(value));
			}
			else if (key.startsWith(">")) {
				objTable.put(key, value);
			}
			else {
				try {
					objTable.put(key, Class.forName(value).newInstance());
				}
				catch (ClassNotFoundException e) {
					objTable.put(key, value);
				}
			}
		}
	}
	
	private void prepareAnnotationObjects() throws Exception {
		
		Reflections reflector = new Reflections();
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		
		for (Class<?> clazz : list) {
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key,  clazz.newInstance());
		}
	}
	
	private void injectDependency() throws Exception {
		
		for (String key : objTable.keySet()) {
			if (!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));
			}
		}
	}
	
	private void callSetter(Object obj) throws Exception {
		
		Object dependency = null;
		
		for (Method m : obj.getClass().getMethods()) {
			
			if (m.getName().startsWith("set")) {
				
				dependency = findPropertyByName(obj.getClass().getName(), m.getName().substring(3));
				
				if (dependency != null) {
					System.out.println(obj.getClass().getName() + "." + m.getName() + "<-" + dependency);
					m.invoke(obj, dependency);
					continue;
				}
				
				dependency = findObjectByType(m.getParameterTypes()[0]);
				
				if (dependency != null) {
					m.invoke(obj, dependency);
				}
			}
		}
	}
	
	private Object findPropertyByName(String objClassName, String propertyName) {
		
		for (String key : objTable.keySet()) {
			
			String[] tokens = key.substring(1).split(Pattern.quote("."));
			
			if (tokens.length > 1) {
				
				if (objClassName.toLowerCase().contains(tokens[0].toLowerCase()) && 
					propertyName.toLowerCase().equals(tokens[1].toLowerCase())) {
					
					return objTable.get(key);
				}
			}
		}
		return null;
	}
	
	private Object findObjectByType(Class<?> type) {
		
		for (Object obj : objTable.values()) {
			
			if (type.isInstance(obj)) {
				return obj;
			}
		}
		
		return null;
	}
}
