package ExceptionHandling;

import lombok.Data;

@Data
public class EmployeeNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldValue;
	
	public EmployeeNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(String message, Throwable cause  ) {
		super(message, cause);
	}

}
