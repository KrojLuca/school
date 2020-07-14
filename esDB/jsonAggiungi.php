<?php
    require("./connection.php");


    //var boolean per controllo input
    $parametriValidi = false;

    //ricevo i parametri
    if(isset($_REQUEST["txtId"])){
        $idPersona = $_REQUEST["txtId"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["txtNome"])){
        $nome = $_REQUEST["txtNome"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["txtCognome"])){
        $cognome = $_REQUEST["txtCognome"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["txtEta"])){
        $eta = $_REQUEST["txtEta"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if(isset($_REQUEST["txtDataDonazione"])){
        $dataDonazione = $_REQUEST["txtDataDonazione"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }
    
    console.log("sono arrivato alle query");
    if($parametriValidi){
        //query per aggungere il prodotto
        $sql = "INSERT INTO donazioni(idPersona, nome, cognome,eta,dataDonazione) VALUES ('$idPersona', '$nome', '$cognome','$eta','$dataDonazione')";
        $sql2="INSERT INTO trasfusioni(idPersona) VALUES ('$idPersona')";


    }else{
        die("errore in jsonAggiungi.php - parametri");
    }


    $rs = $con->query($sql2);
    //controllo sulla query
    if($rs = false){
        die("errore in jsonAggiungi.php - query");
    }

    //faccio la query
    $rs = $con->query($sql);
    //controllo sulla query
    if($rs = false){
        die("errore in jsonAggiungi.php - query");
    }

    echo "aggiunta avvenuta";



?>