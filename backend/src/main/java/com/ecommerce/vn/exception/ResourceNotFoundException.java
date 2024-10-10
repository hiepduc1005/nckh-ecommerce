package com.ecommerce.vn.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String resourceName;
	
	private String field;
	
	private String fieldString;
	
	private UUID fieldId;
	
    private String fieldName;
    private Object fieldValue;

	public ResourceNotFoundException(String resourceName, String field, String fieldString) {
		super(String.format("Cant not found %s with %s: %s",resourceName,field,fieldString));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldString = fieldString;
	}
	

	public ResourceNotFoundException(String resourceName, String field, UUID fieldId) {
		super(String.format("Cant not found %s with %s: %s",resourceName,field,fieldId.toString()));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldId = fieldId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldString() {
		return fieldString;
	}

	public void setFieldString(String fieldString) {
		this.fieldString = fieldString;
	}

	public UUID getFieldId() {
		return fieldId;
	}

	public void setFieldId(UUID fieldId) {
		this.fieldId = fieldId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
	
	
	

}
