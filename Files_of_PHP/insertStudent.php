<?php
$connection = mysqli_connect('localhost','root','','phpinandroid');

if (mysqli_connect_errno())
{
    die ("error in connection:".mysqli_connect_error());
    return;
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    
	$firstname = $_POST["firstname"];	
	$lastname = $_POST["lastname"];
	$age = $_POST["age"];
	
	$query = " Insert into student(firstname,lastname,age) values ('$firstname','$lastname','$age');";
	
	mysqli_query($connection, $query) or die (mysqli_error($connection));
    
	mysqli_close($connection);
    
}

?>
