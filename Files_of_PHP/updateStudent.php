<?php
$connection = mysqli_connect('localhost','root','','phpinandroid');

if (mysqli_connect_errno())
{
    die ("error in connection:".mysqli_connect_error());
    return;
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    
    $id = $_POST["id"];
	$firstname = $_POST["firstname"];	
	$lastname = $_POST["lastname"];
	$age = $_POST["age"];
	
    $query = "UPDATE student SET ";
    $query .= "firstname = '$firstname', ";
    $query .= "lastname = '$lastname', ";
    $query .= "age = '$age' ";
    $query .= "WHERE id = $id ";
	
	mysqli_query($connection, $query) or die (mysqli_error($connection));
    
	mysqli_close($connection);
    
}

?>
