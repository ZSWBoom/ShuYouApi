<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Arrays" %>
<html>
<head>
    <title>Tomcat6.0 JNDI!</title>
</head>
<body>
Tomcat11111111 <br>
<%
    try {
        //初始化查找命名空间
        Context ctx = new InitialContext();
        //参数java:/comp/env为固定路径
        Context envContext = (Context)ctx.lookup("java:/comp/env");
        //参数jdbc/mysqlds为数据源和JNDI绑定的名字
        DataSource ds = (DataSource)envContext.lookup("jdbc/KotlinMall");
        Connection conn = ds.getConnection();
%> first：<%System.out.println(conn);
        Statement st=conn.createStatement();
        String sql="select * from user_info";
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()) {%> first：<%=rs.getString(1)%> second：<%=rs.getString(2)%>
<br>
<%
        }
        conn.close();

    } catch (NamingException | SQLException e) {
        e.printStackTrace();
%> error<%=e.getMessage()%><%

    }
%>

</body>
</html>