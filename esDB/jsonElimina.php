<?php
    require("./connection.php");

    
    //var boolean per controllo input
    $parametriValidi = false;

    //ricevo i parametri
    if(isset($_REQUEST["txtId"])){
        $id = $_REQUEST["txtId"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if($parametriValidi){
        //rimuovo il materiale
        $sql = "DELETE FROM donazioni WHERE idPersona=$id";
    }else{
        //exit()
        die("errore in jsonElimina - parametri");
    }

    //eseguo la query
    $rs = $con->query($sql);

    if($rs == false){
        die("errore in jsonElimina - query");
    }

    echo "elemento eliminato";

?>