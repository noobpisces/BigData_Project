<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Record - Enter Information</title>
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

.form-container {
	display: flex;
	flex-direction: column;
	width: 50%;
	margin: 0 auto;
	border: 1px solid #ddd;
	padding: 15px;
}

.form-group {
	display: flex;
	flex-direction: row;
	margin-bottom: 10px;
	align-items: center;
	justify-content: flex-start
}

.form-group label {
	flex: 0 0 150px;
	text-align: left;
}

.form-group input {
	flex: 1;
	padding: 5px;
	border: 1px solid #ddd;
}

#selectedImage {
    max-width: 500px;
    max-height: 666.6666666666667px;
    margin: 0 auto;
    display: block;
    border: 1px solid #ddd;
}

.submit-button {
	width: 100px;
	background-color: #007bff;
	color: white;
	padding: 5px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	margin-top: 10px;
}

.image-button {
	border: none;
	background-color: transparent;
	cursor: pointer;
	padding: 0;
}

.image-button img {
	width: 100px;
	height: 100px;
}

form {
	max-width: 500px;
	margin: 20px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>

	<h2>Enter Information</h2>

	<form action="create" method="post">
	<div class="form-group">
		<label for="ID">ID:</label> 
		<input type="text" name="ID" id="ID" required><br> 
		</div>
		<div class="form-group">
		<label for="inputString">Name:</label> 
		<input type="text" name="inputString" id="inputString" required><br>
</div>
<div class="form-group">
		<label for="DoB">Date of birth:</label> 
		<input type="date" name="DoB" id="DoB"><br> 
		</div>
		<div class="form-group">
		<input type="hidden" name="base64ImageInput" id="base64ImageInput"> 
		<label for="imageFile">Image (3x4):</label> 
		</div>
		<div class="form-group">
		<input type="file" name="imageFile" id="imageFile" accept="image/*" required onchange="displayImage(this)"><br> 
		</div>
		<img id="selectedImage">
		
		<button type="submit" class="image-button">
			<img src="https://cdn-icons-png.flaticon.com/256/9394/9394544.png"
				alt="Submit">
		</button>
		<h3>${errorString}</h3>
	</form>
	
	<script>

        function displayImage(input) {
            var file = input.files[0];
            if (file) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    var base64Image = e.target.result;
                    document.getElementById('selectedImage').src = base64Image;
                    document.getElementById('selectedImage').style.display = 'block';
                    document.getElementById('base64ImageInput').value = base64Image;
                };
                reader.readAsDataURL(file);
            }
        }
    </script>

</body>
</html>
