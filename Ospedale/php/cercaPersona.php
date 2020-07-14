<?php

    require("./connection.php");


    //var boolean per controllo input
    $parametriValidi = false;

    //ricevo i parametri
    if(isset($_REQUEST["txtCFPaziente"])){
        $CFPaziente = $_REQUEST["txtCFPaziente"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if($parametriValidi == true){
            //rimuovo il materiale
            $sql = "SELECT * FROM calendarioprenotazioni, pazienti WHERE CFPaziente='$CFPaziente'
            GROUP BY CFPaziente";
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