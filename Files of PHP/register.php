<?php
$connection = mysqli_connect('localhost','root','','phpinandroid');

if (mysqli_connect_errno())
{
    die ("error in connection:".mysqli_connect_error());
    return;
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    
	$name = $_POST["name"];	
	$email = $_POST["email"];
	$password = $_POST["password"];
	
    $hashFormat = "$2y$10$";
    $salt = "iusesomecrazystrings22";
    $hashF_and_salat = $hashFormat . $salt;
    $password1 = crypt($password, $hashF_and_salat);
    
	$query = " Insert into users(name,email,password) values ('$name','$email','$password1');";
	
	mysqli_query($connection, $query) or die (mysqli_error($connection));
    
	mysqli_close($connection);
    
}

?>
