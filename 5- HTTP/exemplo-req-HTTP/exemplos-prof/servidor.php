<?php
    //libero o acesso por http request de outros domínios
    header('Access-Control-Allow-Origin: *');
	
	if(isset($_GET['parametro1']) && isset($_GET['parametro2'])){
		$x = $_GET['parametro1'];
		$y = $_GET['parametro2'];
		$resposta = $x+$y;
		echo "Resposta pelo GET: ".$resposta;
	}
	
	else if(isset($_POST['parametro1']) && isset($_POST['parametro2'])){
		$x = $_POST['parametro1'];
		$y = $_POST['parametro2'];
		$resposta = $x+$y;
		echo "Resposta pelo POST: ".$resposta;
	}
	
	else{
		echo "Nenhum parâmetro passado, nem por GET e nem por POST";
	}
?>