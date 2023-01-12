package com.cognixia.jump.exception;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	// fields to gather info about the Method that threw this Exception
	Class invokingClass;
	Method[] invokingClassMethods;
	Parameter[] invokingMethodParameters;
	String throwingMethod;
	Object[] args;

	// Not likely to use super(), but it is a default
	// however, constructor gathers info on the Class/Method that invoked the
	// exception
	public ResourceNotFoundException(Object... args) throws ClassNotFoundException {
		super("Resource not found.");
		this.args = args;
		this.invokingClass = extractInvokingClass();
		this.throwingMethod = extractInvokingMethodName();
	}

	// Modified message to be displayed in our ErrorDetails
	// Here we can display our gather info about the:
	// 1. Entity
	// 2. Method
	// 3. Parameters and assigned arguments
	// that triggered this Exception, and display to JSON through ErrorDetails
	public String getResourceMessage() {

		return String.format("Resource: %s not found, using %s(%s)", extractEntityName(), throwingMethod,
				setParamsAndArgsMsg(extractParameterNames(), this.args));

	}

	/*** THE BELOW ARE HELPER METHODS FOR DISPLAYING THE MESSAGE ***/

	// Use Reflection to get the class that contains the method that threw the
	// Exception
	private Class extractInvokingClass() throws ClassNotFoundException {
		return Class.forName(this.getStackTrace()[0].getClassName());
	}

	// We need the method name to get the parameter set
	private String extractInvokingMethodName() {
		return this.getStackTrace()[0].getMethodName();
	}

	// Get parameter names from the invoking method that started the Exception
	private String[] extractParameterNames() {
		invokingClassMethods = invokingClass.getMethods();
		for (Method m : invokingClassMethods) {

			String trimmed = m.getName().replace("^\\(.*\\)$", "");

			if (trimmed.equalsIgnoreCase(throwingMethod)) {
				invokingMethodParameters = m.getParameters();
			}

		}
		String[] params = new String[invokingMethodParameters.length];
		if (invokingMethodParameters != null) {
			for (int p = 0; p < invokingMethodParameters.length; p++) {
				String pkg = invokingMethodParameters[p].getClass().getPackageName();
				params[p] = invokingMethodParameters[p].getName().replace(pkg, "");
				System.out.println(pkg);

			}
			return params;
		}

		return null;
	}

	// Format the string to assign parameter names to their arguments
	private String setParamsAndArgsMsg(String[] params, Object[] args) {
		String[] assigned = new String[params.length];
		for (int i = 0; i < params.length; i++) {
			assigned[i] = params[i] + "= " + args[i];
		}
		return Arrays.toString(assigned);
	}

	// Extract the Entity name from the class that threw the Exception
	// Logic determines which class called, and then naming convention allows
	// us to remove Service, Controller, etc. to get the entity name.
	private String extractEntityName() {
		if (invokingClass.getSimpleName().contains("Service")) {
			return invokingClass.getSimpleName().replace("Service", "");
		} else if (invokingClass.getSimpleName().contains("Controller")) {
			return invokingClass.getSimpleName().replace("Controller", "");
		} else {
			return null;
		}
	}
}
