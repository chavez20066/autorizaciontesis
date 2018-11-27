 var rowSelect=null;
 var BASE_CONTEXT_PATH=null;
$(document).ready(function () {   	
  
   $('.modal').modal();
   $('select').material_select();   
   BASE_CONTEXT_PATH = $('meta[name=context-path]').attr("content");
   BASE_CONTEXT_PATH = BASE_CONTEXT_PATH.substr(0, BASE_CONTEXT_PATH.length - 1);
   getDocentes();   
});

function getDocentes(){	
	  var token = $('#csrfToken').val();     
	  $.ajax({
	        url: BASE_CONTEXT_PATH+'/autorizaciontesis/listDocentes',
	        type: 'POST',
	        data:{"_csrf":token},
	        success: function (datosDocentes)
	        {	
	        	$('#asesor').autocomplete({
	                data: datosDocentes
	            });	        	
	        }
	    });
}
function operaEvento(evento){	
	   var abuscar=$('#asesor').val();	 
	   $('#asesor').autocomplete({data: {"":""} });
	   $.ajax({
	        url: BASE_CONTEXT_PATH+'/autorizaciontesis/suggestion?request='+abuscar,
	        type: 'POST',
	        dataType: 'json',
	        success: function (datosDocentes)
	        {	
	        	$('#asesor').autocomplete({
	        	   	destroy:({})
	            });
	        	$('#asesor').autocomplete({
	        		 data: {
	        		        "Apple": null,	        		        
	        		      },
		            });	        	
	        }
	    });
}
$("#codigo").keyup(function () {
    if ($(this).val().length > 10) {
        $(this).val($(this).val().substr(0, 10));
    }
});
$("#dni").keyup(function () {
    if ($(this).val().length > 8) {
        $(this).val($(this).val().substr(0, 8));
    }
});
$('.solo-numero').keyup(function () {
    this.value = (this.value + '').replace(/[^0-9]/g, '');
});
$("#btnAgregarAutor").on("click", function () { 	
    limpiar();       
    $("#divAutorizacionTesis").slideUp("slow");   
    $("#divAutor").slideDown("slow");
    
});


$("#btnBuscarAlumno").on("click", function () {	
    var codigoAlumno = $("#codigo").val();   
    var token = $('#csrfToken').val();     
    
    $.ajax({
        url: BASE_CONTEXT_PATH+'/autorizaciontesis/alumno',
        type: 'POST',
        data:  { codigoAlumno: codigoAlumno,"_csrf":token},
        success: function (response) {        
          
            $("#dni").val(response.dni);
            $("#apellidos").val(response.apellidos);
            $("#nombres").val(response.nombres);
            $("#email").val(response.email);
            $("#telefono").val(response.tel_fij);
            $("#celular").val(response.celular);
            $("#direccion").val(response.direccion);
            Materialize.updateTextFields();
            $("#facultad option[value=" + response.idFacultad + "]").attr("selected", true);
            $('#facultad').material_select();
            
            $("#titulo option[value=" + response.idEscuela + "]").attr("selected", true);
            $('#titulo').material_select();
            
            $.ajax({
                url: BASE_CONTEXT_PATH+'/autorizaciontesis/escuelas',
                data:  { idfacultad: response.idFacultad},
                success: function (response_escuelas) {                 
                  
                    $('#escuela').material_select('destroy');
                    $('#escuela').empty().html(' ');
                    $("#escuela").append('<option value="0" disabled selected>Seleccione una opción</option>');
                    for(var i in response_escuelas)
                    {
                    	$("#escuela").append('<option value="' + response_escuelas[i].idPrograma + '">' + response_escuelas[i].progProf + '</option>');
                    }             
                   
                    $("#escuela option[value=" + response.idEscuela + "]").attr("selected", true);
                    $('#escuela').material_select();                
                   
                },
                error: function (jqXHR, exception) {                	 
                     getErrorMessage(jqXHR, exception);
                }
            });
            
            
        },
        error: function (jqXHR, exception) {
        	if (jqXHR.status == 302) {
        		$("#codigoForm").val($("#codigo").val());   			 	
        		swal({
        			  title: "Advertencia",
        			  text: "Para modificar la constancia por favor ingrese su correo electrónico valido, para modificar la Autorización",
        			  type: "input",
        			  showCancelButton: true,
        			  closeOnConfirm: false,
        			  inputPlaceholder: "correo electrónico"
        			}, function (inputValue) {
        			  $("#emailForm").val(inputValue);
        			  
        			  if (inputValue === false) return false;
        			  if (inputValue === "") {
        			    swal.showInputError("Tienes que ingresar tu correo");
        			    return false
        			  }       			          			 
        			        			  
        			  document.getElementById('formhref').submit();
        			  
        			});        		 	
            }
        	else{
        	 swal({title: "Advertencia", text: "El alumno no existe, por favor ingrese el código de matricula", type: "warning", confirmButtonText: "Aceptar"});
        	 getErrorMessage(jqXHR, exception);
             }
        }
    });
});

