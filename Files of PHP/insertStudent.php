<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once('dbConnect.php');
	$firstname = $_POST["firstname"];	
	$lastname = $_POST["lastname"];
	$age = $_POST["age"];
	
	$query = " Insert into student(firstname,lastname,age) values ('$firstname','$lastname','$age');";
	
	mysqli_query($con, $query) or die (mysqli_error($con));
    
	mysqli_close($con);
    
}

?>
