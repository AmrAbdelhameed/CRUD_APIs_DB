<?php 
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $username = $_POST['email'];
 $password = $_POST['password'];
     
     $hashFormat = "$2y$10$";
    $salt = "iusesomecrazystrings22";
    $hashF_and_salat = $hashFormat . $salt;
    $password1 = crypt($password, $hashF_and_salat);
 
 //Creating sql query
 $sql = "SELECT * FROM users WHERE email='$username' AND password='$password1'";
 
 //importing dbConnect.php script 
 require_once('dbConnect.php');
 
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }