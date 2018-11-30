<%--
  Created by IntelliJ IDEA.
  User: dennisottenbacher
  Date: 31.01.18
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,java.io.*" %>

<%
    //DELETE
    SortedSet ss = new TreeSet();
    Enumeration<String> enm=request.getParameterNames();
    while(enm.hasMoreElements())
    {
        String pname = enm.nextElement();

        // als keine Sicherheit das niemand was löscht was er nicht löschen darf
        if(!pname.contains("/")){
            Process deleteProcess = Runtime.getRuntime().exec("rm ../webapps/files/"+pname);
            BufferedReader deleteInput = new BufferedReader(new InputStreamReader(deleteProcess.getInputStream()));
            out.println(pname+" wurde gelöscht. <br/>");
        }
    }
%>

<html>
<head>
    <title></title>
</head>
<body>

<%
    String[] disk;
    String line;
    String process;
    Process p;
    BufferedReader input;
    p = Runtime.getRuntime().exec("ls ../webapps/files/");
    input = new BufferedReader(new InputStreamReader(p.getInputStream()));
%>

<form method="post">
    <input type="submit" value="Löschen">
    <%
        while ((line = input.readLine()) != null) {
            if (line.toLowerCase().endsWith(".jpg") || line.toLowerCase().endsWith(".png")) {
    %>
    <br/>
    <input type="checkbox" value="<%=line%>" name="<%=line%>"><%=line%>
    <%
            }
        }
        input.close();
    %>
</form>
</body>
</html>
