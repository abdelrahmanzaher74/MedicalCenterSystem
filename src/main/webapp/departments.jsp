<%@ page import="java.util.*, com.medical.model.Department" %>

<html>
<head>
    <title>Departments</title>
    <link rel="stylesheet" href="assets/css/main.css">
</head>

<body>

<div class="container">

<h1 class="title">Departments</h1>

<!-- ===== ADD ===== -->
<div class="card">
    <h2>Add Department</h2>

    <form action="departments" method="post" class="form-container">

        <input name="name" placeholder="Department Name" required>
        <input name="phone" placeholder="Phone">

        <button class="btn btn-add">Add</button>

    </form>
</div>

<!-- ===== TABLE ===== -->
<div class="card">
    <h2>Departments List</h2>

    <table class="table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Delete</th>
        </tr>

<%
List<Department> list = (List<Department>) request.getAttribute("departments");

if(list != null){
for(Department d : list){
%>

<tr>
    <td><%= d.getDepartment_id() %></td>
    <td><%= d.getName() %></td>
    <td><%= d.getPhone() %></td>

    <td>
        <a href="deleteDepartment?id=<%= d.getDepartment_id() %>" 
           class="btn-delete">
            Delete
        </a>
    </td>
</tr>

<%
}}
%>

    </table>
</div>

</div>

</body>
</html>