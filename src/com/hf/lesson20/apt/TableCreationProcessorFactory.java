package com.hf.lesson20.apt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.hf.lesson20.database.Constraints;
import com.hf.lesson20.database.DBTable;
import com.hf.lesson20.database.SQLInteger;
import com.hf.lesson20.database.SQLString;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.DeclarationVisitors;
import com.sun.mirror.util.SimpleDeclarationVisitor;

/**
 * 访问者设计模式
 * @author ciker
 * @desc   
 *
 */
public class TableCreationProcessorFactory implements AnnotationProcessorFactory{

	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
		return null;
	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Arrays.asList("../database/DBTable","../database/Constraints","../database/SQLString","../database/SQLInteger");
	}

	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptySet();
	}
	
	private static class TableCreationProcessor implements AnnotationProcessor {
		private final AnnotationProcessorEnvironment env;
		private String sql = "";
		public TableCreationProcessor(AnnotationProcessorEnvironment env) {
			this.env = env;
		}
		@Override
		public void process() {
			for(TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
				typeDecl.accept(DeclarationVisitors.getDeclarationScanner(new TableCreationVisitor(),DeclarationVisitors.NO_OP));
				sql = sql.substring(0, sql.length()-1) + ");";
				System.out.println("creation SQL is :\n"+sql);
				sql = "";
				
			}
		}
		
		public class TableCreationVisitor extends SimpleDeclarationVisitor {

			@Override
			public void visitClassDeclaration(ClassDeclaration d) {
				DBTable dbTable = d.getAnnotation(DBTable.class);
				if(dbTable != null) {
					sql += "CREATE TABLE ";
					sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
					sql += " (";
				}
			}

			@Override
			public void visitFieldDeclaration(FieldDeclaration d) {
				String columnName = "";
				if(d.getAnnotation(SQLInteger.class) != null) {
					SQLInteger sInt = d.getAnnotation(SQLInteger.class);
					if(sInt.name().length() < 1) {
						// 未给定列名的情况下，设置列名为字段名大写
						columnName = d.getSimpleName().toUpperCase();
					}else {
						columnName = sInt.name();
					}
					
					sql += "\n    "+columnName + " INT"+getConstraints(sInt.constraints()) +",";
				}
				
				if(d.getAnnotation(SQLString.class) != null) {
					SQLString sString = d.getAnnotation(SQLString.class);
					if(sString.name().length() < 1) {
						// 未给定列名的情况下，设置列名为字段名大写
						columnName = d.getSimpleName().toUpperCase();
					}else {
						columnName = sString.name();
					}
					
					sql += "\n    "+columnName + " VARCHAR("+sString.value()+") "+getConstraints(sString.constraints()) +",";
				}
				
				
			}
			// 获取约束
			private String getConstraints(Constraints con) {
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
	}
	
}
