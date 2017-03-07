<?php 
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
     
 $username = $_POST['email'];
 $password = $_POST['password'];
     
     $hashFormat = "$2y$10$";
    $salt = "iusesomecrazystrings22";
    $hashF_and_salat = $hashFormat . $salt;
    $password1 = crypt($password, $hashF_and_salat);
 
 $sql = "SELECT * FROM users WHERE email='$username' AND password='$password1'";
 
 require_once('dbConnect.php');
 
 $result = mysqli_query($con,$sql);
 
 $check = mysqli_fetch_array($result);
 
 if(isset($check)){
 echo "success";
 }else{
 echo "failure";
 }
 mysqli_close($con);
 }

?>