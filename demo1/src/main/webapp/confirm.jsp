<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
</head>
<body>
<h1>Confirmation of registration</h1>
<form action="result" method="post">
    <p>Username: ${param.username} </p>
    <input type="text" name="username" value = ${param.username} hidden=true>
    <br>
    <p>Password: ${param.password} </p>
    <input type="text" name="password" value=${param.password} hidden=true>
    <br>
    <p>Gender: ${param.gender} </p>
    <input type="text" name="gender" value =${param.gender} hidden=true>
    <br>
    <% String[] hobbies = request.getParameterValues("hobbies");
        String concat_hobbies="";
        for(String hobby :hobbies){
            concat_hobbies +=  hobby + ", ";
        }
    %>
    <p>Hobbies: <%=concat_hobbies%> </p>
    <label>
        <input type="hidden" name="hobbies" value="<%=concat_hobbies%>" >
    </label>
    <br>
    <p>City: ${param.city} </p>
    <label>
        <input type="hidden" name="city" value=${param.city}  >
    </label>
    <input type =submit value=submit >
</form>
</body>
</html>