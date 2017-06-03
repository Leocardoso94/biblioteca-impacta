<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>BibliotecaFIT</title>
<c:url value="/resources/admin" var="adminPath" />
<c:url value="/resources/css" var="cssPath" />
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="${adminPath}/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${cssPath}/font-awesome.min.css">
<!-- Style -->
<link rel="stylesheet" href="${cssPath}/style.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${adminPath}/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet"
	href="${adminPath}/dist/css/skins/skin-blue.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a href="<c:url value="/admin"/>" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>FIT</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>Biblioteca</b>FIT</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">

						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<img src="${adminPath}/dist/img/user2-160x160.jpg"
								class="user-image" alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs">${pessoa.nome}</span>
						</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img
									src="${adminPath}/dist/img/user2-160x160.jpg"
									class="img-circle" alt="User Image">

									<p>
										${pessoa.nome} - ${pessoa.tipopessoa} <small>Membro desde
											<fmt:formatDate value="${pessoa.data_registro.time}" type="date" dateStyle="long" /></small>
									</p></li>								
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="<c:url value="/admin/buscaPessoa?search=${pessoa.idpessoa}"/>" class="btn btn-default btn-flat">Informações</a>
									</div>
									<div class="pull-right">
										<a href="<c:url value="/logout"/>"
											class="btn btn-default btn-flat">Sair</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${adminPath}/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${pessoa.nome}</p>
						<!-- Status -->
						<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>

				<!-- search form (Optional) -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- /.search form -->

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu">
					<li class="header">HEADER</li>
					<!-- Optionally, you can add icons to the links -->
					<li class="active"><a href="<c:url value="/admin"/>"><i
							class="fa fa-link"></i> <span>Dashboard</span></a></li>
					<li class=""><a href="<c:url value="/admin/emprestimo"/>"><i
							class="fa fa-link"></i> <span>Empréstimos</span></a></li>
					<li class=""><a href="<c:url value="/admin/reserva"/>"><i
							class="fa fa-link"></i> <span>Reservas</span></a></li>
					<li class="treeview"><a href="#"><i class="fa fa-book"></i>
							<span>Acervo</span> <span class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span> </a>
						<ul class="treeview-menu">
							<li><a href="<c:url value="/admin/obra"/>">Obras</a></li>
							<li><a href="<c:url value="/admin/exemplar"/>">Exemplares</a></li>
							<li><a href="<c:url value="/admin/assunto"/>">Assuntos</a></li>
						</ul></li>
					<li class=""><a href="<c:url value="/admin/pessoa"/>"><i
							class="fa fa-user"></i> <span>Usuarios</span></a></li>
					<li class=""><a href="<c:url value="/admin/autor"/>"><i
							class="fa fa-male"></i> <span>Autores</span></a></li>
					<li class=""><a href="<c:url value="/admin/editora"/>"><i
							class="fa fa-industry"></i> <span>Editoras</span></a></li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>