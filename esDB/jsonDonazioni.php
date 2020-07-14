<?php
    require("./connection.php");

    //scrivo la query
    $sql = "SELECT * FROM donazioni
            GROUP BY idPersona";

    //faccio la query sulla connessione
    $rs = $con->query($sql);
    //faccio il controllo
    if($rs == false){
        //messaggio di errore + exit()
        die("errore nella query jsonMateriali.php");
    }

    //vettore che conterrà i materiali
    $vet = array();


    //ciclo che scorre gli elementi della query
    while($record = $rs->fetch_assoc()){

        //metto nel vettore ogni record, VETTORE DI RECORD
        array_push($vet, $record);
    }

    //converto il vettore in un json
    $json = json_encode($vet);

    echo $json;



?>