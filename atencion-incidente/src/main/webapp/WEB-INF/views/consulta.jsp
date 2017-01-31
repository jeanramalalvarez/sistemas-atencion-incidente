<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
	<head>
 		<title>Atencion de Incidentes</title>
 		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="/atencion-incidente/vendors/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="/atencion-incidente/vendors/bootstrap/css/bootstrap-datepicker.css" />
		<link rel="stylesheet" type="text/css" href="/atencion-incidente/vendors/datatables/css/jquery.dataTables.css">
		<!-- Optional theme -->
		
 		<link rel="stylesheet" href="/atencion-incidente/resources/css/main.css" />
 		<script type="text/javascript" src="/atencion-incidente/vendors/js/jquery.min.js"></script>
 		<script src="/atencion-incidente/vendors/bootstrap/js/bootstrap.min.js"></script>
 		<script src="/atencion-incidente/vendors/bootstrap/js/bootstrap-datepicker.js"></script>

 	</head>

 	<body>
 		<header>
 			<div class="bg-main">
 				<div id="header-nav" class="wrap">
 					<div class="pull-left">
 						<img src="/atencion-incidente/img/logo.png" alt="Logo" /> 
 					</div>
 					<div class="pull-right" id="picture">
 						<img src="/atencion-incidente/img/usuario.png" alt="Logo" /> 
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
            		<li class="active">
            			<a href="/atencion-incidente/solicitud/consulta" style="padding-left: 30px">
            				Consulta de Sol. CTI
            			</a>
            		</li>
            		<li ><a href="#" class="menu"><span class="glyphicon glyphicon-folder-close"></span> Sol. de Servicio</a></li>
            		<li ><a href="#" class="menu"> <span class="glyphicon glyphicon-folder-close"></span> Sol. de Atenci&oacute;n de Req.</a></li>
				</ul>
  			</div>
  			<div id="content">
    			<div class="container-fluid">
    				<div class="panel panel-default">
  						<div class="panel-heading">
    						<h3 class="panel-title">Consulta de Solicitudes de Atenci&oacute;n de Incidente</h3>
  						</div>
  						<div class="panel-body">
    						<form>
    							<div class="row">
    								<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Nro. Sol. CTI:</label>
    											<div class="col-sm-7">
      												<input type="text" class="form-control" id="nrocti" placeholder="Nro CTI">
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Fecha de Inicio:</label>
    											<div class="col-sm-7">
      												<div class="input-group date">
    													<input id="fecha_ini" type="text" class="form-control datepicker1" >
    													<div class="input-group-addon">
        													<span class="glyphicon glyphicon-th"></span>
    													</div>
													</div>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Tipo de Solicitud:</label>
    											<div class="col-sm-7">
													<form:select path="tipoSolicitud" id="tipoSolicitud" cssClass="form-control" required="true" >
												    	<form:option value="" label="Seleccionar" />
    													<form:options items="${tipoSolicitud}" />
													</form:select>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Fecha Fin:</label>
    											<div class="col-sm-7">
      												<div class="input-group date" >
    													<input id="fecha_fin" type="text" class="form-control datepicker1" >
    													<div class="input-group-addon">
        													<span class="glyphicon glyphicon-th"></span>
    													</div>
													</div>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Estado:</label>
    											<div class="col-sm-7">
      												
													<select id="estado" name="estado" class="form-control" >
														<option value="">Seleccionar</option>
														
													</select>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Nro. SS:</label>
    											<div class="col-sm-7">
      												<input type="text" id="nroSS" class="form-control" placeholder="Nro SS">
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								<div class="row">
								  <div class="pull-right" style="margin-right:10px;">
								    <button type="button" class="btn btn-primary">
								    	<a style="color:#FFF;" href="/atencion-incidente/solicitud/nuevo">Nuevo</a>
								    </button>
								    <button id="btn_limpiarConsulta" type="button" class="btn btn-primary">Limpiar</button>
								    <button id="btn_buscarIncidente" type="button" class="btn btn-primary">Buscar</button>
								  </div>
								</div>
    						</form><!-- fin form-->
  						</div>
					</div>

    				<div>
    					<table id="solicitudes" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Nro. CTI</th>
                <th>Estado</th>
                <th>Tipo de Sol.</th>
                <th>Sistema</th>
                <th>Proceso</th>
                <th>Fecha de Reg.</th>
                <th>Prioridad</th>
                <th>Usuarios Afectados</th>
                <th>Nro. SS</th>
                <th>Solicitante</th>
                <th>Analista Funcional</th>
               <th>Opci&oacute;n</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Nro. CTI</th>
                <th>Estado</th>
                <th>Tipo de Sol.</th>
                <th>Sistema</th>
                <th>Proceso</th>
                <th>Fecha de Reg.</th>
                <th>Prioridad</th>
                <th>Usuarios Afectados</th>
                <th>Nro. SS</th>
                <th>Solicitante</th>
                <th>Analista Funcional</th>
                <th>Opci&oacute;n</th>
            </tr>
        </tfoot>
        <tbody>
            
          
        </tbody>
    </table>
    				</div>

    			</div>
  			</div>
		</div>

		<script type="text/javascript" language="javascript" src="/atencion-incidente/vendors/datatables/js/jquery.dataTables.js">
		</script>
		<script type="text/javascript" src="/atencion-incidente/vendors/js/moment.js"></script>
		<script src="/atencion-incidente/resources/js/main.js"></script>
 	</body>

 </html>