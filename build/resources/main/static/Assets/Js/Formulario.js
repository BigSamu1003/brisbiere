const formulario = document.getElementById('singUp');
const inputs = document.querySelectorAll('#singUp input')

var birthdate;
var yearAge;  
var monthAge;
var dayAge;

const expresiones = {
  usuario: /^[a-zA-Z0-9\_\-]{ 4,16}$/,  // Letras, numeros, guion y guion_bajo
  nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/,  // Letras y espacios, pueden llevar acentos.
  contraseña: /^[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+)*$/,  // 4 a 12 dígitos.
  correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
  telefono: /^\d{7,14}$/  // 7 a 14 numeros.
}

const campos = {
  nombre: false,
  apellido: false,
  correo: false,
  password: false
}

const validarFormulario = (e) => {
  switch (e.target.name) {
    case "nombre":
      validarCampo(expresiones.nombre, e.target, 'nombre');
      break;
    case "apellido":
      validarCampo(expresiones.nombre, e.target, 'apellido');
      break;
    case "correo":
      validarCampo(expresiones.correo, e.target, 'correo');
      break;
    case "password":
      validarCampo(expresiones.contraseña, e.target, 'password');
      validarPassword2();
      break;
    case "password2":
      validarPassword2();
      break;
  }
}


const validarCampo = (expresiones, input, campo) => {
  if (expresiones.test(input.value)) {
    document.getElementById(`group__${campo}`).classList.remove('form__group-incorrecto');
    document.getElementById(`group__${campo}`).classList.add('form__group-correcto');
    document.querySelector(`#group__${campo} i`).classList.add('bi-check-circle-fill');
    document.querySelector(`#group__${campo} i`).classList.remove('bi-x-circle-fill');
    document.querySelector(`#group__${campo} .form__input-error`).classList.remove('form__input-error-activo');
    campos[campo] = true;
  } else {
    document.getElementById(`group__${campo}`).classList.add('form__group-incorrecto');
    document.getElementById(`group__${campo}`).classList.remove('form__group-correcto');
    document.querySelector(`#group__${campo} i`).classList.add('bi-x-circle-fill');
    document.querySelector(`#group__${campo} i`).classList.remove('bi-check-circle-fill');
    document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
    campos[campo] = false;
  }
}


const validarPassword2 = () => {
  const inputPassword1 = document.getElementById('password');
  const inputPassword2 = document.getElementById('password2');

  if (inputPassword1.value !== inputPassword2.value) {
    document.getElementById(`group__password2`).classList.add('form__group-incorrecto');
    document.getElementById(`group__password2`).classList.remove('form__group-correcto');
    document.querySelector(`#group__password2 i`).classList.add('bi-x-circle-fill');
    document.querySelector(`#group__password2 i`).classList.remove('bi-check-circle-fill');
    document.querySelector(`#group__password2 .form__input-error`).classList.add('form__input-error-activo');
    campos['password'] = false;
  } else {
    document.getElementById(`group__password2`).classList.remove('form__group-incorrecto');
    document.getElementById(`group__password2`).classList.add('form__group-correcto');
    document.querySelector(`#group__password2 i`).classList.remove('bi-x-circle-fill');
    document.querySelector(`#group__password2 i`).classList.add('bi-check-circle-fill');
    document.querySelector(`#group__password2 .form__input-error`).classList.remove('form__input-error-activo');
    campos['password'] = true;
  }
}

inputs.forEach((input) => {
  input.addEventListener('keyup', validarFormulario);
  input.addEventListener('blur', validarFormulario);
});




formulario.addEventListener('submit', (e) => {
  e.preventDefault();
  
  validarEdad();

  if (yearAge < 18) {
    alert("Para poder registrarse es necesario ser mayor de edad.");
    window.location.href = "./Index.html";
  }


  if (campos.nombre && campos.apellido && campos.correo && campos.password && confirma.checked) {
    registerUser();
    alert("Usuario registrado");
    //window.location.href = "./Session.html"
  }
});


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

function mostrarPassword2() {
  var cambio = document.getElementById("password2");
  if (cambio.type == "password") {
    cambio.type = "text";
    $('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
  } else {
    cambio.type = "password";
    $('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
  }
}

function validarEdad() {
  //Validación de edad
  const hoy = new Date();
  birthdate = new Date(document.getElementById("fecha").value);
  yearAge = hoy.getFullYear() - birthdate.getFullYear();
  monthAge = hoy.getMonth() - birthdate.getMonth();
  dayAge = hoy.getDate() - birthdate.getDate();
  if (dayAge < 0) monthAge -= 1;
  if (monthAge < 0) yearAge -= 1;
  return yearAge;
}

async function registerUser() {
  validarEdad();

  let datos = {};
  datos.name = document.getElementById("nombre").value;
  datos.lastname = document.getElementById("apellido").value;
  datos.birthday = document.getElementById("fecha").value + "T00:00:00";
  datos.email = document.getElementById("correo").value;
  datos.password = document.getElementById("password").value;

  console.log(datos);
  

  const request = await fetch('http://localhost:8080/auth/signup', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
  });
  // const usuarios = await request.json();
  window.location.href = './Login.html';
}