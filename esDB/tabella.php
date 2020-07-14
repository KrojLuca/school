<?php

    require_once ("./login/testLogin.php");

?>

<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="utf-8" >
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" >
		<meta name="author" content="Lepre Ferdinando" >
		
		<title>Tesina Esame di Stato Lepre Ferdinando</title>
		
		<script src="../vendor/jquery/jquery-3.4.1.js"></script>
		<link rel="stylesheet" href="../vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../vendor/jquery-ui-1.12.1.custom/jquery-ui.css">

        <script type="application/javascript">

            function caricaDettaglio(id){     // si dichiara fuori il document ready perche non richiamato da ajax
               console.log("sei entrato nel dettaglio");
               var vDonazioni;
               var parametri="idPersona="+ id;
               var link="jsonDettaglio.php?"+parametri;
               console.log(parametri);
               $.get(link,function(data,status){
                   vDonazioni=JSON.parse(data);
                   console.log(vDonazioni);
                   numeroDonazioni=vDonazioni.length;
                   for(contdonazioni = 0; contdonazioni < vDonazioni.length; contdonazioni++){
                   if(vDonazioni.length==0){
                       $("#dettaglio").html("<tr><td>NESSUN PRODOTTO!</td></tr>");
                   }
                    $("#dettaglio").html("<br>date donazioni: "+vDonazioni[contdonazioni].dataDonazione+  "<br>Numero Totale di Donazioni:"+numeroDonazioni);
                   
                   
                   }
               });
           }
            //attendo che sia caricata la pagina
            $(document).ready(function(){

                function clearForm(){
                    $("#txtId").val("");
                    $("#txtNome").val("");
                    $("#txtCognome").val("");
                    $("#txtEta").val("");
                    $("#txtDataDonazione").val("");

                }

                function caricaTabella(){
                    //definiamo il link del file della chiamata per la lettura del db
                    var link = "jsonDonazioni.php";
                    //vettore che contiene i donazioni
                    var donazioni;

                    //facciamo la chiamata sul link, passando il link e la relativa funzione contenente i dati ricevuti
                    $.get(link, function(data, status){
                        //stampo il json
                        console.log(data);

                        //parsifichiamo il json
                        donazioni = JSON.parse(data);

                        //controllo sul json
                        if(donazioni.length == 0){
                            //stampo che è vuoto
                            $("#tbldonazioni").html("<tr><td>Elenco Donazioni Vuoto</td></tr>");
                        }else{
                            //inserisco nella tabella i dati
                            $("#tbldonazioni").html("<tr><th>ID PERSONA</th><th>NOME</th><th>COGNOME</th><th>ETA</th></tr>");
                            //ciclo for che scorre tutti i prodotti
                            for(contdonazioni = 0; contdonazioni < donazioni.length; contdonazioni++){
                                //dichiaro la riga
                                var tr = $("<tr></tr>");
                                //dichiaro le colonne
                                var idPersona = donazioni[contdonazioni].idPersona;
                                var nome = donazioni[contdonazioni].nome;
                                var cognome = donazioni[contdonazioni].cognome;
                                var eta = donazioni[contdonazioni].eta;
                                var dataDonazione = donazioni[contdonazioni].dataDonazione;

                                //appendo sulla riga
                                tr.append($("<td>" + idPersona + "</td><td>" + nome + "</td><td>" + cognome + "</td><td>" + eta +"</td><td><button type='button' class='btn btn-info btn-lg' data-toggle='modal' onclick='caricaDettaglio("+idPersona+")'>Dettaglio</button></td>"));

                                //<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
                               // <button  type='button' class='btn btn-secondary-form-control' onclick='caricaDettaglio(" +id+ ")'>dettaglio</button></td>
                                //append sulla tabella
                                //("+Nome +",\""+Cognome+"\")
                                $("#tbldonazioni").append(tr);
                            }
                        }
                    });
                }

                
                caricaTabella();

                $("#btnFiltra").click(function(){
                    //parametri
                    var parametri = "txtId=" + $("#txtId").val();
                    link = "./jsonFiltra.php?" + parametri;
                    console.log(link);
                    $.get(link, function(data, status){
                        console.log(data);

                        //parsifichiamo il json
                        donazioni = JSON.parse(data);

                        //controllo sul json
                        if(donazioni.length == 0){
                            //stampo che è vuoto
                            $("#tbldonazioni").html("<tr><td>Magazzino vuoto</td></tr>");
                        }else{
                            //inserisco nella tabella i dati
                            $("#tbldonazioni").html("<tr><th>ID DONAZIONE</th><th>NOME</th><th>COGNOME</th><th>ETA</th></tr>");
                            //ciclo for che scorre tutti i prodotti
                            for(contdonazioni = 0; contdonazioni < donazioni.length; contdonazioni++){
                                //dichiaro la riga
                                var tr = $("<tr></tr>");
                                //dichiaro le colonne
                                var idPersona = donazioni[contdonazioni].idPersona;
                                var nome = donazioni[contdonazioni].nome;
                                var cognome = donazioni[contdonazioni].cognome;
                                var eta = donazioni[contdonazioni].eta;

                                //appendo sulla riga
                                tr.append($("<td>" + idPersona + "</td><td>" + nome + "</td><td>" + cognome + "</td><td>" + eta +"</td><td><button type='button' class='btn btn-info btn-lg' data-toggle='modal' onclick='caricaDettaglio("+idPersona+")'>Dettaglio</button></td>"));

                                //append sulla tabella
                                $("#tbldonazioni").append(tr);
                            }
                        }
                        clearForm();                    });
                });

                $("#btnElimina").click(function(){
                    //scrivo i parametri
                    var parametri = "txtId=" + $("#txtId").val();
                    link = "./jsonElimina.php?" + parametri;
                    console.log(link);

                    $.get(link, function(data, status){
                        caricaTabella();
                        clearForm();
                    });
                });

                $("#btnRicarica").click(function(){
                   caricaTabella();
                });


                $("#btnAggiungi").click(function(){
                    //scrivo i parametri dell'url
                    var parametri = "txtId=" + $("#txtId").val() + "&txtNome=" + $("#txtNome").val() + "&txtCognome=" + $("#txtCognome").val() + "&txtEta=" + $("#txtEta").val() + "&txtDataDonazione=" + $("#txtDataDonazione").val();
                    link = "./jsonAggiungi.php?" + parametri;
                    console.log(link);

                    $.get(link, function(data, status){
                        caricaTabella();
                        clearForm();
                    });
                });


            });

        </script>
