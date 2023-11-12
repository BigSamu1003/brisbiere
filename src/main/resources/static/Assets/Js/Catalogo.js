
window.addEventListener("load", function (){ 
    let div = document.getElementById("div_Productos");

    if(this.localStorage.getItem("filtro")=='ASC' || this.localStorage.getItem("filtro")=='DESC'){
        loadBeers();
    } else{
        showItems(div);
    }
});

function updateFiltro(catalogo){
    localStorage.setItem("filtro", catalogo);
    window.location.href = './Catalogo.html'; 
}


function showItems(div_Productos) {  
    fetch('https://www.brisbiere.com/products/' + localStorage.filtro, {
        mode: "no-cors",
        method: 'get',
        headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
    })
    .then(function(response) {
        response.json().then(function (json) { 
            Array.from(json).forEach((p, index) => { 
                beerPrice = p.price;
                div_Productos.innerHTML += `
                <div class="col-sm-3 pb-3">
                    <div class="item card md-3">
                        <figure><img class="card-image-top" role="img" src="Assets/Img/${p.image}" /></figure>
                        <div class="card-body info-product">
                            <strong>${p.name}</strong>
                            <p>${p.description}</p>
                    <p class="price">$ ${beerPrice.toFixed(2)} MXN </p><button type="button" class="btn btn-dark btn-sm btn-add-cart">Agregar</button>
                  </div>
                </div>
              </div>
                    `;
            }); 
        });
    }).catch(function(err) { 
        console.log(err); 
    });
   
}


async function loadBeers(){

    const request = await fetch('https://www.brisbiere.com/products/available?sortDirection=' + localStorage.filtro,{
        method : 'GET',
        mode: "no-cors",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });
    const response = await request.json();

    for(let beer of response.content){
        beerPrice = beer.price;

        div_Productos.innerHTML += `
        <div class="col-sm-3 pb-3">
            <div class="item card md-3">
                <figure><img class="card-image-top" role="img" src="Assets/img/${beer.image}" /></figure>
                <div class="card-body info-product">
                    <strong>${beer.name}</strong>
                    <p>${beer.description}</p>
                    <p class="price">$ ${beerPrice.toFixed(2)} MXN </p><button type="button" class="btn btn-dark btn-sm btn-add-cart">Agregar</button>
                </div>
            </div>
        </div>
        `;
    }
    document.getElementById("div_Productos") = div_Productos;
    
}

