<?php
    require("./connection.php");


    //var boolean per controllo input
    $parametriValidi = false;

    //ricevo i parametri
    if(isset($_REQUEST["txtTipoVisita"])){
        $TipoVisita = $_REQUEST["txtTipoVisita"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["txtOrario"])){
        $Orario = $_REQUEST["txtOrario"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }
    
    console.log("sono arrivato alle query");


    if($parametriValidi){
        $sql = "INSERT INTO calendarioprenotazioni(TipologiaVisita, Orario) VALUES ('$TipoVisita','$Orario')";
    }else{
        die("errore in aggiungiPrenotazioneVisita.php - parametri");
    }



    //faccio la query
    $rs = $con->query($sql);
    //controllo sulla query
    if($rs = false){
        die("errore in aggiungiPrenotazioneVisita.php - query");
    }

?>