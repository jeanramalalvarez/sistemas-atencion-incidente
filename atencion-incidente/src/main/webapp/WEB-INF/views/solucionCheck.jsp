<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
	<head>
 		<title>Detalle de Valores Claves</title>
 		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-datepicker.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendors/datatables/css/jquery.dataTables.css">
		<!-- Optional theme -->
		
 		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
 		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/jquery.min.js"></script>
 		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script>
 		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap-datepicker.js"></script>
		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap-filestyle.min.js"></script>
 	</head>

 	<body>
 		<header>
 			<div class="bg-main">
 				<div id="header-nav" class="wrap">
 					<div class="pull-left">
 						<img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" /> 
 					</div>
 					<div class="pull-right" id="picture">
 						<img src="${pageContext.request.contextPath}/img/usuario.png" alt="Logo" /> 
 					</div>
 				</div>
 			</div>
 			<div class="wrap">
 				<p class="txt-oge text-right" >Usuario SI2921 - Javier Huaman</p>
 			</div>
 		</header>

 		<div class="wrap">
  			<div class="pull-left" id="menu">
    			<ul class="nav nav-pills nav-stacked">
  					<li><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Administraci&oacute;n</a></li>
            		<li >
            			<i class="glyphicon glyphicon-triangle-top arrow"></i>
            			<a href="#" class="menu">
            				<span class="glyphicon glyphicon-folder-close"></span>  Sol. de Atenci&oacute;n de Incidente 
            			</a>
            		</li>
            		<li>
            			<a href="${pageContext.request.contextPath}/solicitud/consulta" class="menu">
            				Consulta de Sol. CTI
            			</a>
            		</li>
            		<li ><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Sol. de Servicio</a></li>
            		<li ><a href="#" class="menu"> <span class="glyphicon glyphicon-folder-close"></span> Sol. de Atenci&oacute;n de Req.</a></li>
            		<li class="active"><a href="${pageContext.request.contextPath}/incidente/solucionCheck" style="padding-left: 30px" > <span class="glyphicon glyphicon-folder-close"></span> Actualizar Validaciones BC</a></li>
				</ul>
  			</div>
  			<div id="content">
    			<div class="container-fluid">
    				<div class="panel panel-default">
  						<div class="panel-heading">
    						<h3 class="panel-title">Actualizar Validaciones BC</h3>
  						</div>
  						<div class="panel-body">
    							<div class="row">
    								<div class="col-md-12" style="text-align: right;" >
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="secuencia" class="col-sm-2 control-label" >Número:</label>
    											<div class="col-sm-1">
    												<input type="text" id="idSolucionCh" disabled="disabled" class="form-control" style="" />
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="secuencia" class="col-sm-2 control-label" >Glosa:</label>
    											<div class="col-sm-5">
    												<input type="text" id="txtGlosa" class="form-control"/>
    											</div>
  											</div>
 											<div class="form-group">
    											<label for="secuencia" class="col-sm-2 control-label" >Pautas:</label>
    											<div class="col-sm-5">
    												<input type="text" id="txtPautas" class="form-control" />
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="secuencia" class="col-sm-2 control-label" >Descripción:</label>
    											<div class="col-sm-5">
    												<textarea id="txtDescripcion" class="form-control" style="resize: none;"></textarea>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="txtRuta" class="col-sm-2 control-label" >Ruta:</label>
    											<div class="col-sm-5">
    												<input id="txtRuta" type="file" name="txtRuta" class="filestyle" data-buttonText="Examinar"  />
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
							<div class="row">
								  <div class="pull-right" style="margin-right:10px;">
								  	<button id="btn_limpiar" type="button" class="btn btn-primary">Limpiar</button>
								    <button id="btn_guardar" type="button" class="btn btn-primary">Guardar</button>
								  </div>
								</div>
  						</div>
					</div>
					
	    				<table id="solucionCheckList" class="table table-striped table-bordered" cellspacing="0" width="100%">
					        <thead>
					            <tr>
					                <th style="width: 50px;" >Num</th>
					                <th style="width: 80px;">glosa</th>
					                <th style="width: 250px;">Pautas</th>
					                <th style="width: 100px;">Descripcion</th>
					                <th style="width: 250px;">Anexo</th>
					                <th>Opci&oacute;n</th>
					            </tr>
					        </thead>
					        <tfoot>
					            <tr>
      								<th style="width: 50px;" >Num</th>
					                <th style="width: 80px;">glosa</th>
					                <th style="width: 250px;">Pautas</th>
					                <th style="width: 100px;">Descripcion</th>
					                <th style="width: 250px;">Anexo</th>
					                <th>Opci&oacute;n</th>
					            </tr>
					        </tfoot>
					        <tbody>
					        </tbody>
					    </table>


    			</div>
  			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/datatables/js/jquery.dataTables.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/moment.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/solucionCheck.js"></script>
 	</body>

 </html>