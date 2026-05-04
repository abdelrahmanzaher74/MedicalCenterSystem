function openEditModal(id, patient, doctor, payment) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editPatient").value = patient;
    document.getElementById("editDoctor").value = doctor;
    document.getElementById("editPayment").value = payment;
}

function closeModal() {
    document.getElementById("editModal").style.display = "none";
}