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

    if(isset($_REQUEST["inCognome"])){
        $Cognome = $_REQUEST["inCognome"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["inNome"])){
        $Nome = $_REQUEST["inNome"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["inComuneNascita"])){
        $ComuneNascita = $_REQUEST["inComuneNascita"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["inProvincia"])){
        $Provincia = $_REQUEST["inProvincia"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["inDataNascita"])){
        $DataNascita = $_REQUEST["inDataNascita"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["inSesso"])){
        $Sesso = $_REQUEST["inSesso"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["CodiceVisita"])){
        $CodiceVisita = $_REQUEST["CodiceVisita"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }
    
    console.log("sono arrivato alle query");


    if($parametriValidi){
        $sql = "INSERT INTO pazienti(CodiceVisita, CFPaziente, nome, cognome, luogoNascita, provincia, dataNascita, sesso) VALUES ('$CodiceVisita', '$CFPaziente', '$Nome', '$Cognome', '$ComuneNascita', '$Provincia', '$DataNascita', '$Sesso')"
    }else{
        die("errore in aggiungiDati.php - parametri");
    }

    

    //faccio la query
    $rs = $con->query($sql);
    //controllo sulla query
    if($rs = false){
        die("errore in aggiungiDati.php - query");
    }

    echo "aggiunta avvenuta";

?>