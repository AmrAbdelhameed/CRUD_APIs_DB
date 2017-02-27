<?php 
 $connection = mysqli_connect('localhost','root','','phpinandroid');

if (mysqli_connect_errno())
{
    die ("error in connection:".mysqli_connect_error());
    return;
}
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $query_tampil_biodata = mysqli_query($connection,"SELECT * FROM student") or die(mysqli_error());
     
        $data_array = array();
     
        while ($data = mysqli_fetch_assoc($query_tampil_biodata)) {
            
            $data_array[] = $data;
            
        }
     
 echo json_encode(array('results' => $data_array));
 
 mysqli_close($connection);
 
 }

?>