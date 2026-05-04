function openEditModal(id, name, gender, phone, spec, email, salary, dept) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editName").value = name;
    document.getElementById("editGender").value = gender;
    document.getElementById("editPhone").value = phone;
    document.getElementById("editSpec").value = spec;
    document.getElementById("editEmail").value = email;
    document.getElementById("editSalary").value = salary;
    document.getElementById("editDept").value = dept;
}

function closeModal() {
    document.getElementById("editModal").style.display = "none";
}