<?php 

if($_SERVER["REQUEST_METHOD"]=="POST"){
  require_once('dbConnect.php');
     
     $username = $_POST["username"];
     
 $query_Q = mysqli_query($con,"SELECT * FROM student WHERE username='$username'") or die(mysqli_error());
     
        $data_array = array();
     
        while ($data = mysqli_fetch_assoc($query_Q)) {
            
            $data_array[] = $data;
            
        }
     
 echo json_encode(array('results' => $data_array));
 
 mysqli_close($con);
 
 }

?>