<?php

require("./connection.php");

//var boolean per controllo input
$parametriValidi = false;


//ricevo i parametri
if(isset($_REQUEST["CodiceVisita"])){
    $CodiceVisita = $_REQUEST["CodiceVisita"];
    $parametriValidi = true;
}else{
    $parametriValidi = false;
}

if($parametriValidi == true){
        //rimuovo il materiale
        $sql = "SELECT * FROM pazienti WHERE CodiceVisita='$CodiceVisita'";
}else{
    die("errore nel cercaPrenotazione.php - parametri");
}

$rs = $con->query($sql);
if($rs == false){
    die("errore nel cercaPrenotazione.php - query");
}

//vettore che contiene i materiali ricercati
$vet = array();

while($record = $rs->fetch_assoc()){
    array_push($vet, $record);
}

$json = json_encode($vet);

echo $json;

?>