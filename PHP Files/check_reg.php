<?php 
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
     
 $username = $_POST['email'];
 
 $sql = "SELECT * FROM users WHERE email='$username'";
 
 require_once('dbConnect.php');
 
 $result = mysqli_query($con,$sql);
 
 $check = mysqli_fetch_array($result);
 
 if(isset($check)){
 echo "failure";
 }else{
 echo "success";
 }
 mysqli_close($con);
 }