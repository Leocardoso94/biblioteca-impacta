<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section class="content-header">
	<h1>
		Reservas <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Reservas</h3>
					<button class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#inserir${reserva.idreserva}">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
					<hr>
					<form action="buscaReserva" method="get" class="">
						<div class="input-group">
							<input type="text" name="search" class="form-control"
								placeholder="Buscar..."> <span class="input-group-btn">
								<button type="submit" id="search-btn" class="btn btn-flat">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>

				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">#</th>
							<th>Exemplar</th>
							<th>Usuário</th>
							<th>Data da Reserva</th>
							<th>Data da Retirada</th>
							<th>Cancelar Reserva</th>
						</tr>
						<c:forEach items="${reservas}" var="reserva">
							<tr>
								<td>${reserva.idreserva}</td>
								<td><a
									href="<c:url value="/admin/buscaExemplar?search=${reserva.num_exemplar}"/>">#${reserva.num_exemplar}
										- ${reserva.getNomeExemplar()}</a></td>
								<td><a
									href="<c:url value="/admin/buscaPessoa?search=${reserva.idpessoa}"/>">#${reserva.idpessoa}
										- ${reserva.getNomePessoa()}</a></td>
								<td><fmt:formatDate value="${reserva.data_reserva}"
										type="date" dateStyle="long" /></td>
								<td><fmt:formatDate value="${reserva.data_retirada}"
										type="date" dateStyle="long" /></td>
							</tr>

							<div class="modal fade modal-success"
								id="modal${reserva.idreserva}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Devolver</h4>
										</div>
										<form role="form" method="post" class="form-horizontal"
											action="devolver">
											<div class="modal-body">

												<input value="${reserva.idreserva}" name="idreserva"
													type="hidden" readonly>
												<div class="box-body">
													<div class="form-group">
														<p>
															Tem certeza que deseja efetuar a devolução deste
															exemplar?<br>Após isso o processo não poderá ser
															desfeito.<br> <br>Lembre-se o usuário deve
															pagar de multa.
														</p>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Cancelar</button>
												<button type="submit" class="btn btn-outline">Devolver</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</table>
				</div>


				<!-- Modal -->
				<div class="modal fade modal-primary"
					id="inserir${reserva.idreserva}" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Inserir</h4>
							</div>
							<form role="form" method="post" class="form-horizontal"
								action="adicionarReserva">
								<div class="modal-body">
									<div class="box-body">
										<div class="form-group">
											<label for="inputExemplar" class="col-sm-2 control-label">Exemplar</label>
											<div class="col-sm-10">
												<select id="inputExemplar" class="form-control"
													name="num_exemplar" required>
													<option></option>
													<c:forEach items="${exemplares}" var="exemplar">
														<option value="${exemplar.num_exemplar}">#${exemplar.num_exemplar}
															- ${exemplar.nomeobra}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputUsuario" class="col-sm-2 control-label">Usuário</label>
											<div class="col-sm-10">
												<select id="inputUsuario" class="form-control"
													name="idpessoa" required>
													<option></option>
													<c:forEach items="${pessoas}" var="pessoa">
														<option value="${pessoa.idpessoa}">#${pessoa.idpessoa}
															- ${pessoa.nome}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputSenha" class="col-sm-6 control-label">O
												usuário deve digitar a senha de acesso:</label>
											<div class="col-sm-6">
												<input type="password" name="senha" placeholder="Senha"
													class="form-control" id="inputSenha">
											</div>
										</div>
										<div class="form-group">
											<button type="button" class="btn btn-danger pull-right"
												onclick="checaSenha()">Validar usuário</button>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancelar</button>
									<button type="submit" disabled id="btnReserva"
										class="btn btn-outline">Inserir</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<ul class="pagination pagination-sm no-margin pull-right">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
			<!-- /.box -->
		</div>

	</div>
</section>