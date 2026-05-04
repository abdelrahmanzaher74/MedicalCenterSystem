<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.medical.model.entities.*" %>

<html>
<head>
<title>Blood Compatibility</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.card-custom{border-radius:15px; transition:0.3s;}
.card-custom:hover{transform:scale(1.05);}
.highlight{background:#ffe5e5;}
</style>

</head>

<body class="bg-light">

<jsp:include page="sidebar.jsp" />

<div style="margin-left:230px; padding:20px;">

<h2 class="mb-4">🩸 Blood Compatibility</h2>

<!-- CARDS -->
<div class="row mb-4">

<div class="col-md-6">
<div class="card card-custom bg-danger text-white p-3 text-center">
<h4>Donor → Receiver</h4>
<p>Select blood type</p>
</div>
</div>

<div class="col-md-6">
<div class="card card-custom bg-dark text-white p-3 text-center">
<h4>Smart Filter</h4>
<p>Live Matching</p>
</div>
</div>

</div>

<!-- SELECT -->
<select id="bloodFilter" class="form-select mb-3">
<option value="">Select Blood Type</option>

<%
Set<String> bloodTypes = new HashSet<>();

List<Transfusion> list = (List<Transfusion>) request.getAttribute("transfusionList");

if(list != null){
for(Transfusion t : list){

if(t.getBloodtype() != null){
bloodTypes.add(t.getBloodtype().getBloodname());
}

if(t.getBloodtype1() != null){
bloodTypes.add(t.getBloodtype1().getBloodname());
}

}}

for(String bt : bloodTypes){
%>
<option value="<%= bt %>"><%= bt %></option>
<%
}
%>

</select>

<!-- TABLE -->
<table class="table table-bordered text-center">

<tr class="table-dark">
<th>Donor</th>
<th>Can Donate To</th>
</tr>

<%
if(list != null){
for(Transfusion t : list){
%>

<tr class="data-row">
<td class="donor">
<%= t.getBloodtype() != null ? t.getBloodtype().getBloodname() : "" %>
</td>

<td class="receiver">
<%= t.getBloodtype1() != null ? t.getBloodtype1().getBloodname() : "" %>
</td>
</tr>

<%
}}
%>

</table>

</div>

<script>

document.getElementById("bloodFilter").addEventListener("change", function(){

let selected = this.value.trim().toLowerCase();

document.querySelectorAll(".data-row").forEach(row=>{

let donor = row.querySelector(".donor").innerText.trim().toLowerCase();

if(selected === ""){
row.style.display = "";
row.classList.remove("highlight");
}
else if(donor === selected){
row.style.display = "";
row.classList.add("highlight");
}
else{
row.style.display = "none";
row.classList.remove("highlight");
}

});

});

</script>

</body>
</html>