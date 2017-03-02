<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once('dbConnect.php');
    $id = $_POST["id"];
	$firstname = $_POST["firstname"];	
	$lastname = $_POST["lastname"];
	$age = $_POST["age"];
	
    $query = "UPDATE student SET ";
    $query .= "firstname = '$firstname', ";
    $query .= "lastname = '$lastname', ";
    $query .= "age = '$age' ";
    $query .= "WHERE id = $id ";
	
	mysqli_query($con, $query) or die (mysqli_error($con));
    
	mysqli_close($con);
    
}

?>
