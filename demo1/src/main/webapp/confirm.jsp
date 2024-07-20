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
    <label>
        <input type="text" name="username" value = ${param.username} hidden=true>
    </label>
    <br>
    <p>Password: ${param.password} </p>
    <label>
        <input type="text" name="password" value=${param.password} hidden=true>
    </label>
    <br>
    <p>Gender: ${param.gender} </p>
    <label>
        <input type="text" name="gender" value =${param.gender} hidden=true>
    </label>
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