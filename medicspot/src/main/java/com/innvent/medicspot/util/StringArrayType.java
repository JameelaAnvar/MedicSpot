/*package com.medicspot.medicspot_java_apis.utils;

import java.util.Properties;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

public class StringArrayType extends AbstractSingleColumnStandardBasicType<String[]>
		implements DynamicParameterizedType {

	public StringArrayType() {
		super(ArraySqlTypeDescriptor.INSTANCE, StringArrayTypeDescriptor.INSTANCE);
	}

	public String getName() {
		return "string-array";
	}

	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}

	@Override
	public void setParameterValues(Properties parameters) {
		((StringArrayTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
	}
}
*/