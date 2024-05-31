<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo CRUD with Servlet JSP and CouchDB</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-image:
		url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOlntPB3M-u_0R38QcDWOIkZflHHRmeeypGw&usqp=CAU");
	text-align: center;
	margin: 0;
	padding: 0;
	background-repeat: no-repeat;
	background-size: cover;
	height: auto;
}

h2 {
	color: #000080;
	text-shadow: 2px 2px 4px #999999;
}

table {
	border-collapse: collapse;
	width: 80%;
	margin: 20px auto;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 15px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
}

a {
	text-decoration: none;
	color: #333333;
}

a:hover {
	text-decoration: underline;
}

.error-message {
	color: red;
}

.add-record-button {
	border: none;
	background-color: transparent;
	cursor: pointer;
	padding: 0;
	margin-top: 20px; /* Khoảng cách cố định so với top của trang */
}

.add-record-button img {
	width: 50px;
	height: 50px;
}

.image-button {
	border: none;
	background-color: transparent;
	cursor: pointer;
	padding: 0;
}

.image-button img {
	width: 24px;
	height: 24px;
}
</style>
</head>
<body>
	<h2>Demo CRUD with Servlet JSP and CouchDB</h2>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Date of birth</th>
			<th>Info</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${listPerson}" var="Person">
			<tr>
				<td>${Person.getId()}</td>
				<td>${Person.getName()}</td>
				<td class="timestamp">${Person.getDob()}</td>
				<td>
					<button class="image-button"
						onclick="location.href='info?id=${Person.getId()}'">
						<img src="https://cdn-icons-png.flaticon.com/128/9195/9195785.png"
							alt="Info">
					</button>
				</td>
				<td>
					<button class="image-button"
						onclick="location.href='edit?id=${Person.getId()}'">
						<img src="https://cdn-icons-png.flaticon.com/128/1160/1160515.png"
							alt="Edit">
					</button>
				</td>
				<td>
					<button class="image-button"
						onclick="location.href='delete?id=${Person.getId()}'">
						<img src="https://cdn-icons-png.flaticon.com/128/9790/9790368.png"
							alt="Delete">
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<button class="add-record-button" onclick="location.href='create'">
		<img src="https://cdn-icons-png.flaticon.com/128/1090/1090923.png"
			alt="Add Record">
	</button>
	<script>
		function formatDate(timestamp) {
			var date = new Date(Number(timestamp));
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();

			day = (day < 10) ? '0' + day : day;
			month = (month < 10) ? '0' + month : month;

			return day + '/' + month + '/' + year;
		}
		window.onload = function() {
			var elements = document.getElementsByClassName('timestamp');
			for (var i = 0; i < elements.length; i++) {
				var timestamp = elements[i].textContent;
				elements[i].textContent = formatDate(timestamp);
			}
		};
	</script>
</body>
</html>