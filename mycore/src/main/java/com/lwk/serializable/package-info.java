
/**
 * @author lwkjob
 * java序列化和反序列化
 * 没有实现Serializable接口的自定义序列化
 * 
 * 通过使用定制的序列化方法，可以在不使用transient的情况下，
 * 对一个带有不可序列化属性的类进行序列化。
 * 当你要在一个需要序列化的类中使用不可序列化的类型，
 * 并且这些类型不能被修改时，这是一个有用的技术
 * 通过实现Externalizable 接口，实现writeExternal（）和readExternal（）方法，然后再自定义序列话对象。
 */
package com.lwk.serializable;
