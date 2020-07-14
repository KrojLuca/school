<?php

    require("./connection.php");


    //var boolean per controllo input
    $parametriValidi = false;

    //ricevo i parametri
    if(isset($_REQUEST["Orario"])){
        $Orario = $_REQUEST["Orario"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["Giorno"])){
        $Giorno = $_REQUEST["Giorno"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["Mese"])){
        $Mese = $_REQUEST["Mese"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["Anno"])){
        $Anno = $_REQUEST["Anno"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["txtTipoVisita"])){
        $TipoVisita = $_REQUEST["txtTipoVisita"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if($parametriValidi == true){
            //rimuovo il materiale
            $sql = "SELECT * FROM calendarioprenotazioni WHERE TipologiaVisita='$TipoVisita' AND Orario='$Orario' AND Giorno='$Giorno' AND Mese='$Mese' AND Anno='$Anno'
            ORDER BY Orario, Anno, Mese, Giorno";
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