function openEditModal(id, equipment, status, cost) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editEquipment").value = equipment;
    document.getElementById("editStatus").value = status;
    document.getElementById("editCost").value = cost;
}

function closeModal() {
    document.getElementById("editModal").style.display = "none";
}