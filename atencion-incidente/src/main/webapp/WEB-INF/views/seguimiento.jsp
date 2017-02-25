<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
	<head>
 		<title>Actualizar Base de Conocimiento de Incidentes</title>
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
            		<li ><a href="${pageContext.request.contextPath}/incidente" style="padding-left: 30px" > <span class="glyphicon glyphicon-folder-close"></span> Actualizar Incidentes BC.</a></li>
            		<li class="active"><a href="${pageContext.request.contextPath}/seguimiento" style="padding-left: 30px" > <span class="glyphicon glyphicon-folder-close"></span> Seguimiento</a></li>
				</ul>
  			</div>
  			<div id="content">
    			<div class="container-fluid">
    				<div class="panel panel-default">
  						<div class="panel-heading">
    						<h3 class="panel-title">Seguimiento de Cartera CTI</h3>
  						</div>
  						<div class="panel-body" style="height: 140px;">
    						<form>
    							<div class="row" style="height: 50px;">
    								<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-9">
    												<label for="analista" class="control-label">Analista Funcional:</label>
      												<form:select path="analistaList" id="idAnalista" cssClass="form-control" required="true" >
												    	<form:option value="0" label="Seleccionar" />
    													<form:options items="${analistaList}" />
													</form:select>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-9">
    												<label for="fecha_ini" class="control-label">Fecha de Inicio:</label>
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
    											<div class="col-sm-9">
    												<label for="fecha_fin" class="control-label">Fecha Fin:</label>
      												<div class="input-group date">
    													<input id="fecha_fin" type="text" class="form-control datepicker1" >
    													<div class="input-group-addon">
        													<span class="glyphicon glyphicon-th"></span>
    													</div>
													</div>
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								<div class="row">
								  <div class="pull-right" style="margin-right:10px;">
								    <button id="btn_generar" type="button" class="btn btn-primary">Generar</button>
								  </div>
								</div>
    						</form><!-- fin form-->
  						</div>
					</div>
					
					<div class="panel panel-default">
  						<div class="panel-body">
 	 							<p style="vertical-align: top; color: #FF4C01;" >
    							Fecha de Corte: 01/01/2017 - 15/02/2017
    							</p>
    							<div class="row">
    								<div class="col-md-5">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-12" style="text-align: center;">
    												<label for="tb_carteraAF" style="vertical-align: top; color: #FF4C01;">Cartera de Analistas Funcionales</label>
      												
      												<table id="tb_carteraAF" class="table table-bordered table-striped" style="text-align: center;">
													    <thead>
													      <tr>
													        <th>Analista Funcional</th>
													        <th style="width: 85px;">En Proceso de Atención</th>
													        <th style="width: 60px;">Asignados</th>
													        <th style="width: 60px;">Total</th>
													      </tr>
													    </thead>
													    <tbody>
													    </tbody>
													  </table>
      												
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-7">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-14" style="text-align: center;">
	    											<label for="cnv_carteraCTI" style="vertical-align: top; color: #FF4C01;" >Cartera de CTI</label>
      												<br />
      												<canvas id="cnv_carteraCTI" ></canvas>
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								
    							<div class="row" style="overflow: auto; margin: 5px;" >
									<div class="col-md-14">
    									<div class="form-horizontal">
  											<div class="form-group" style="margin-right: 0px;margin-left: 0px;">
    											<div class="col-sm-14" style="text-align: center;">
	    											<label for="cnv_demandaOferta" style="vertical-align: top; color: #FF4C01;" >Demanda vs Oferta</label>
      												<br />
      												<canvas id="cnv_demandaOferta" ></canvas>
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								
  						</div>
					</div>
    				
    			</div>
  			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/datatables/js/jquery.dataTables.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/moment.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/chartjs/js/Chart.bundle.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/seguimiento.js"></script>
 	</body>

 </html>