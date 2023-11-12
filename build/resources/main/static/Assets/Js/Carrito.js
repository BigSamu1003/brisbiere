const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.container-cart-products');

btnCart.addEventListener('click', () => {
    containerCartProducts.classList.toggle('hidden-cart');
    const rowProduct = document.querySelector('.row-product');
    rowProduct.innerHTML = JSON.parse(localStorage.getItem("rowProduct"));
    
})
/*******************************/
window.addEventListener("load",()=>{
    if(window.location.pathname==="/Catalogo.html"){
        
        productsList.addEventListener('click', e =>{

            if(e.target.classList.contains('btn-add-cart')){
                const product = e.target.parentElement.parentElement
        
                const infoProduct = {
                    quantity : 1,
                    title: product.querySelector('strong').textContent,
                    price: product.querySelector('.price').textContent,
                }
                
                const exits = allProducts.some(product => product.title === infoProduct.title)
        
                if(exits){
                    const products = allProducts.map(product => {
                        if(product.title == infoProduct.title){
                            product.quantity++;
                            return product
                        }else{
                            return product
                        }
                    })
                    allProducts = [...allProducts]
                }else{
                    allProducts = [...allProducts, infoProduct]
                }
        
                
            }
            // localStorage.setItem("productsList", JSON.stringify(productsList.innerHTML))
            showHTML();
        })
        showHTML();
    }else{
        allProducts = JSON.parse(localStorage.getItem("allProducts"));
        countProducts.innerText = JSON.parse(localStorage.getItem("countProducts"));
        rowProduct.innerHTML = JSON.parse(localStorage.getItem("rowProduct"));
        valorTotal.innerText = JSON.parse(localStorage.getItem("totalProducts"));
        if (!allProducts.length) {
            cartEmpty.classList.remove('hidden');
            rowProduct.classList.add('hidden');
            cartTotal.classList.add('hidden');
        } else {
            cartEmpty.classList.add('hidden');
            rowProduct.classList.remove('hidden');
            cartTotal.classList.remove('hidden');
        }
        // productsList.innerHTML = JSON.parse(localStorage.getItem('productsList'));
    }

    
})
/*-----------------------*/
const cartInfo = document.querySelector('.cart-product');
const rowProduct = document.querySelector('.row-product');

// Lista de todos los contenedores deproductos
const productsList = document.querySelector('.container-items');


//variable de arreglos de productos
let allProducts = JSON.parse(localStorage.getItem("allProducts")) || [];

const valorTotal = document.querySelector('.total-pagar')

const countProducts = document.querySelector('#contador-productos');

const cartEmpty = document.querySelector('.cart-empty');
const cartTotal = document.querySelector('.cart-total');



// Función para eliminar articulos
rowProduct.addEventListener('click', (e) =>{
    if(e.target.classList.contains('icon-close')){
        const product = e.target.parentElement
        const title = product.querySelector('p').textContent
        
        allProducts = allProducts.filter(
             product => product.title !== title
        );
        
        showHTML()
    }
})

//Función para mostrar HTML
const showHTML = () =>{
    console.log(allProducts.length)
    if (!allProducts.length) {
		cartEmpty.classList.remove('hidden');
		rowProduct.classList.add('hidden');
		cartTotal.classList.add('hidden');
	} else {
		cartEmpty.classList.add('hidden');
		rowProduct.classList.remove('hidden');
		cartTotal.classList.remove('hidden');
	}

    // Limpiar HTML
    rowProduct.innerHTML = '';

    let total = 0;
    let totalOfProducts = 0;

    allProducts.forEach(product =>{
        const indiceRowProducto = allProducts.indexOf(product)+1;
        const containerProduct = document.createElement('div')
        containerProduct.classList.add('cart-product')
        const pricePerBeer = parseFloat(product.quantity * product.price.slice(1,7)).toFixed(2)

        containerProduct.innerHTML = `
            <div class="info-cart-product" id="rowIndex${indiceRowProducto}">
                <span class="cantidad-producto-carrito">${product.quantity}</span>
                <p class="titulo-producto-carrito">${product.title}</p>
                <span class="precio-producto-carrito">$ ${pricePerBeer} MXN </span>
            </div>
            <img class="icon-close" src="./Assets/Img/iconx.png" >
        `;
        rowProduct.append(containerProduct);
        total = total + parseFloat(product.quantity * product.price.slice(1,7));
        totalOfProducts = totalOfProducts + product.quantity;
        
    })
    
    localStorage.setItem("rowProduct", JSON.stringify(rowProduct.innerHTML));
    valorTotal.innerText = `$ ${total.toFixed(2)} MXN`;
    localStorage.setItem("countProducts", JSON.stringify(totalOfProducts))
    countProducts.innerText = JSON.parse(localStorage.getItem("countProducts"));

    localStorage.setItem("allProducts", JSON.stringify(allProducts));
    localStorage.setItem("totalProducts", JSON.stringify(valorTotal.innerText));
    
}
