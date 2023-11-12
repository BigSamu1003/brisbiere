
async function loggedIn(){

    if(localStorage.getItem("usuarioId")!= null){
        window.location.href= "envio.html";  
    } else{
        Swal.fire({
            title: 'Hola!',
            text: 'Inicie sesión primero o cree una cuenta, por favor.',
            imageUrl: './Assets/Img/logo.png',
            imageWidth: 175,
            imageHeight: 150,
            imageAlt: 'Custom image',
          }).then((result)=>{
            if(result.isConfirmed){
                window.location.href= "./Login.html";
            }
          })
    }
}