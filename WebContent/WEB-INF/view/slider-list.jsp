<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product list</title>
</head>
<body>

Product list

<div id="wrapper">
   <h2>MY SHOP - Products</h2>
</div>
  <br><br>
  
<div id="data_container">
   <table>
     <tr>
       <th>ID</th>
       <th>TITLE</th>
       <th>DESCRIPTION</th> 
       <th>PRICE</th>
       <th>QUANTITY</th>
       <th>IMAGE</th>
       <th>CATEGORY</th>
     </tr>
     
     <c:forEach var= "product" items="${products}">
       <tr>
       <th>${product.id}</th>
       <th>${product.title}</th>
       <th>${product.description}</th>
       <th>${product.price}</th>
       <th>${product.quantity}</th>
       <th>${product.image}</th>
       <th>${product.category.name}</th>
       <th> <a href="product-update?id=${product.id}">Update</a> <th/> 
       <th> <a href="product-delete?id=${product.id}" onclick="if(!(confirm('Do you want to delete this product')))return false">Delete</a> <th/>
     </tr>  
     </c:forEach>
   </table>
</div>
<br><br><br>
<a href="product-form">Create product</a>

</body>
</html-->

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>Cubes school - Restaurant project</title>

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/jquery-ui/jquery-ui.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/jquery-ui/jquery-ui.theme.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">


 <jsp:include page="include-admin-header-profile.jsp"/>  

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Product Logo -->
   

    <!-- Sidebar Menu -->
    
        <jsp:include page="include-admin-menu.jsp"/>
        
    <!-- /.sidebar -->
      <!-- /.sidebar-menu -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Sliders</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">All Sliders</h3>
                <div class="card-tools">
                  <a href="slider-form" class="btn btn-success">
                    <i class="fas fa-plus-square"></i>
                    Add new Slider
                  </a>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>                  
                    <tr>
                      <th class="text-center">ID</th>
                      <th class="text-center">Title</th>
                      <th class="text-center">description</th>
                      <th class="text-center">URL</th>
                      <th class="text-center">Image</th>
                      <th class="text-center">Button Title</th>
                      <th class="text-center">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  
                  <c:forEach var="slider" items="${sliders}">
                    <tr>
                      <td>
                      ${slider.id}
                      </td>
                      <td>
                      ${slider.title}
                      </td>
                      <td>
                       ${slider.description}
                      </td>
                      <td>
                      <a href="${slider.url}">URL</a>
                      </td>
                       <td>
                      ${slider.buttonTitle}
                      </td>
                      <td class="text-center">
                        <img src="${product.image}" style="max-width: 80px;">
                      </td>
                    
                      
                      <td class="text-center">
                        <div class="btn-group">
                          <a href="slider-update?id=${slider.id}" class="btn btn-info">
                            <i class="fas fa-edit"></i>
                          </a>
                          <a href="slider-delete?id=${slider.id}">
                          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete-modal">
                            <i class="fas fa-trash"></i>
                          </button>
                          </a>
                        </div>
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                
              </div>
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->

    <div class="modal fade" id="delete-modal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Delete Product</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete product?</p>
            <strong></strong>
          </div>
          <div class="modal-footer justify-content-between">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-danger">Delete</button>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Java
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/admin/dist/js/adminlte.min.js"></script>
</body>
</html>
