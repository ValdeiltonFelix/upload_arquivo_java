<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script type="text/javascript" src="resource/js/jQuery-v3.3.1.js"></script>

<title>Cadastro de arquivo</title>
<style>
ul{
background-color:#eee;
cursor:pointer;
}

li{
     pandding=12px;
}
#arq{
padding:0;
}
</style>
</head>

<body>

<div class="container" style="width:500px;">
        <h3 alegn="center">Cadastro de arquivos</h3>

         <form action="salvararquivo" method="post" enctype="multipart/form-data">
         		<input type="file"  class="form-control" id="arq" name="arq">
         		<input type="text"  class="form-control" id="nomearq" name="nomearq">
         		<input type="text" id="idarquivo" name="idarquivo" readonly="" value="" placeholder="Codigo usuario"   class="form-control"/>
         		<input type="text" id="arquivo" name="arquivo" class="form-control" value="" placeholder="Entre com nome do usuário"  autocomplete="off" />         
         		<div id="listar"></div>
         		<button class="btn">Enviar</button>

         </form>
</div>

<div id="conteudo"> 

</div>

<script>
  $(document).ready(function(){
	  
	  $("#arquivo").keyup(function(){
		  var query= $(this).val();
		  if(query != ""){
			  $.ajax({
			  		url:"carregardados?dados="+query,
			  		method:"POST",
			  		data:{query:query},
			  		success:function(data){
			  			$('#listar').fadeIn();
			  			$('#listar').html(data);
			  		}
			  	})
		  }
	  })
	  
  });

$(document).on("click",'li',function(){
	
	var codigotexto=$(this).text().split('.');
	$("#idarquivo").val(codigotexto[0]);
	$("#arquivo").val(codigotexto[1]);
	$('#listar').fadeOut();
	 AssignValues();
});

var arq=document.getElementById("arq");

arq.addEventListener("change", function (event) {
	  if (document.getElementById("arq").files.length != 0) {
	    document.getElementById("nomearq").value=document.getElementById("arq").files[0].name;
	  }
	  
	  });



///envia o arquivo por ajax nesse caso o formulario inteiro
/*
var arquivo = document.getElementById("arquivo");
var formulario = document.getElementById("formulario");

arquivo.addEventListener("change", function (event) {
  if (arquivo.files.length == 0) {
    alert("Nenhum Arquivo Selecionado");
    return;
  }

  //Enviando o Arquivo por AJAX e monitorando o Progresso.
  var data = new FormData(formulario);
  var xmlHttp = new XMLHttpRequest();
  xmlHttp.open("POST", urlEnvio);
  xmlHttp.upload.onprogress = function(event) {
    if (event.lengthComputable) {
      var progresso = (event.loaded / event.total) * 100;
    }
  };
  xmlHttp.send(data);
})
*/


function AssignValues()
{
	
	var arq = document.getElementById("arq"); 
	var nomearq=document.getElementById("nomearq").value;
	var idusuario=document.getElementById("idarquivo").value
	var type=arq.files[0].type;
	//alert("carregardados?acao=gravassesion&id="+idusuario+"&nomearquivo="+nomearq+"&type="+type);
	
	 $.ajax({
	  		url:"carregardados",//&id="+idusuario,//+"&nomearquivo="+nomearq+"&type="+type,
	  		method:"POST",
	  		data:{"acao":"salvasessaodoarquivo","idarquivo":idusuario,"nomearq":nomearq,"type":type},
	  		success:function(data){
	  			
	  			alert("Registro do usuario gravado em sessão");
	  			
	  		}
	  	})

}

if("${dados.errocod}" !="" ){

	alert("${dados.message}");
}

</script>

</body>
</html>