<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.medical.model.entities.*" %>

<html>
<head>
<title>Equipment</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.card-custom{border-radius:15px; transition:0.3s;}
.card-custom:hover{transform:scale(1.05);}
</style>

</head>

<body class="bg-light">

<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">

<h2>Equipment Management</h2>

<!-- CARDS -->
<div class="row mb-4">
<div class="col-md-4">
<div class="card card-custom bg-primary text-white p-3 text-center">
<h4><%= request.getAttribute("equipmentList")!=null ? ((List)request.getAttribute("equipmentList")).size() : 0 %></h4>
<p>Total Equipment</p>
</div>
</div>
</div>

<!-- ADD -->
<form action="equipment" method="post" class="row g-3 mb-4">

<input name="id" placeholder="ID" class="form-control">
<input name="name" placeholder="Name" class="form-control">
<input name="model" placeholder="Model" class="form-control">
<input name="price" placeholder="Price" class="form-control">
<input name="interval" placeholder="Maintenance Interval" class="form-control">

<button class="btn btn-success">Add</button>

</form>

<input id="searchInput" class="form-control mb-3" placeholder="Search...">

<table class="table table-striped text-center">

<tr>
<th>ID</th>
<th>Name</th>
<th>Model</th>
<th>Price</th>
<th>Interval</th>
<th>Actions</th>
</tr>

<%
List<Equipment> list = (List<Equipment>) request.getAttribute("equipmentList");

if(list != null){
for(Equipment e : list){
%>

<tr>
<td><%= e.getEquipmentid() %></td>
<td><%= e.getEquipmentName() %></td>
<td><%= e.getEquipmentModel() %></td>
<td><%= e.getEquipmentPrice() %></td>
<td><%= e.getMaintenanceinterval() %></td>

<td>
<a href="deleteEquipment?id=<%= e.getEquipmentid() %>" class="btn btn-danger btn-sm">Delete</a>

<button class="btn btn-primary btn-sm editBtn"
data-id="<%= e.getEquipmentid() %>"
data-name="<%= e.getEquipmentName() %>"
data-model="<%= e.getEquipmentModel() %>"
data-price="<%= e.getEquipmentPrice() %>"
data-interval="<%= e.getMaintenanceinterval() %>">
Edit
</button>
</td>

</tr>

<%
}}
%>

</table>

</div>

<!-- MODAL -->
<div id="editModal" style="display:none; position:fixed; top:0; width:100%; height:100%; background:rgba(0,0,0,0.5);">
<div style="background:white; width:400px; margin:100px auto; padding:20px;">

<form action="updateEquipment" method="post">

<input id="edit_id" name="id" readonly class="form-control mb-2">
<input id="edit_name" name="name" class="form-control mb-2">
<input id="edit_model" name="model" class="form-control mb-2">
<input id="edit_price" name="price" class="form-control mb-2">
<input id="edit_interval" name="interval" class="form-control mb-2">

<button class="btn btn-primary">Update</button>
<button type="button" onclick="closeModal()" class="btn btn-secondary">Cancel</button>

</form>

</div>
</div>

<script>

document.getElementById("searchInput").addEventListener("keyup", function(){
let v=this.value.toLowerCase();
document.querySelectorAll("table tr").forEach(r=>{
r.style.display=r.innerText.toLowerCase().includes(v)?"":"none";
});
});

document.querySelectorAll(".editBtn").forEach(b=>{
b.onclick=function(){
editModal.style.display="block";
edit_id.value=this.dataset.id;
edit_name.value=this.dataset.name;
edit_model.value=this.dataset.model;
edit_price.value=this.dataset.price;
edit_interval.value=this.dataset.interval;
}
});

function closeModal(){ editModal.style.display="none"; }

</script>

</body>
</html>