$("#departamento").on("change", function () {
	var departamentoSelect = $("#departamento").val();
	  $.ajax({
	      url: BASE_CONTEXT_PATH+'/autorizaciontesis/provincias',
	      data:  { iddepartamento: departamentoSelect},
	      success: function (response) {	         
	          
	          $('#provincia').material_select('destroy');
	          $('#provincia').empty().html(' ');
	          $("#provincia").append('<option value="0" disabled selected>Seleccione una opción</option>');
	          $('#provincia').material_select();
	        
	          $('#distrito').material_select('destroy');
	          $('#distrito').empty().html(' ');
	          $("#distrito").append('<option value="0" disabled selected>Seleccione una opción</option>');
	          $('#distrito').material_select();         
	          	      	          
	          for(var i in response)
	          {
	          	$("#provincia").append('<option value="' + response[i].idprovincia + '">' + response[i].provincia + '</option>');
	          }
	          $('#provincia').material_select();          
	      },
	      error: function (jqXHR, exception) {	      	
	           getErrorMessage(jqXHR, exception);
	      }
	  });
});

$("#provincia").on("change", function () {

    var provinciaSelect = $("#provincia").val();
    var departamentoSelect = $("#departamento").val();
    
    $.ajax({
	      url: BASE_CONTEXT_PATH+'/autorizaciontesis/distritos',
	      data:  { iddepartamento: departamentoSelect,
	    	  	   idprovincia: provinciaSelect},
	      success: function (response) {	               
	          
	          
	          $('#distrito').material_select('destroy');
	          $('#distrito').empty().html(' ');
	          $("#distrito").append('<option value="0" disabled selected>Seleccione una opción</option>');
	          $('#distrito').material_select();    
	              
	          	      	          
	          for(var i in response)
	          {
	          	$("#distrito").append('<option value="' + response[i].idDistrito + '">' + response[i].distrito + '</option>');
	          }
	          $('#distrito').material_select();          
	      },
	      error: function (jqXHR, exception) {	      	
	           getErrorMessage(jqXHR, exception);
	      }
	  });     
});
$("#facultad").on("change", function () {

    var FacultadSelect = $("#facultad").val();
    $.get(BASE_CONTEXT_PATH+'/autorizaciontesis/escuelas', {
        idfacultad: FacultadSelect
    }, function (response) {  	       
    	
        $('#escuela').material_select('destroy');
        $('#escuela').empty().html(' ');
        $("#escuela").append('<option value="0" disabled selected>Seleccione una opción</option>');
        for(var i in response)
        {
        	$("#escuela").append('<option value="' + response[i].idPrograma + '">' + response[i].progProf + '</option>');
        }       
        $('#escuela').material_select();
    });
});

$("#facultadListarTesis").on("change", function () {

    var FacultadSelect = $("#facultadListarTesis").val();
    $.get(BASE_CONTEXT_PATH+'/autorizaciontesis/escuelas', {
        idfacultad: FacultadSelect
    }, function (response) {  	       
    	
        $('#escuelaListarTesis').material_select('destroy');
        $('#escuelaListarTesis').empty().html(' ');
        $("#escuelaListarTesis").append('<option value="1" selected>Todas las Escuelas Profesionales</option>');
        for(var i in response)
        {
        	$("#escuelaListarTesis").append('<option value="' + response[i].idPrograma + '">' + response[i].progProf + '</option>');
        }       
        $('#escuelaListarTesis').material_select();
    });
});


