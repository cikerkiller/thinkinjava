package com.hf.lesson20.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解处理器,生成表
 * @author ciker
 * @desc   
 *
 */
public class TableCreator {
	
	public static void main(String[] args) {
		createTable(Member.class,Member.class);
	}
	
	public static void createTable(Class<?>...cls) {
		for(Class<?> cl : cls) {
			DBTable dbTable = cl.getAnnotation(DBTable.class);
			if(dbTable == null) {
				System.out.println(" No DBTable annotations in class "+cl.getName());
				return;
			}
			// 表名
			String tableName = dbTable.name();
			if(tableName.length() < 1) {
				// 当未给定表名时，使用类名大写作为表名
				tableName = cl.getSimpleName().toUpperCase();
			}
			
			List<String> columnDefs = new ArrayList<>();
			// 处理列
			for(Field field : cl.getDeclaredFields()) {
				// 列名
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if(anns.length < 1) {
					// 字段上没有注解，进行下一次循环
					continue;
				}
				
				if(anns[0] instanceof SQLInteger) {
					SQLInteger sInt = (SQLInteger)anns[0];
					if(sInt.name().length() < 1) {
						// 未给定列名的情况下，设置列名为字段名大写
						columnName = field.getName().toUpperCase();
					}else {
						columnName = sInt.name();
					}
					
					columnDefs.add(columnName + " INT"+getConstraints(sInt.constraints()));
				}
				
				if(anns[0] instanceof SQLString) {
					SQLString sString = (SQLString)anns[0];
					if(sString.name().length() < 1) {
						// 未给定列名的情况下，设置列名为字段名大写
						columnName = field.getName().toUpperCase();
					}else {
						columnName = sString.name();
					}
					
					columnDefs.add(columnName + " VARCHAR("+sString.value()+")"+getConstraints(sString.constraints()));
				}
			}
			
			StringBuilder createCommand = new StringBuilder("CREATE TABLE "+tableName+"(");
			
			for(String columnDef : columnDefs) {
				createCommand.append("\n   "+columnDef+",");
			}
			String createTable = createCommand.substring(0, createCommand.length()-1)+"\n);";
			System.out.println("Table Creation SQL for "+cl.getName()+" is :\n"+createTable+"\n\n");
		}
	}
	
	
	
	// 获取约束
	private static String getConstraints(Constraints con) {
		String constraints = "";
		if(!con.allowNull()) {
			constraints += " NOT NULL";
		}
		
		if(con.primaryKey()) {
			constraints += " PRIMARY KEY";
		}
		
		if(con.unique()) {
			constraints += " UNIQUE";
		}
		
		return constraints;
				
	}
}
