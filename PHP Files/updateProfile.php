<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once('dbConnect.php');
    
    $id = $_POST["id"];
	$name = $_POST["name"];	
	
    $query = "UPDATE users SET ";
    $query .= "name = '$name' ";
    $query .= "WHERE id = $id ";
	
	mysqli_query($con, $query) or die (mysqli_error($con));
    
	mysqli_close($con);
    
}

?>
