window.addEventListener("load", function (){ //cuando se cargue la página
       let div = document.getElementById("div_User"); //div donde se va a agregar los productos
       loadUser(div); //se llama a la función addItems
       
});

async function loadUser(){

    const request = await fetch('https://www.brisbiere.com/users/email/' + localStorage.email,{
        method : 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+localStorage.token
        }
    });
    
    const usuario = await request.json();
    localStorage.usuarioId = usuario.userId;
    var birthday = Date.parse(usuario.birthday);
    var age = Date.now() - birthday;
    var year = Math.floor(age / 31536000000);

    let botonEliminar = '<button onclick="eliminarUsuario()" type="button" class="btn btn-outline-dark" id="eraseAccountButton">Eliminar cuenta</button>';
    
    let usuarioHtml = '<h3><strong>' + usuario.name + ' ' + usuario.lastname + ', ' + year + ' años</strong></h3>' + botonEliminar + '<p>' + usuario.email + '</p>';

    document.querySelector('#div_User').outerHTML = usuarioHtml;
    loadOrders();
}


async function loadOrders(){

    const request = await fetch('https://www.brisbiere.com/orders/' + localStorage.usuarioId,{
        method : 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+localStorage.token
        }
    });
    const orders = await request.json();

    listadoHTML = '';
    for(let order of orders){
        
        let itemsList = '';
        for(let item of order.items){
            
            item = '<p>'+item.quantity+ 'pz.  '+ item.name+' ' + item.beerPrice + ' $ MXN</p>';
            itemsList+= item;
        }
        let orderHtml = '<tr><th scope="row">'+order.orderId+'</th><td>'+order.trackingGuide+'</td><td>'+order.status+'</td><td>'+order.date+'</td><td>'+order.payment+'</td><td>'+
        itemsList+'</td><td>'+"$ "+order.total.toFixed(2)+' MXN'+'</td></tr>';
        listadoHTML += orderHtml;
    }

    document.querySelector('#sessionOrdersTable tbody').outerHTML = listadoHTML;
}

async function eliminarUsuario(){
    confirm("¿Está seguro que quiere eliminar su cuenta?");

    const request = await fetch('https://www.brisbiere.com/users/delete/' + localStorage.usuarioId,{
        method : 'DELETE',
        headers: {
            // 'Accept': 'application/json',
            // 'Content-Type': 'application/json',
            'Authorization': 'Bearer '+localStorage.token 
        }
    });
    const resp = await request.text();

    console.log(resp);

    if(resp == "User: Deleted"){
        window.location.href = './Index.html'; 
    }
    
}