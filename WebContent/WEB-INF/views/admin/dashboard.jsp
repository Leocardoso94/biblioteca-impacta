<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
		<h1>
			Dashboard <small></small>
		</h1>
		<!--
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
			<li class="active">Here</li>
		</ol>
		-->
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Info boxes -->
		<div class="row">
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-aqua"><i
						class="ion ion-ios-gear-outline"></i></span>

					<div class="info-box-content">
						<span class="info-box-text">Reservas</span> <span
							class="info-box-number">${contagemDeReservas}</span>
					</div>
					<!-- /.info-box-content -->
				</div>
				<!-- /.info-box -->
			</div>
			<!-- /.col -->
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-red"><i class="fa fa-book"></i></span>

					<div class="info-box-content">
						<span class="info-box-text">Exemplares</span> <span
							class="info-box-number">${contagemDeExemplares}</span>
					</div>
					<!-- /.info-box-content -->
				</div>
				<!-- /.info-box -->
			</div>
			<!-- /.col -->

			<!-- fix for small devices only -->
			<div class="clearfix visible-sm-block"></div>

			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-green"><i
						class="ion ion-ios-cart-outline"></i></span>

					<div class="info-box-content">
						<span class="info-box-text">Empréstimos</span> <span
							class="info-box-number">${contagemDeEmprestimos}</span>
					</div>
					<!-- /.info-box-content -->
				</div>
				<!-- /.info-box -->
			</div>
			<!-- /.col -->
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-yellow"><i
						class="ion ion-ios-people-outline"></i></span>

					<div class="info-box-content">
						<span class="info-box-text">Membros</span> <span
							class="info-box-number">${contagemDePessoas}</span>
					</div>
					<!-- /.info-box-content -->
				</div>
				<!-- /.info-box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
		<!-- Your Page Content Here -->

		<!-- TABLE: LATEST ORDERS -->
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">Últimos empréstimos</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<div class="table-responsive">
					<table class="table no-margin">
						<thead>
							<tr>
								<th>Número do empréstimo</th>
								<th>Obra</th>
								<th>Status</th>
								<th>Usuário</th>
								<th>Data da devolução</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${emprestimos}" var="emprestimo">
								<tr>
									<td><a href="pages/examples/invoice.html">${emprestimo[0]}</a></td>
									<td>${emprestimo[1]}</td>


									<c:choose>
										<c:when test="${emprestimo[2] == 'Atraso'}">
											<td><span class="label label-danger">${emprestimo[2]}</span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-success">${emprestimo[2]}</span></td>
										</c:otherwise>
									</c:choose>

									<td>${emprestimo[3]}</td>
									<td>
										<div class="sparkbar" data-color="#00a65a" data-height="20">${emprestimo[4]}</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.box-body -->
			<div class="box-footer clearfix">
				<a href="javascript:void(0)"
					class="btn btn-sm btn-info btn-flat pull-left">Novo empréstimo</a>
				<a href="javascript:void(0)"
					class="btn btn-sm btn-default btn-flat pull-right">Consultar
					todos os empréstimos</a>
			</div>
			<!-- /.box-footer -->
		</div>
		<!-- /.box -->

		<!-- quick email widget -->
		<div class="box box-info">
			<div class="box-header">
				<i class="fa fa-envelope"></i>

				<h3 class="box-title">Email rápido</h3>
				<!-- tools box -->
				<div class="pull-right box-tools">
					<button type="button" class="btn btn-info btn-sm"
						data-widget="remove" data-toggle="tooltip" title="Remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
				<!-- /. tools -->
			</div>
			<div class="box-body">
				<form action="#" method="post">
					<div class="form-group">
						<input type="email" class="form-control" name="emailto"
							placeholder="Email to:">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="subject"
							placeholder="Subject">
					</div>
					<div>
						<textarea class="textarea" placeholder="Message"
							style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
					</div>
				</form>
			</div>
			<div class="box-footer clearfix">
				<button type="button" class="pull-right btn btn-default"
					id="sendEmail">
					Enviar <i class="fa fa-arrow-circle-right"></i>
				</button>
			</div>
		</div>


	</section>
	<!-- /.content -->