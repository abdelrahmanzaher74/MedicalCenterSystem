function openEditModal(id, name, spec, gender, email, dept, price) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editName").value = name;
    document.getElementById("editSpec").value = spec;
    document.getElementById("editGender").value = gender;
    document.getElementById("editEmail").value = email;
    document.getElementById("editDept").value = dept;
    document.getElementById("editPrice").value = price;
}

function closeModal() {
    document.getElementById("editModal").style.display = "none";
}