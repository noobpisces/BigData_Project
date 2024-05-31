<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Infomation</title>
<style>
    .fixed-size-image {
        width: 500px; 
        height: 666.6666666666667px; 
    }
    .label {
        color: blue; 
    }
</style>
</head>

<body>
<label for="person-id" class="label">ID:</label>
<span id="person-id">${person.getId()}</span><br>

<label for="person-name" class="label">Name:</label>
<span id="person-name">${person.getName()}</span><br>

<label for="date" class="label">Date of birth:</label>
<span id="date">${date}</span><br>
	<img alt="" src="${person.getImage()}" class="fixed-size-image" /><br>

</body>
</html>