function getErrorMessage(jqXHR, exception) {
    var msg = '';
    if (jqXHR.status === 0) {
        msg = 'Not connect.\n Verify Network.';
    } else if (jqXHR.status == 404) {
        msg = 'Requested page not found. [404]';
    } else if (jqXHR.status == 500) {
        msg = 'Internal Server Error [500].';
    } else if (jqXHR.status == 302) {
        msg = 'found[302].';
    } else if (exception === 'parsererror') {
        msg = 'Requested JSON parse failed.';
    } else if (exception === 'timeout') {
        msg = 'Time out error.';
    } else if (exception === 'abort') {
        msg = 'Ajax request aborted.';
    } else {
        msg = 'Uncaught Error.\n' + jqXHR.responseText;
    }   
    console.log(msg);
}
$("#AgregarAutor").on("click", function () {
	
	if(rowSelect!=null) rowSelect.closest('tr').remove();
    if ($("#codigo").val() === "" || $("#dni").val() === "" || $("#apellidos").val() === "" 
    	|| $("#nombres").val() === "" || $("#email").val() === "" || $("#celular").val() === ""
        || $("#ciudad").val() === null || $("#provincia").val() === null || $("#distrito").val() === null 
        || $("#direccion").val() === "" || $("#facultad").val() === null || $("#escuela").val() === null
        || $("#titulo").val() === null) {
        swal({title: "Error", text: "Por favor completar sus datos", type: "error", confirmButtonText: "Aceptar"});
    } else {
        if ($("#codigo").val().length !== 10 || $("#dni").val().length !== 8) {
            swal({title: "Error", text: "Código o DNI incorrectos", type: "error", confirmButtonText: "Aceptar"});
        } else {
            var codigo = $("#codigo").val();
            var dni = $("#dni").val();
            var apellidos = $("#apellidos").val();
            var nombres = $("#nombres").val();
            var email = $("#email").val();
            var telefono = $("#telefono").val();
            var celular = $("#celular").val();
            var departamento = $("#departamento option:selected").html();
            var idDepartamento = $("#departamento").val();
            var provincia = $("#provincia option:selected").html();
            var idProvincia = $("#provincia").val();
            var distrito = $("#distrito option:selected").html();
            var idDistrito = $("#distrito").val();
            var direccion = $("#direccion").val();
            var facultad = $("#facultad option:selected").html();
            var idFacultad = $("#facultad").val();
            var escuela = $("#escuela option:selected").html();
            var idEscuela = $("#escuela").val();
            var titulo = $("#titulo option:selected").html();
            var idTitulo = $("#titulo").val();
            
          
            $('#tablaAutores').append('<tr><td th:text="'+codigo+'">'+codigo+'</td>'+
            		'<td th:text="'+dni+'">'+dni+'</td>'+
            		'<td th:text="'+apellidos+'">'+apellidos+'</td>'+
            		'<td th:text="'+nombres+'">'+nombres+'</td>'+
            		'<td style="display:none;" th:text="'+idFacultad+'" >'+idFacultad+'</td>'+
            		'<td style="display:none;" th:text="'+idEscuela+'">'+idEscuela+'</td>'+
            		'<td style="display:none;" th:text="'+telefono+'">'+telefono+'</td>'+
            		'<td style="display:none;" th:text="'+celular+'">'+celular+'</td>'+            		
            		'<td style="display:none;" th:text="'+email+'">'+email+'</td>'+
            		'<td style="display:none;" th:text="'+idDepartamento+'">'+idDepartamento+'</td>'+
            		'<td style="display:none;" th:text="'+idProvincia+'">'+idProvincia+'</td>'+
            		'<td style="display:none;" th:text="'+idDistrito+'">'+idDistrito+'</td>'+
            		'<td style="display:none;" th:text="'+direccion+'">'+direccion+'</td>'+
            		'<td style="display:none;" th:text="'+idTitulo+'">'+idTitulo+'</td>'+            		
               		'<td><a class="editar btn-floating btn-large waves-effect waves-light yellow"><i class="material-icons">edit</i></a></td>'+
            		'<td><a class="borrar btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></a></td></tr>');
    
            $("#divAutorizacionTesis").slideDown("slow");   
            $("#divAutor").slideUp("slow");
            
        }
    }
});

$("#cancelarAutor").on("click",function(){
	 $("#divAutorizacionTesis").slideDown("slow");   
     $("#divAutor").slideUp("slow");
});

