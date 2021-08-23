package br.com.dataagil.web.util;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.Scope;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	  void registerScope(String scopeName, Scope scope);

	}
