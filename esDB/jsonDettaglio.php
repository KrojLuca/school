<?php
   require("./connection.php");
  
  $validazioneParametri= false;

  if(isset($_REQUEST["idPersona"])){
    $id = $_REQUEST["idPersona"];
    $validazioneParametri=true;
  }else{
    $validazioneParametri= false;
  }
   
  if($validazioneParametri){
  $sql= "SELECT dataDonazione 
  FROM donazioni JOIN trasfusioni ON donazioni.idTrasfusioni=trasfusioni.idTrasfusione 
  WHERE donazioni.idPersona=$id";
 
  $vet=array();
  $rs= $con->query($sql);
  if($rs== false){
      die("query non valida");
  }else{
      while($record= $rs->fetch_assoc()){
          array_push($vet,$record);
      }

      $json=json_encode($vet);
      echo $json;
  }

  }else{
     die("parametri non validi");
  }

?>