function mostrarPassword() {
	var cambio = document.getElementById("password");
	if (cambio.type == "password") {
		cambio.type = "text";
		$('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
	} else {
		cambio.type = "password";
		$('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
	}
}

$(document).ready(function () {
	//CheckBox mostrar contraseña
	$('#ShowPassword').click(function () {
		$('#Password').attr('type', $(this).is(':checked') ? 'text' : 'password');
	});
});



// Validation

const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

const expresiones = {
	password: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
}

const campos = {
	password: false,
	correo: false
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "correo":
			validarCampo(expresiones.correo, e.target, 'correo');
			break;
		case "password":
			validarCampo(expresiones.password, e.target, 'password');
			break;
	}
}

const validarCampo = (expresion, input, campo) => {
	if (expresion.test(input.value)) {
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}

inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('keyup', validarFormulario);
});

async function login() {

	let datos = {};
	datos.username = document.getElementById("correo").value;
	datos.password = document.getElementById("password").value;

	console.log(datos);

	const request = await fetch('http://localhost:8080/auth/signin',{
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		  },
		body: JSON.stringify(datos)
	})
	const respuesta = await request.text();

	console.log(respuesta);

	if (respuesta.startsWith("ey")) {
		localStorage.token = respuesta;
		localStorage.email = datos.username;
		alert("Ha iniciado sesión como " + localStorage.email);
		window.location.href = "Session.html";
	} else {
		alert("Las credenciales son incorrectas. por favor intente nuevamente.");
	}
}

window.addEventListener("click", function(e){
	if (e && "preventDefault" in e) e.preventDefault();

	switch(e.target.id){
		case "icon-login":
			window.location.href = "Login.html";
			break;
		case "logoMainPage":
			window.location.href = "Index.html";
			break;
		case "loggingButton":
			login();
			break;
		case "register":
			window.location.href = "formulario.html";
			break;
	}



});

