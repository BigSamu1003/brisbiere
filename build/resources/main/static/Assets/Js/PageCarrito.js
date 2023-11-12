window.addEventListener('load', ()=>{
    const allProducts = JSON.parse(localStorage.getItem('allProducts'));
    
    showCart();
})



// Función para eliminar articulos
document.addEventListener('click', (e) =>{
  if(e.target.classList.contains('icon-close')){
      
    if(e.target.parentElement.classList.contains('cart-product')){
      const rowCarritoProduct = e.target.parentElement;
      var indiceCarritoProduct = rowCarritoProduct.querySelector('.cantidad-producto-carrito').innerText; 
      var title = document.querySelector(`.rowBeer${indiceCarritoProduct}`);
    }else{
      const trProduct = e.target.parentElement.parentElement.parentElement;
      var title = trProduct.querySelector('.beerTitle').textContent;
      console.log("pedido")
    }
      
      allProducts = allProducts.filter(
           product => product.title !== title
      );
      
      
      localStorage.setItem("allProducts", JSON.stringify(allProducts));
      
      showCart();
      showHTML();
      sumar();
  }
})

async function showCart(){

    const totalProducts = JSON.parse(localStorage.getItem("totalProducts"));
    let pedidoHTML ='';

    allProducts.forEach(beer => {
        const indiceProducto = allProducts.indexOf(beer)+1;
        const beersPrice = parseFloat(beer.quantity * beer.price.slice(2,7)).toFixed(2);
        var quantityText = parseInt(beer.quantity);

        let itemHTML = `
        <tr id="rowBeer${indiceProducto}">
          <th scope="row">${indiceProducto}</th>
          <td>
            <img src="./Assets/Img/${beer.title}.jpeg" id="imgCart" />
          </td>
          <td class="imageTitle">
            <h3 class="beerTitle">${beer.title}</h3>
            <p class="unitPrice">${beer.price}</p>
          </td>
          <td>
            <p id="quantityParent">
              <button type="button" class="btn btn-light modify" name="-">-</button>
              <input type="text" class="quantityBox" min="0" size="1" value="${quantityText}" />
              <button type="button" class="btn btn-light modify" name="+">+</button>
              <img class="icon-close" src="./Assets/Img/iconx.png">
            </p>
          </td>
          <td class="beerPrice">$ ${beersPrice} MXN </td>
        </tr>
       `;
       pedidoHTML += itemHTML;
    });
    document.querySelector('#sessionOrdersTable tbody').innerHTML = pedidoHTML;
    document.querySelector("#totalGrid").innerHTML = `Total: ${totalProducts}`;


}

document.addEventListener("click", (e) => {

  if (e.target.name === "-") {
    var p = e.target.parentElement;
    var rI = p.parentElement.parentElement;
    var roxIndice = rI.querySelector('th').innerText;

    if(parseInt(p.querySelector('input').value)>0){
    let quantity = parseInt( p.querySelector('input').value) - 1;
    var unitRow = e.target.parentElement.parentElement.parentElement;
    var unitPrice = unitRow.querySelector(".unitPrice").textContent;
    precioCerveza = quantity * parseFloat(unitPrice.slice(2,7));

    p.querySelector('input').value = quantity.toString();
    unitRow.querySelector(".beerPrice").innerHTML = "$ "+precioCerveza.toFixed(2).toString()+" MXN";
    
    document.querySelector(`#rowIndex${roxIndice}`).querySelector('.cantidad-producto-carrito').innerHTML = quantity.toString();
    document.querySelector(`#rowIndex${roxIndice}`).querySelector('.precio-producto-carrito').innerHTML = "$ "+precioCerveza.toFixed(2).toString()+" MXN";

    sumar(roxIndice, quantity);
    }
  }else if(e.target.name === "+"){
    var p = e.target.parentElement;
    var rI = p.parentElement.parentElement;
    var roxIndice = rI.querySelector('th').innerText;
    let quantity = parseInt( p.querySelector('input').value) + 1;
    var unitRow = e.target.parentElement.parentElement.parentElement;
    var unitPrice = unitRow.querySelector(".unitPrice").textContent;
    precioCerveza = quantity * parseFloat(unitPrice.slice(2,7));
    
    p.querySelector('input').value = quantity.toString();
    unitRow.querySelector(".beerPrice").innerHTML = "$ "+precioCerveza.toFixed(2).toString()+" MXN";

    document.querySelector(`#rowIndex${roxIndice}`).querySelector('.cantidad-producto-carrito').innerHTML = quantity.toString();
    document.querySelector(`#rowIndex${roxIndice}`).querySelector('.precio-producto-carrito').innerHTML = "$ "+precioCerveza.toFixed(2).toString()+" MXN";

    sumar(roxIndice, quantity);
  }
  
})

function sumar(roxIndice, quantity){
  //      Contador de icono del carrito
  const beerCount = document.querySelector('#contador-productos'); 
  let contadorCerveza = 0;
  [...document.getElementsByClassName("quantityBox")].forEach( function ( beers ) {
    if(beers !== '0') {
      let beerSubCount = parseInt(beers.value);
      contadorCerveza += beerSubCount;
    }
  });
  beerCount.innerHTML = contadorCerveza;

  //       Total de la ventana del icono del carrito
  const total = document.getElementById('totalGrid');
  const totalVentana = document.querySelector('.total-pagar');
  let subtotal = 0;
  [ ...document.getElementsByClassName( "beerPrice" ) ].forEach( function ( element ) {
    if(element.innerHTML !== '') {
      let beerSubtotal = element.innerHTML.slice(2,7);
      subtotal += parseFloat(beerSubtotal);
    }
  });
  total.innerHTML = `Total: $ ${subtotal.toFixed(2)} MXN`;
  totalVentana.innerHTML = `$ ${subtotal.toFixed(2)} MXN`;

  //Guardar en localStorage
  const rowProduct = document.querySelector('.row-product');

allProducts.forEach(element => {
    console.log(allProducts.indexOf(element));
    if(allProducts.indexOf(element) === roxIndice-1){
      element.quantity = quantity;
    }
  });
  
  localStorage.setItem("allProducts", JSON.stringify(allProducts));
  localStorage.setItem("countProducts", JSON.stringify(beerCount.innerHTML));
  localStorage.setItem("rowProduct", JSON.stringify(rowProduct.innerHTML));
  localStorage.setItem("totalProducts", JSON.stringify(totalVentana.innerHTML));
  
}
