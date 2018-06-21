package com.hf.lesson20.apt;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

@SuppressWarnings("deprecation")
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory{

	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
			AnnotationProcessorEnvironment env) {
		return new InterfaceExtractorProcessor(env);
	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Collections.singleton("com.hf.lesson20.apt.ExtractInterface");
	}

	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptySet();
	}

}
