<?php

    require("./connection.php");


    //var boolean per controllo input
    $parametriValidi = false;

    //ricevo i parametri
    if(isset($_REQUEST["txtId"])){
        $id = $_REQUEST["txtId"];
        $parametriValidi = true;
    }else{
        $parametriValidi= false;
    }

    if($parametriValidi== true){
        $sql = "SELECT * FROM donazioni WHERE idPersona='$id' 
        GROUP BY idPersona";
    }else{
        die("errore nel jsonFiltra.php - parametri");
    }

    $rs = $con->query($sql);
    if($rs == false){
        die("errore nel jsonFiltra.php - query");
    }

    //vettore che contiene i materiali ricercati
    $vet = array();

    while($record = $rs->fetch_assoc()){
        array_push($vet, $record);
    }

    $json = json_encode($vet);

    echo $json;

?>