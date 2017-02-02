<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
	<head>
 		<title>Incidente</title>
 		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/bootstrap/css/bootstrap-datepicker.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendors/datatables/css/jquery.dataTables.css">
		<!-- Optional theme -->
		
 		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
 		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/jquery.min.js"></script>
 		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script>
 		<script src="${pageContext.request.contextPath}/vendors/bootstrap/js/bootstrap-datepicker.js"></script>

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
            		<li class="active"><a href="${pageContext.request.contextPath}/incidente/actualizarbc" style="padding-left: 30px" > <span class="glyphicon glyphicon-folder-close"></span> Actualizar Incidentes BC.</a></li>
				</ul>
  			</div>
  			<div id="content">
    			<div class="container-fluid">
    				<div class="panel panel-default">
  						<div class="panel-heading">
    						<h3 class="panel-title">Actualizar Base de Conocimiento de Incidentes</h3>
  						</div>
  						<div class="panel-body">
    						<form>
    							<div class="row">
    								<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Tipo Solicitud:</label>
    											<div class="col-sm-7">
      												<form:select path="tiposSolicitud" id="tipoSolicitud" cssClass="form-control" required="true" >
												    	<form:option value="" label="Seleccionar" />
    													<form:options items="${tiposSolicitud}" />
													</form:select>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Sistema:</label>
												<div class="col-sm-7">
      												<form:select path="sistemas" id="sistema" cssClass="form-control" required="true" >
												    	<form:option value="" label="Seleccionar" />
    													<form:options items="${sistemas}" />
													</form:select>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Proceso:</label>
    											<div class="col-sm-7">
      												<select class="form-control" id="proceso">
                                  						<option value="">Seleccionar</option>
                              						</select>
    											</div>
  											</div>
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Sub Proceso:</label>
 												<div class="col-sm-7">
      												<select class="form-control" id="subProceso">
                                  						<option value="">Seleccionar</option>
                              						</select>
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								<div class="row">
								  <div class="pull-right" style="margin-right:10px;">
								    <button id="btn_buscarIncidente" type="button" class="btn btn-primary">Buscar</button>
								  </div>
								</div>
    						</form><!-- fin form-->
  						</div>
					</div>
					
					<div class="panel panel-default">
  						<div class="panel-body">
    						<form:form modelAttribute="incidente" >
    							<div class="row">
    								<div class="col-md-3">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="secuencia" class="col-sm-5 control-label">Secuencia:</label>
    											<div class="col-sm-5">
      												<form:input path="nuSecuencia" disabled="true" class="form-control" style="" />
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-5">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="txtDescripcion" class="col-sm-5 control-label">Descripción:</label>
    											<div class="col-sm-7">
      												<form:input path="txtDescripcion" disabled="false" class="form-control"/>
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-4">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<label for="inputtext3" class="col-sm-5 control-label">Ind. Sol. Usuario:</label>
    											<div class="col-sm-7">
      												<form:checkbox path="flgResolucion" value="S" />
    											</div>
  											</div>
  										</div>
									</div>
								</div><!-- fin row-->
								<div class="row">
								  <div class="pull-right" style="margin-right:10px;">
								    <button id="btnGuardar" type="button" class="btn btn-primary">Guardar</button>
								  </div>
								</div>
    						</form:form><!-- fin form-->
  						</div>
					</div>

    				<div>
	    				<table id="solicitudes" class="table table-striped table-bordered" cellspacing="0" width="100%">
					        <thead>
					            <tr>
					                <th>Secuencia</th>
					                <th>Descripción</th>
					                <th>Ind. Sol. Usuario.</th>
					                <th>Opci&oacute;n</th>
					            </tr>
					        </thead>
					        <tfoot>
					            <tr>
					                <th>Secuencia</th>
					                <th>Descripción</th>
					                <th>Ind. Sol. Usuario.</th>
					                <th width="230px;" >Opci&oacute;n</th>
					            </tr>
					        </tfoot>
					        <tbody>
					        </tbody>
					    </table>
    				</div>

    			</div>
  			</div>
		</div>

		<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/vendors/datatables/js/jquery.dataTables.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/moment.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/incidente.js"></script>
 	</body>

 </html>