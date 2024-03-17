function validateForm() {
    let puntuacion = document.forms["agregarPelicula"]["puntuacion"].value;
    if (puntuacion < 0){
        alert("La puntuacion no puede ser menor que 0.")
        return false;
    }
    else if (puntuacion > 10){
        alert("La puntuacion no puede ser mayor que 10.")
        return false;
    }
}

function reviewForm(){
    let puntuacion = document.forms["agregarReview"]["puntuacion"].value;
    if (puntuacion < 0){
        alert("La puntuacion no puede ser menor que 0.")
        return false;
    }
    else if (puntuacion > 10){
        alert("La puntuacion no puede ser mayor que 10.")
        return false;
    }
}