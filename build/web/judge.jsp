<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <form action="judge" method="GET" >
            
            Judge ID : 
            <input type="text" name="jid">        
            <br>
            Name : 
            <input type="text" name="jname">
            <br>
            Email : 
            <input type="text" name="jemail">
            <br>
            Contact : 
            <input type="text" name="jcontact">
            <br>
            
            <button type="submit">Add</button>
            
        </form>
    </body>
</html>
