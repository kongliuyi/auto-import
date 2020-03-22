package net.riking.auto.commmon.inject;


import lombok.Getter;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public   class InjectedElement  {
	    @Getter
	    private  final AnnotationAttributes ann;

		protected final Member member;

		protected final boolean isField;

		public InjectedElement(Member member ,AnnotationAttributes ann) {
			this.ann = ann;
			this.member = member;
			this.isField = (member instanceof Field);
		}

		public final Member getMember() {
			return this.member;
		}

		protected final Class<?> getResourceType() {
			if (this.isField) {
				return ((Field) this.member).getType();
			}
			else {
				return ((Method) this.member).getParameterTypes()[0];
			}
		}


		public void inject(Object target, @Nullable Object value)throws Throwable {
			//是字段
			if (this.isField) {
				Field field = (Field) this.member;
				//使给定字段可访问，并显式地将其设置为可访问
				ReflectionUtils.makeAccessible(field);//field.setAccessible(true);
				field.set(target,value);
			}
			else {
				try {
					Method method = (Method) this.member;
					ReflectionUtils.makeAccessible(method);
					method.invoke(target, value);
				}
				catch (InvocationTargetException ex) {
					throw ex.getTargetException();
				}
			}
		}





		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof InjectedElement)) {
				return false;
			}
			InjectedElement otherElement = (InjectedElement) other;
			return this.member.equals(otherElement.member);
		}

		@Override
		public int hashCode() {
			return this.member.getClass().hashCode() * 29 + this.member.getName().hashCode();
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + " for " + this.member;
		}
	}