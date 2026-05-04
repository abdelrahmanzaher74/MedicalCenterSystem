<style>
.sidebar {
    width: 220px;
    height: 100vh;
    position: fixed;
    background: #212529;
    color: white;
    transition: 0.3s;
}

.sidebar a {
    display: block;
    color: #ccc;
    padding: 12px 15px;
    text-decoration: none;
    transition: 0.3s;
}

.sidebar a:hover {
    background: #343a40;
    color: white;
    padding-left: 20px;
}

.sidebar a.active {
    background: #0d6efd;
    color: white;
}

.logo {
    padding: 15px;
    font-size: 18px;
    font-weight: bold;
}
</style>

<div class="sidebar">

    <div class="logo"> Medical System</div>

    <a href="dashboard" class="nav-link">
        <i class="fas fa-home me-2"></i> Dashboard
    </a>

    <a href="appointments" class="nav-link">
        <i class="fas fa-calendar-check me-2"></i> Appointments
    </a>

    <a href="maintenance" class="nav-link">
        <i class="fas fa-tools me-2"></i> Maintenance
    </a>

    <a href="patients" class="nav-link">
        <i class="fas fa-user-injured me-2"></i> Patients
    </a>
      
<a href="patientProfile">
    <i class="fa-solid fa-id-card"></i> Patient Profile
</a>
<a href="blood"><i class="fa-solid fa-droplet"></i> Blood Types</a>
     <a href="staff" class="nav-link">
        <i class="fas fa-user-nurse me-2"></i> Staff
    </a>

    <a href="doctors" class="nav-link">
        <i class="fas fa-user-md me-2"></i> Doctors
    </a>
    <a href="equipment" class="nav-link">
    <i class="fas fa-cogs me-2"></i> Equipment
<a href="transfusion">
    <i class="fas fa-droplet me-2"></i> Blood Compatibility
</a>
    <a href="reports">
    <i class="fa-solid fa-chart-pie"></i> Reports
</a>
    <a href="logout">
    <i class="fa-solid fa-right-from-bracket"></i> Logout
</a>
  

</div>