<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <form action="group" method="GET" >
            
            Project ID : 
            <input type="text" name="pid">        
            <br>
            Member 1 : 
            <input type="text" name="name1">
            <br>
            Member 2 : 
            <input type="text" name="name2">
            <br>
            Member 3 : 
            <input type="text" name="name3">
            <br>
            Member 4 : 
            <input type="text" name="name4">
            <br>
            Member 5 : 
            <input type="text" name="name5">
            <br>
            College : 
            <input type="text" name="clg">
            
            
            <div id="radiobutton">
            <label>Domain of Interest : </label>
    <ul style="list-style-type:none">
    <li><input type="radio" name="rb" value="rb1"> 1. a </li>
    <li><input type="radio" name="rb" value="rb2"> 2. b</li>
    <li><input type="radio" name="rb" value="rb3"> 3. c</li>
    <li><input type="radio" name="rb" value="rb4"> 4. d</li>
    <li><input type="radio" name="rb" value="rb5"> 5. e</li>
    <li><input type="radio" name="rb" value="rb6"> 6. f</li>
    <li><input type="radio" name="rb" value="rb7"> 7. g</li>
    </ul>
    </div>
         <br>
            Title :
            <input type="text" name="title">
            
            <br>
            <button type="submit">Add</button>
            
        </form>
    </body>
</html>
