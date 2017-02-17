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
    							<form:hidden path="idIncidenteBase" id="idIncidenteBase" />
    							<div class="row">
    								<div class="col-md-2">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-8">
    												<label for="secuencia">Secuencia:</label>
      												<form:input path="nuSecuencia" id="txtSecuencia" disabled="true" class="form-control" style="" />
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-7">
    									<div class="form-horizontal">
  											<div class="form-group">
    											<div class="col-sm-14">
	    											<label for="txtDescripcion">Descripción:</label>
      												<form:textarea path="txtDescripcion" id="txtDescripcion" disabled="false" class="form-control" style="resize: none;" />
    											</div>
  											</div>
  										</div>
									</div>
									<div class="col-md-2">
    									<div class="form-horizontal">
  											<div class="form-group" style="height: 10px;" >
    											<div class="col-sm-1">
    											</div>
  											</div>
  											<div class="form-group">
    											<div class="col-sm-1">
      												<form:checkbox path="flgResolucion" id="flgResolucion"  value="S" />
    											</div>
    											<label for="inputtext3">Ind. Sol. Usuario:</label>
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
    						</form:form><!-- fin form-->
  						</div>
					</div>

    				<div>
	    				<table id="incidentes" class="table table-striped table-bordered" cellspacing="0" width="100%">
					        <thead>
					            <tr>
					                <th style="width: 70px;">Secuencia</th>
					                <th>Descripción</th>
					                <th style="width: 100px;">Ind. Sol. Usuario.</th>
					                <th style="width: 230px;">Opci&oacute;n</th>
					            </tr>
					        </thead>
					        <tfoot>
					            <tr>
					                <th style="width: 70px;">Secuencia</th>
					                <th>Descripción</th>
					                <th style="width: 100px;">Ind. Sol. Usuario.</th>
					                <th style="width: 230px;">Opci&oacute;n</th>
					            </tr>
					        </tfoot>
					        <tbody>
					        </tbody>
					    </table>
    				</div>
    				
					<div id="contentSolucion" class="panel panel-default" style="margin-top: 20px; display: none;" >
  						<div class="panel-body">
							<ul class="nav nav-tabs" id="tabIncidente" role="tabIncidente" >
							  <li role="presentation" class="active"><a href="#tabSolucionesIncidente" id="solucionesIncidente-tab" role="tab" data-toggle="tab" aria-controls="1" aria-expanded="true" >Soluciones del Incidente</a></li>
							  <li role="presentation"><a href="#tabSolucion" id="solucion-tab" role="tab" data-toggle="tab" aria-controls="2" aria-expanded="false" >Solución</a></li>
							  <li role="presentation"><a href="#tabValidacion" id="validacion-tab" role="tab" data-toggle="tab" aria-controls="3" aria-expanded="false" >Validaciones</a></li>
							</ul>
							<div class="tab-content">
							    <div role="tabpanel" class="tab-pane active" id="tabSolucionesIncidente" style="margin-top: 15px;" >
									<input type="hidden" id="idSolucion" />
									<div class="panel-body">
								    	<div class="row">
		    								<div class="col-md-2">
		    									<div class="form-horizontal">
		  											<div class="form-group">
		    											<div class="col-sm-9">
		    												<label for="txtNroSolucion">Nro Solución:</label>
		      												<input id="txtNroSolucion" disabled="disabled" class="form-control" style="" />
		      												
		      												<label for="txtNroSolucion">Prioridad:</label>
		      												<input id="txtPrioridad" class="form-control" style="" />
		    											</div>
		  											</div>
		  										</div>
											</div>
											<div class="col-md-8">
		    									<div class="form-horizontal">
		  											<div class="form-group">
		    											<div class="col-sm-14">
		    											<label for="txtDescripcion">Descripción:</label>
		      												<textarea id="txtDescripcionSolucion" class="form-control" style="resize: none;"></textarea>
		    											</div>
		  											</div>
		  										</div>
											</div>
										</div>
										<div class="row">
										  <div class="pull-right" style="margin-right:10px;">
										  	<button id="btn_limpiar_solucion" type="button" class="btn btn-primary">Limpiar</button>
										    <button id="btn_guardar_solucion" type="button" class="btn btn-primary">Guardar</button>
										  </div>
										</div>
					    				<div>
						    				<table id="soluciones" class="table table-striped table-bordered" cellspacing="0" width="100%" style="margin-top: 15px;" >
										        <thead>
										            <tr>
										                <th style="width: 80px;" >Nro. Solución</th>
										                <th>Descripción</th>
										                <th style="width: 40px;">Prioridad</th>
										                <th style="width: 60px;">Cant. Uso</th>
										                <th style="width: 160px;" >Opci&oacute;n</th>
										            </tr>
										        </thead>
										        <tfoot>
										            <tr>
										                <th style="width: 80px;" >Nro. Solución</th>
										                <th>Descripción</th>
										                <th style="width: 40px;">Prioridad</th>
										                <th style="width: 60px;">Cant. Uso</th>
										                <th style="width: 160px;" >Opci&oacute;n</th>
										            </tr>
										        </tfoot>
										        <tbody>
										        </tbody>
										    </table>
					    				</div>
							    	</div>	
							    </div>
							    <div role="tabpanel" class="tab-pane" id="tabSolucion">
							    	<input type="hidden" id="idSolucionTmp" />
							    	<input type="hidden" id="numSecuenciaSolucionSetup" />
									<div class="panel-body">
		    							<div class="row">
		    								<div class="col-md-10">
		    									<div class="form-horizontal">
		  											<div class="form-group">
		    											<label for="tipoSolucion" class="col-sm-2 control-label">Tipo Solucion:</label>
		    											<div class="col-sm-3">
		      												<select class="form-control" id="tipoSolucion">
		                                  						<option value="">Seleccionar</option>
		                                  						<option value="C">Considereciones</option>
		                                  						<option value="S">Actualizaciones</option>
		                              						</select>
		    											</div>
		  											</div>
		  											<div class="form-group">
		    											<label for="txtGlosa" class="col-sm-2 control-label">Glosa:</label>
														<div class="col-sm-7">
		      												<input type="text" id="txtGlosa" class="form-control" style="resize: none;" />
		    											</div>
		  											</div>
		  											<div class="form-group">
		    											<label for="txtGlosa" class="col-sm-2 control-label">Sustento:</label>
														<div class="col-sm-7">
		      												<input type="text" id="txtSustento" class="form-control" style="resize: none;" />
		    											</div>
		  											</div>
		  											<div class="form-group">
		    											<label for="txtDescripcionSolucionSetup" class="col-sm-2 control-label">Descripción:</label>
														<div class="col-sm-7">
		      												<textarea id="txtDescripcionSolucionSetup" class="form-control" style="resize: none;"></textarea>
		    											</div>
		  											</div>
		  											<div class="form-group">
		    											<label for="txtRuta" class="col-sm-2 control-label">Ruta:</label>
														<div class="col-sm-7">
		      												<input id="txtRuta" type="file" name="txtRuta" class="filestyle" data-buttonText="Examinar"  />
		    											</div>
		  											</div>
		  										</div>
											</div>
										</div>
										<div class="row">
										  <div class="pull-right" style="margin-right:10px;">
										  	<button id="btn_limpiar_solucion_setup" type="button" class="btn btn-primary">Limpiar</button>
										    <button id="btn_guardar_solucion_setup" type="button" class="btn btn-primary">Guardar</button>
										  </div>
										</div>
					    				<div>
						    				<table id="solucionesSetup" class="table table-striped table-bordered" cellspacing="0" width="100%" style="margin-top: 15px;" >
										        <thead>
										            <tr>
										                <th style="width: 50px;" >Nro</th>
										                <th style="width: 70px;" >Tipo Sol.</th>
										                <th>Glosa</th>
										                <th>Sustento</th>
										                <th>Descripción</th>
										                <th style="width: 100px;">Anexo</th>
										                <th style="width: 170px;">Opci&oacute;n</th>
										            </tr>
										        </thead>
										        <tfoot>
										            <tr>
										                <th style="width: 50px;" >Nro</th>
										                <th style="width: 70px;" >Tipo Sol.</th>
										                <th>Glosa</th>
										                <th>Sustento</th>
										                <th>Descripción</th>
										                <th style="width: 100px;">Anexo</th>
										                <th style="width: 170px;">Opci&oacute;n</th>
										            </tr>
										        </tfoot>
										        <tbody>
										        </tbody>
										    </table>
					    				</div>
			  						</div>
							    
							    </div>
							    <div role="tabpanel" class="tab-pane" id="tabValidacion">Validaciones</div>
							 </div>
						</div>
					</div>
    			</div>
  			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/datatables/js/jquery.dataTables.js">
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/js/moment.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/incidente.js"></script>
 	</body>

 </html>