</head>
<body>
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-8">

               
                <table id="tbldonazioni" class="table table-hover">


                </table>

                <form name="formMagazzino" id="formMagazzino">

                    <div class="form-group input-group">
                        <input type="text" id="txtId" class="form-control" placeholder="id">
                    </div>
    
                    <div class="form-group input-group">
                        <input type="text" id="txtNome" class="form-control" placeholder="nome">
                    </div>
    
                    <div class="form-group input-group">
                        <input type="text" id="txtCognome" class="form-control" placeholder="cognome">
                    </div>

                    <div class="form-group input-group">
                        <input type="text" id="txtEta" class="form-control" placeholder="eta">
                    </div>

                    <div class="form-group input-group">
                        <input type="text" id="txtDataDonazione" class="form-control" placeholder="dataDonazione">
                    </div>

                    <div class="form-group input-group">
                        <input type="button" id="btnAggiungi" class="btn btn-secondary form-control" value="aggiungi">
                    </div>

                    <div class="form-group input-group">
                        <input type="button" id="btnElimina" class="btn btn-secondary form-control" value="elimina">
                    </div>

                    <div class="form-group input-group">
                        <input type="button" id="btnFiltra" class="btn btn-secondary form-control" value="filtra">
                    </div>

                    <div class="form-group input-group">
                        <input type="button" id="btnRicarica" class="btn btn-secondary form-control" value="ricarica">
                    </div>
                    <div class="col-sm-2" id="dettaglio" >
                    </div>
    
                </form>








            </div>
            <div class="col-sm-2">
                <?php
                    echo $_SESSION["utente"];
                ?>
                <a href="./login/logout.php">logout</a>
            </div>


            







        </div>









    </div>

</body>
</html>