$("#guardar").on("click", function () {
	
    var Modalidad = $("#TipoInvest").val();
    var tituloTesis = $("#Titulo").val();
    var asesor = $("#asesor").val();
    var Autores = [];
    var correos="";
    var DatosTesis = {
		'Autorizac': $("#Autorizac").val(),
        'TipoTesis': Modalidad,
        'Titulo': tituloTesis,
        'Asesor': asesor
    };
   
    
    $('#tablaAutores tr').each(function (index) {
        if (index > 0) {
        	        	
            var codigo = $(this).find("td").eq(0).html();
            var dni = $(this).find("td").eq(1).html();
            var apellidos = $(this).find("td").eq(2).html();
            var nombres = $(this).find("td").eq(3).html();
            var idFacultad = $(this).find("td").eq(4).html();
            var idEscuela = $(this).find("td").eq(5).html();
            var telefono = $(this).find("td").eq(6).html();
            var celular = $(this).find("td").eq(7).html();            
            var email = $(this).find("td").eq(8).html();           
            var idDepartamento = $(this).find("td").eq(9).html();
            var idProvincia = $(this).find("td").eq(10).html();
            var idDistrito = $(this).find("td").eq(11).html();
            var direccion = $(this).find("td").eq(12).html();
            var idTitulo = $(this).find("td").eq(13).html();           
           
            correos+=email +" ";
            Autores.push({"codigo": codigo, "dni": dni, "apellidos": apellidos, "nombres": nombres,
            	"idFacultad": idFacultad, "idEscuela": idEscuela,            	
            	"telefono": telefono, "celular": celular, "email":email,
            	"idDepartamento": idDepartamento,"idProvincia": idProvincia, "idDistrito": idDistrito, 
            	"direccion": direccion,  "idTitulo": idTitulo});
        }
    });
    if (tituloTesis === "" || asesor === "" || Autores.length === 0) {
        swal({title: "Error", text: "Complete los campos necesarios", type: "error", confirmButtonText: "Aceptar"});
    } else {
    	 var token = $('#csrfToken').val();
    	 $.ajax({
    		 url: BASE_CONTEXT_PATH+'/autorizaciontesis/GuardarAutorizacion',  
    		 type: 'POST',
    		 data:  { datosAutorizacion: JSON.stringify(DatosTesis),
    			 Autores: JSON.stringify(Autores),"_csrf":token},
   	      success: function (response) {   	    	 
   	    	  response=JSON.parse(response);   	    	
 	    	  
   	    	  if(response.RESULT=="OK"){
	   	    	swal({
	                title: "Registro Satisfactorio",
	                text: "La constancia se va descargar en su navegador y se enviara a:\n " +
	                correos+"\n"+
	                "Por favor acérquese a Biblioteca con los ejemplares de su tesis y la constancia impresa",
	                type: "success"
	            },
	            function () {
	                window.open(BASE_CONTEXT_PATH+'/verPDF?idAutorizacion='+response.idAutorizacion+ '&format=pdf');	                
	    	 		window.location.href = '/autorizaciontesis';                    
	            });
   	    	  }else{
   	    		 swal({title: "Error", text: "Algo salió mal, vuelva a intentárlo", type: "error", confirmButtonText: "Aceptar"});
   	    	  }
   	      },
   	      error: function (jqXHR, exception) {	      	
   	           getErrorMessage(jqXHR, exception);
   	           swal({title: "Error", text: "No se pudo registrar su Autorización, Por favor revisar sus datos y vuelva a intentarlo.", type: "error", confirmButtonText: "Aceptar"});
            
   	      }
   	  });
 
    }
});

