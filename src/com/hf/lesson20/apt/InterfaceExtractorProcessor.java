package com.hf.lesson20.apt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

@SuppressWarnings("deprecation")
public class InterfaceExtractorProcessor implements AnnotationProcessor{
	
	private final AnnotationProcessorEnvironment env;
	
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<>();
	
	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}
	
	@Override
	public void process() {
		for(TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
			
			ExtractInterface annot = typeDecl.getAnnotation(ExtractInterface.class);
			// 类上没有ExtractInterface注解就退出
			if(annot == null) {
				break;
			}
			
			for(MethodDeclaration m : typeDecl.getMethods()) {
				// 将非静态的公共方法加入集合
				if(m.getModifiers().contains(Modifier.PUBLIC) && !(m.getModifiers().contains(Modifier.STATIC))) {
					interfaceMethods.add(m);
				}
			}
			
			if(interfaceMethods.size() > 0) {
				try {
					// 根据给定接口名生成源文件
					PrintWriter writer = env.getFiler().createSourceFile(annot.value());
					writer.println("package "+ typeDecl.getPackage().getQualifiedName()+";");
					writer.println("public interface "+annot.value()+" {");
					// 处理方法
					for(MethodDeclaration m : interfaceMethods) {
						writer.print("    public ");
						writer.print(m.getReturnType()+" ");
						writer.print(m.getSimpleName()+"(");
						int i=0;
						// 处理参数
						for(ParameterDeclaration param : m.getParameters()) {
							writer.print(param.getType()+" "+param.getSimpleName());
							if(++i < m.getParameters().size()) {
								writer.print(", ");
							}
						}
						writer.print(");");
					}
					writer.println("}");
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
}
