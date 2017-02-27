<?php
$connection = mysqli_connect('localhost','root','','phpinandroid');

if (mysqli_connect_errno())
{
    die ("error in connection:".mysqli_connect_error());
    return;
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    
    $id = $_POST["id"];
	
    $query = "DELETE FROM student ";
    $query .= "WHERE id = $id ";
	
	mysqli_query($connection, $query) or die (mysqli_error($connection));
    
	mysqli_close($connection);
    
}

?>
