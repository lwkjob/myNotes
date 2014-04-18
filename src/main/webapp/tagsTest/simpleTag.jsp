<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%
        //创建一个List对象
        List<String> a = new ArrayList<String>();
        a.add("hello");
        a.add("world");
        a.add("java");
        //将List对象放入page范围内
        pageContext.setAttribute("a" , a);
%>
<!-- 导入标签库，指定mytag前缀的标签，	由http://www.lwkjob.org/mytag的标签库处理 -->
<%@ taglib uri="http://www.lwkjob.org/simpleTag" prefix="simpleTag"%>
<%@ taglib uri="http://www.lwkjob.org/queryTag" prefix="queryTag"%>
<%@ taglib uri="http://www.lwkjob.org/queryTag" prefix="queryTag"%>
<%@ taglib uri="http://www.lwkjob.org/iterator" prefix="iterator"%>
<html>
<head>
<title>自定义标签示范</title>
</head>
<body bgcolor="#ffffc0">
	<h2>下面显示的是自定义标签中的内容</h2>
	<!-- 使用标签 ，其中mytag是标签前缀，根据taglib的编译指令，
	mytag前缀将由http://www.lwkjob.org/mytag的标签库处理 -->
	<!-- 简单标签 -->
	<simpleTag:helloWorld />
	<!-- 带属性的标签 -->
	<queryTag:query user="root" url="jdbc:mysql://localhost:3306/cms" pass="111111" driver="com.mysql.jdbc.Driver" sql="select * from t_article"/>
	 
        <table border="1"  width="300">
        <!-- 使用迭代器标签，对a集合进行迭代 -->
        <iterator:iterator collection="a" item="item">
            <tr>
                <td>${pageScope.item}</td>
            <tr>
        </iterator:iterator>
        </table>
</body>
</html>