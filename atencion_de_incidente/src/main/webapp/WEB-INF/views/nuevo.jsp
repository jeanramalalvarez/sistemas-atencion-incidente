<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<title>Registrar Solicitud</title>
<!-- Latest compiled and minified CSS -->
<meta charset='utf-8'>
<link rel="stylesheet"
	href="/atencion-incidente/vendors/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="/atencion-incidente/vendors/bootstrap/css/bootstrap-datepicker.css" />

<link rel="stylesheet" href="/atencion-incidente/resources/css/main.css" />
<script type="text/javascript"
	src="/atencion-incidente/vendors/js/jquery.min.js"></script>
<script src="/atencion-incidente/vendors/bootstrap/js/bootstrap.min.js"></script>
<script
	src="/atencion-incidente/vendors/bootstrap/js/bootstrap-datepicker.js"></script>

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
			<p class="txt-oge text-right">Usuario SI2921 - Javier Huaman</p>
		</div>
	</header>

	<div class="wrap">
		<div class="pull-left" id="menu">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="#" class="menu"><span
						class="glyphicon glyphicon-folder-close"></span> Administracion</a></li>
				<li><i class="glyphicon glyphicon-triangle-top arrow"></i> <a
					href="#" class="menu"> <span
						class="glyphicon glyphicon-folder-close"></span> Sol. de Atencion
						de Incidente
				</a></li>
				<li class="active"><a
					href="/atencion-incidente/solicitud/consulta"
					style="padding-left: 30px"> Consulta de Sol. CTI </a></li>
				<li><a href="#" class="menu"><span
						class="glyphicon glyphicon-folder-close"></span> Sol. de Servicio</a></li>
				<li><a href="#" class="menu"> <span
						class="glyphicon glyphicon-folder-close"></span> Sol. de Atencion
						de Req.
				</a></li>
			</ul>
		</div>
		<div id="content">
			<div class="container-fluid">

				<h3>Registrar Solicitud de Atención de Incidente</h3>

				<div class="row">

					<div class="col-md-6">
						<form:form modelAttribute="solicitante">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Datos del Solicitante</h3>
								</div>
								<!-- panel-heading -->
								<div class="panel-body" style="min-height: 282px;">

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label for="matricula">Matricula:</label>
												<form:input path="matricula" disabled="true"
													class="form-control" />
											</div>
										</div>
										<div class="col-md-8">
											<div class="form-group">
												<label for="nomApe">Nombres y Apellidos:</label>
												<form:input path="nombresApellidos" disabled="true"
													class="form-control" />
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label for="area">&Aacute;rea:</label>
												<form:input path="txtArea" disabled="true"
													class="form-control" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label for="cargo">Cargo:</label>
												<form:input path="txtCargo" disabled="true"
													class="form-control" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label for="anexo">Anexo:</label>
												<form:input path="anexo" disabled="true"
													class="form-control" />
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-8">
											<div class="form-group">
												<label for="agencia">Agencia:</label>
												<form:input path="txtAgencia" disabled="true"
													class="form-control" />
											</div>
										</div>
									</div>
									<!-- row -->

								</div>
								<!-- panel-body -->
							</div>
							<!-- panel-default -->

						</form:form>

					</div>
					<!-- col-md-6 -->

					<div class="col-md-6">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Datos de Solicitud</h3>
							</div>
							<!-- panel-heading -->
							<form id="form-datos">
								<div class="panel-body">

									<div class="row">
										<div class="col-md-8">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="tipoSolicitud" class="col-sm-4 control-label">Tipo
														Solicitud:</label>
													<div class="col-sm-8">
														<form:select path="tipoSolicitud" id="tipoSolicitud"
															name="tipoSolicitud" cssClass="form-control"
															required="true">
															<form:option value="" label="" />
															<form:options items="${tipoSolicitud}" />
														</form:select>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-horizontal">
												<div class="form-group">

													<div class="col-sm-8">
														<input type="hidden" id="nrocti" name="nrocti"
															class="form-control" readonly>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-8">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="sistema" class="col-sm-4 control-label">Sistema:</label>
													<div class="col-sm-8">
														<select id="sistema" name="sistema" class="form-control">
															<option value="">Seleccionar</option>

														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->


									<div class="row">
										<div class="col-md-8">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="proceso" class="col-sm-4 control-label">Proceso:</label>
													<div class="col-sm-8">
														<select class="form-control" id="proceso" name="proceso">
															<option value=""></option>

														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->
									<input type="hidden" id="descripcionSol" name="descripcionSol" class="form-control">
									<div class="row">
										<div class="col-md-8">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="subProceso" class="col-sm-4 control-label">Sub
														Proceso:</label>
													<div class="col-sm-8">
														<select class="form-control" id="subProceso"
															name="subProceso">
															<option value=""></option>

														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-8">
											<div class="form-horizontal">
												<div class="form-group">
													<label for="nroUsAfec" class="col-sm-6 control-label">Nro.
														Usuarios Afect. :</label>
													<div class="col-sm-6">
														<select id="nroUsAfec" name="nroUsAfec"
															class="form-control">
															<option value="">Seleccionar</option>

														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->

								</div>
								<!-- panel-body -->
							</form>

						</div>
						<!-- panel-default -->

					</div>
					<!-- col-md-6 -->

				</div>
				<!-- row principal-->

				<div class="row" id="view_tipo_solicitud">
					<!-- ini row dynamic-->

					<div class="col-md-12 panel-dinamico ">
						<form id="form-tipo-1-2" enctype="multipart/form-data"
							method="post" accept-charset="utf-8">

							<input type="hidden" class="idSolicitud_tipo1 idSolicitud_tipo2"
								name="idSolicitud" class="form-control"> <input
								type="hidden" class="usAfectados_tipo1 usAfectados_tipo2"
								name="usAfectados" class="form-control">

							<div class="panel panel-default tipo1 tipo2"
								style="display: none">
								<div class="panel-heading">
									<h3 class="panel-title">Detalle Solicitud</h3>
								</div>
								<!-- panel-heading -->
								<div class="panel-body">

									<div class="row">
										<input type="hidden" id="asunto" name="asunto" class="form-control">
										<div class="col-md-4">
											<div class="form-group">
												<div class="col-sm-8">
													<div class="checkbox">
														<label> <input type="checkbox" name="afectaIdi"
															id="afectaIdi" value="1"> Afecta IDI
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="descripcion" class="control-label">Descripcion:</label>
												<textarea id="descripcion" name="descripcion"
													class="form-control" rows="6"></textarea>
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-6">
												<input type="hidden" id="error" name="error" class="form-control">
												<div class="form-group field-ts-2">
													<label for="datosModif" class="col-sm-4 control-label">Datos
														a Modificar:</label>
													<div class="col-sm-8">
														<input type="text" id="datosModif" name="datosModif"
															class="form-control">
													</div>
												</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-6">
												<div class="form-group">
													<div class="col-md-1">
													<label for="nrocti" class="control-label">Ruta:</label>
													</div>
													<div class="col-md-8">
														<input type="file" name="file"
															class="form-control active-file-tipo1 active-file-tipo2">
													</div>
													<div class="col-md-1">
														<a class="btn btn-default btnAdjuntar">Adjuntar</a>
													</div>
												</div>
										</div>

									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-6">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>Nro</th>
														<th>Nombre Archivo</th>
														<th>Fecha de Carga</th>
														<th>Opcion</th>
													</tr>
												</thead>
												<tbody class="tb-files-tipo1 tb-files-tipo2">
													<tr>
														<td></td>
														<td></td>
														<td></td>
													</tr>
												</tbody>

											</table>
										</div>
									</div>
									<!-- row -->

								</div>
								<!-- panel-body -->
							</div>
							<!-- panel-default -->
						</form>

						<form id="form-tipo-3" enctype="multipart/form-data" method="post"
							accept-charset="utf-8">

							<input type="hidden" class="idSolicitud_tipo3" name="idSolicitud"
								class="form-control"> <input type="hidden"
								class="usAfectados_tipo3" name="usAfectados"
								class="form-control">

							<div id="form-tipo-3" class="panel panel-default tipo3"
								style="display: none">
								<div class="panel-heading">
									<h3 class="panel-title">Datos de Extraccion</h3>
								</div>
								<!-- panel-heading -->
								<div class="panel-body">

									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<div class="col-sm-8">
													<div class="checkbox">
														<label> <input type="checkbox" name="regulatorio"
															id="regulatorio" value="1">Regulatorio
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- row -->

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label for="resumen" class="control-label">Resumen:</label>
												<input type="text" id="resumen" name="resumen"
													class="form-control" />
											</div>

											<div class="form-group">
												<label for="descripcion2" class="control-label">Descripcion:</label>
												<textarea id="descripcion2" name="descripcion2"
													class="form-control" rows="4"></textarea>
											</div>

											<div class="form-group">
												<label for=volumenes class="control-label">Validaciones
													a Considerar:</label>
												<textarea id="volumenes" name="volumenes"
													class="form-control" rows="5"></textarea>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group">
												<label for="fechasCorte" class="control-label">Fechas
													de Corte:</label> <input type="text" id="fechasCorte"
													name="fechasCorte" class="form-control" />
											</div>

											<div class="form-group">
												<label for="estructuraReporte" class="control-label">Estructura
													de Reporte:</label>
												<textarea id="estructuraReporte" name="estructuraReporte"
													class="form-control" rows="5"></textarea>
											</div>

											<div class="row">
												<div class="col-md-6">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="nrocti" class="col-sm-4 control-label">Ruta:</label>
															<div class="col-sm-8">
																<input type="file" name="file2"
																	class="form-control active-file-tipo3">
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-2">
													<a class="btn btn-default btnAdjuntar">Adjuntar</a>
												</div>
											</div>
											<!-- row -->

											<div class="row">
												<div class="col-md-12">
													<table class="table table-bordered">
														<thead>
															<tr>
																<th>Nro</th>
																<th>Nombre Archivo</th>
																<th>Fecha de Carga</th>
																<th>Opcion</th>
															</tr>
														</thead>
														<tbody class="tb-files-tipo3">
															<tr>
																<td></td>
																<td></td>
																<td></td>
															</tr>
														</tbody>

													</table>
												</div>
											</div>
											<!-- row -->


										</div>
									</div>
									<!-- row -->





									<div class="row"></div>
									<!-- row -->

								</div>
								<!-- panel-body -->
							</div>
							<!-- panel-default -->
						</form>

					</div>
					<!-- fin panel-dinamico-->

				</div>
				<!-- fin row dynamic-->

				<div class="row" id="buttons">
					<div class="col-md-4 col-md-offset-8">
						<div class="pull-right">
							<button class="btn btn-primary" id="btn_guardar">Guardar</button>
							<button class="btn btn-primary">
								<a style="color: #FFF;"
									href="/atencion-incidente/solicitud/consulta">Cancelar</a>
							</button>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>

	<!-- Modal -->
	<div class="modal fade" id="show-nroCti" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-body">

					<h4>
						Nro. Sol. : <span id="mNroCti"></span>
					</h4>
					<br />

					<div class="modal-footer">
						<button class="btn btn-primary">
							<a style="color: #FFF;"
								href="/atencion-incidente/solicitud/consulta">Aceptar</a>
						</button>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="/atencion-incidente/resources/js/nuevo.js"></script>
	<script type="text/javascript"
		src="/atencion-incidente/vendors/js/jquery.form.js"></script>
	<script type="text/javascript"
		src="/atencion-incidente/vendors/js/moment.js"></script>
</body>

</html>