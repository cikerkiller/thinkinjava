package com.hf.lesson12;

class DynamicFieldException extends Exception{}
public class DynamicFields {
	private Object[][] fields;
	public DynamicFields(int iniyialSize) {
		fields=new Object[iniyialSize][2];
		for(int i=0;i<iniyialSize;i++) {
			fields[i]= new Object[]{null,null};
		}
	}
	
	public String toString() {
		StringBuilder builder=new StringBuilder();
		for(Object[] obj:fields) {
			builder.append(obj[0]);
			builder.append(": ");
			builder.append(obj[1]);
			builder.append("\n");
		}
		return builder.toString();
	}
	
	private int hasFields(String id) {
		for(int i=0;i<fields.length;i++) {
			if(id.equals(fields[i][0])) {
				return i;
			}
		}
		return -1;
	}
	
	private int getFieldNumber(String id) throws NoSuchFieldException{
		int fieldNum = hasFields(id);
		if(fieldNum==-1) {
			throw new NoSuchFieldException();
		}
		return fieldNum;
	}  
	
	private int makeField(String id) {
		for(int i=0;i<fields.length;i++) {
			if(fields[i][0]==null) {
				fields[i][0]=id;
				return i;
			}
		}
		Object[][] tmp = new Object[fields.length+1][2];
		for(int i=0;i<fields.length;i++) {
			tmp[i]=fields[i];
		}
		for(int i=fields.length;i<tmp.length;i++) {
			tmp[i]=new Object[] {null,null};
		}
		fields=tmp;
		return makeField(id);
	}
	
	public Object getField(String id) throws NoSuchFieldException{
		return fields[getFieldNumber(id)][1];
	}
	
	public Object setField(String id,Object value) throws DynamicFieldException{
		if(value==null) {
			DynamicFieldException dynamicFieldException=new DynamicFieldException();
			dynamicFieldException.initCause(new NullPointerException());
			throw dynamicFieldException;
		}
		
		int fieldNumber=hasFields(id);
		if(fieldNumber==-1) {
			fieldNumber=makeField(id);
		}
		Object result=null;
		try {
			result=getField(id);
		} catch (NoSuchFieldException e) {
			// 
			throw new RuntimeException(e);
		}
		fields[fieldNumber][1]=value;
		return result;
	}
	
	public static void main(String[] args) {
		DynamicFields df=new DynamicFields(3);
		System.out.println(df);
		
		try {
			df.setField("d", "A value for d");
			df.setField("number", 47);
			df.setField("number2", 48);
			System.out.println(df);
			
			df.setField("d", "A new value for d");
			df.setField("number3", 11);
			System.out.println(df);
			System.out.println("df.getField(\"d\") : "+df.getField("number2"));
			Object field = df.setField("d", null);
		} catch (DynamicFieldException e) {
			e.printStackTrace(System.out);
		} catch (NoSuchFieldException e) {
			e.printStackTrace(System.out);
		}
	}
	
}
