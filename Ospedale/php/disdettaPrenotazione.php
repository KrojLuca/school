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

    if(isset($_REQUEST["txtTipoVisita"])){
        $TipoVisita = $_REQUEST["txtTipoVisita"];
        $parametriValidi = true;
    }else{
        $parametriValidi = false;
    }

    if($parametriValidi){
        $sql = "DELETE FROM calendarioprenotazioni WHERE CFPaziente='$CFPaziente' AND TipologiaVisita='$TipoVisita'";
        
    }else{
        //exit()
        die("errore in disdettaPrenotazione - parametri");
    }

    //eseguo la query
    $rs = $con->query($sql);

    if($rs == false){
        die("errore in disdettaPrenotazione - query");
    }

    echo "elemento eliminato";

?>