$(document).on('click', '.borrar', function (event) {
    event.preventDefault();
    $(this).closest('tr').remove();
});
$(document).on('click', '.editar', function (event) {
    event.preventDefault();
    rowSelect=$(this);   
   
    limpiar();       
    $("#divAutorizacionTesis").slideUp("slow");   
    $("#divAutor").slideDown("slow");
    
    
   
    $("#codigo").val($(this).closest('tr').find("td").eq(0).html());
    $("#dni").val($(this).closest('tr').find("td").eq(1).html());
    $("#apellidos").val($(this).closest('tr').find("td").eq(2).html());
    $("#nombres").val($(this).closest('tr').find("td").eq(3).html());
    $("#facultad option[value=" + $(this).closest('tr').find("td").eq(4).html() + "]").attr("selected", true);
    $('#facultad').material_select();
    var idEscuela=$(this).closest('tr').find("td").eq(5).html();
    
    
    $("#telefono").val($(this).closest('tr').find("td").eq(6).html());
    $("#celular").val($(this).closest('tr').find("td").eq(7).html());
    $("#email").val($(this).closest('tr').find("td").eq(8).html()); 
    $("#departamento option[value=" + $(this).closest('tr').find("td").eq(9).html() + "]").attr("selected", true);
    $('#departamento').material_select();    
    var idProvincia=$(this).closest('tr').find("td").eq(10).html();
    var idDistrito=$(this).closest('tr').find("td").eq(11).html();
    $("#direccion").val($(this).closest('tr').find("td").eq(12).html());
    
    var idtitulo = $(this).closest('tr').find("td").eq(13).html()
    $("#titulo option[value=" + idtitulo + "]").attr("selected", true);
    $('#titulo').material_select();  
    
    Materialize.updateTextFields();
    $.ajax({
        url: BASE_CONTEXT_PATH+'/autorizaciontesis/escuelas',
        data:  { idfacultad: $(this).closest('tr').find("td").eq(4).html()},
        success: function (response_escuelas) {           
            
            $('#escuela').material_select('destroy');
            $('#escuela').empty().html(' ');
            $("#escuela").append('<option value="0" disabled selected>Seleccione una opción</option>');
            for(var i in response_escuelas)
            {
            	$("#escuela").append('<option value="' + response_escuelas[i].idPrograma + '">' + response_escuelas[i].progProf + '</option>');
            }                   
            
            $("#escuela option[value=" + idEscuela + "]").attr("selected", true);
            $('#escuela').material_select();   
            
           
        },
        error: function (jqXHR, exception) {
        	 
             getErrorMessage(jqXHR, exception);
        }
    });
    
	var departamentoSelect = $("#departamento").val();
	  $.ajax({
	      url: BASE_CONTEXT_PATH+'/autorizaciontesis/provincias',
	      data:  { iddepartamento: departamentoSelect},
	      success: function (response) {	        
	          		          
	          
	          $('#provincia').material_select('destroy');
	          $('#provincia').empty().html(' ');
	          $("#provincia").append('<option value="0" disabled selected>Seleccione una opción</option>');
	          $('#provincia').material_select();
	                          
	          	      	          
	          for(var i in response)
	          {
	          	$("#provincia").append('<option value="' + response[i].idprovincia + '">' + response[i].provincia + '</option>');
	          }
			  $("#provincia option[value=" + idProvincia + "]").attr("selected", true);
			  $('#provincia').material_select();   
	      },
	      error: function (jqXHR, exception) {	      	
	           getErrorMessage(jqXHR, exception);
	      }
	  });
	  
	  
	    var departamentoSelect = $("#departamento").val();
	    
	    
	    $.ajax({
		      url: BASE_CONTEXT_PATH+'/autorizaciontesis/distritos',
		      data:  { iddepartamento: departamentoSelect,
		    	  	   idprovincia: idProvincia},
		      success: function (response) {		              		          
		          
		          $('#distrito').material_select('destroy');
		          $('#distrito').empty().html(' ');
		          $("#distrito").append('<option value="0" disabled selected>Seleccione una opción</option>');
		          $('#distrito').material_select();    
		              
		          	      	          
		          for(var i in response)
		          {
		          	$("#distrito").append('<option value="' + response[i].idDistrito + '">' + response[i].distrito + '</option>');
		          }
		        
		        
				  $("#distrito option[value=" + idDistrito + "]").attr("selected", true);
				  $('#distrito').material_select(); 
		      },
		      error: function (jqXHR, exception) {
		      	
		           getErrorMessage(jqXHR, exception);
		      }
		  }); 	  
    
});

function limpiar() {

    $('#provincia distrito escuela').material_select('destroy');
    $('#provincia distrito escuela').empty().html(' ');
    $('#provincia distrito escuela').append('<option value="0" disabled selected>Seleccione una opción</option>');
    $('#provincia distrito escuela').material_select();
    $('#divAutor input').val('');
}

