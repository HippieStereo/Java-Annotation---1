package com.hs.annotations_custom_01;

import java.lang.reflect.Method;
import java.util.Objects;

public class ObjectToJsonConverter {
	
	private void checkIfSerializable(Object object) {
		
		if(Objects.isNull(object)) {
			
			throw new JsonSerializationException("The object to serialize is null");
			
		}
		
		Class<?> clazz = object.getClass();
		
		if(!clazz.isAnnotationPresent(JsonSerializable.class)) {
			
			throw new JsonSerializationException("The class " 
					+ clazz.getSimpleName() + " is not annotated with JsonSerializable");
			
		}
		
	}
	
	private void initializeObject(Object object) throws Exception {
		
		Class<?> clazz = object.getClass();
		
		for(Method method : clazz.getDeclaredMethods()) {
			
			if(method.isAnnotationPresent(Init.class)) {
				
				method.setAccessible(true);
				method.invoke(object);
				
			}
			
		}
		
	}

}
