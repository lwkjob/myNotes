

package com.gatesocket.utils;

import java.io.PrintStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public abstract class ClassUtil
{
	public static interface ReadWriteOperator
	{

		public abstract Object readObject(String s, Object obj);

		public abstract String writeObject(Object obj);
	}


	public ClassUtil()
	{
	}

	public static Field[] getDeclaredField(Class cls, Class annoCls)
	{
		Field fields[] = cls.getDeclaredFields();
		List ls = new ArrayList();
		for (int i = 0; i < fields.length; i++)
			if (fields[i].getAnnotation(annoCls) != null)
			{
				fields[i].setAccessible(true);
				ls.add(fields[i]);
			}

		return (Field[])ls.toArray(new Field[0]);
	}

	public static Field[] getDeclaredField(Class cls)
	{
		Field fields[] = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
			fields[i].setAccessible(true);

		return fields;
	}

	public static String getPackageName(String className)
	{
		int index = className.lastIndexOf(".");
		if (index == -1)
		{
			return "";
		} else
		{
			String packageName = className.substring(0, index);
			return packageName;
		}
	}

	public static String getShortName(Object objBean)
	{
		if (objBean == null)
			return "";
		else
			return objBean.getClass().getSimpleName();
	}

	public static Object instantiateClass(Class cls)
		throws InstantiationException, IllegalAccessException
	{
		return cls.newInstance();
	}

	public static Object instantiateClass(Constructor constructor, Object args[])
		throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		return constructor.newInstance(args);
	}

	public static Class forName(String className)
		throws ClassNotFoundException
	{
		return forName(className, getDefaultClassLoader());
	}

	public static Class forName(String className, ClassLoader classLoader)
		throws ClassNotFoundException
	{
		Class cls = null;
		cls = getDefaultClassLoader().loadClass(className);
		return cls;
	}

	public static Constructor getConstructorIfAvailable(Class cls, Class argTypes[])
		throws NoSuchMethodException
	{
		Constructor constructor = null;
		try
		{
			constructor = cls.getConstructor(argTypes);
		}
		catch (NoSuchMethodException e)
		{
			constructor = cls.getDeclaredConstructor(argTypes);
			constructor.setAccessible(true);
		}
		return constructor;
	}

	public static Method getMethodIfAvailable(Class cls, String methodName, Class argTypes[])
		throws NoSuchMethodException
	{
		Method method = null;
		try
		{
			method = cls.getDeclaredMethod(methodName, argTypes);
		}
		catch (NoSuchMethodException e)
		{
			if (cls.getSuperclass() != null)
				return getMethodIfAvailable(cls.getSuperclass(), methodName, argTypes);
			else
				throw e;
		}
		method.setAccessible(true);
		return method;
	}

	public static ClassLoader getDefaultClassLoader()
	{
		return Thread.currentThread().getContextClassLoader();
	}

	public static ClassLoader getSystemClassLoader()
	{
		return ClassLoader.getSystemClassLoader();
	}

	public static Object invoke(Object obj, String methodName, Object args[])
		throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Class argTypes[] = new Class[args.length];
		for (int i = 0; i < argTypes.length; i++)
			argTypes[i] = args[i].getClass();

		Method method = getMethodIfAvailable(obj.getClass(), methodName, argTypes);
		return method.invoke(obj, args);
	}

	public static Annotation getAnnotationIfAvailable(Class cls, Class annoCls)
	{
		return cls.getAnnotation(annoCls);
	}

	public static Object instance(String className)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class cls = forName(className);
		return instantiateClass(cls);
	}

	public static Object instantiateClass(Class cls, Object args[])
		throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException
	{
		Class types[] = new Class[args.length];
		for (int i = 0; i < args.length; i++)
			types[i] = args[i].getClass();

		Constructor constructor = getConstructorIfAvailable(cls, types);
		return instantiateClass(constructor, args);
	}

	public static Class[] getInterfaces(Class cls)
	{
		List retVal = new ArrayList();
		if (cls.isInterface())
		{
			retVal.add(cls);
		} else
		{
			Class inters[] = cls.getInterfaces();
			retVal.addAll(Arrays.asList(inters));
			Class scls = cls.getSuperclass();
			if (!scls.getName().equals("java.lang.Object"))
				retVal.addAll(Arrays.asList(getInterfaces(scls)));
		}
		return (Class[])retVal.toArray(new Class[0]);
	}

	public static void main(String args[])
	{
//		System.out.println(java/io/Serializable.getInterfaces().length);
	}
}