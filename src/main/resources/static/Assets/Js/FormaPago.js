var jItems = JSON.parse(localStorage.allProducts);
var itemsPaypal = "";
var itemHtml = "";
var totalProducts = JSON.parse(localStorage.totalProducts);
var totalPaypalChain = [];
var address_user = JSON.parse(localStorage.datosEnvio);
var estado = []; 
var request = "";
var itemCarrito = "";
var content = [];
var longestBeer=0;

jItems.forEach(element =>{
    var sizeBeer = element.title.length;
    if(sizeBeer>longestBeer){
        longestBeer = sizeBeer;
    }
    
})

jItems.forEach(element => {

    var itemPrice = element.price.split(" ");
    var itemTotal = parseFloat(itemPrice[1])*parseFloat(element.quantity);
    var beerName = element.title;

    for(let i = 0 ;i<longestBeer-element.title.length;i++){
        beerName+=" ";
    }
    let contentItem = "                                "+element.quantity+"                                "+beerName+"                                "+itemPrice[1]+"                                "+itemTotal;
    content = [...content, contentItem];

    const itemHtml = {
        name: element.title,
        quantity: element.quantity,
        unit_amount: {
            currency_code: "MXN",
            value: itemPrice[1]
        }
    }

    itemsPaypal = [...itemsPaypal, itemHtml]
    itemCarrito += '<tr><td>'+element.quantity+'</td><td>'+element.title+'</td></tr>';

})

document.querySelector('#orderProducts tbody').innerHTML = itemCarrito;
document.querySelector('#totalGrid').innerHTML = 'Total: '+ totalProducts;

totalPaypalChain = totalProducts.split(" ");
estado = address_user.estado.split(" ");

const address_info = {
    address_line_1: address_user.direccion+" "+ address_user.numero+", Colonia "+address_user.colonia,
    admin_area_2: address_user.ciudad,
    admin_area_1: estado[1],
    postal_code: address_user.cp,
    country_code: "MX"
}

request = {
    "intent": "CAPTURE",
    "purchase_units": [{
        "items": itemsPaypal,
        "amount": {
            "currency_code": "MXN",
            "value": totalPaypalChain[1],
            "breakdown": {
                "item_total": {
                    "currency_code": "MXN",
                    "value": `${totalPaypalChain[1]}`
                }
            }
        },
        "shipping": {
            "address": address_info
        }
    }]
}

paypal.Buttons({
    style: {
        layout: 'vertical',
        color: 'silver',
        shape: 'rect',
        label: 'pay'
    },
    // Sets up the transaction when a PayPal payment button is clicked
    createOrder: async (data, actions) => {
        const response = await fetch("https://www.brisbiere.com/paypal/order", {
            method: "post",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': localStorage.token 
            },
            body: JSON.stringify(request)
            // use the "body" param to optionally pass additional order information
            // like product ids or amount or you can pass them in the backend like we did.
        });
        const order = await response.json();
        return order.id;
    },
    // Finalize the transaction after payer approval
    onApprove: async (data, actions) => {
        const response = await fetch(`https://www.brisbiere.com/paypal/order/${data.orderID}/capture`, {
            method: "post"
        });
        const orderData = await response.json();
        // Successful capture! For dev/demo purposes:
        console.log("Capture result", orderData, JSON.stringify(orderData, null, 2));
        const transaction = orderData.purchase_units[0].payments.captures[0];
        alert("Transaction " + transaction.status + ": " + transaction.id + "\n\nSee console for all available details");
        // Save order in DB
        saveOrder();
        // Send confirmation email
        sendEmail();
        //reedirect to session page
        //window.location.href="/session.html";
    },
}).render("#paypal-button-container");



async function saveOrder(){
    let orderItems = JSON.parse(localStorage.allProducts);
    let orderItemsArray = [];
    let addressUser = JSON.parse(localStorage.datosEnvio);
    let totalProducts = JSON.parse(localStorage.totalProducts);
    let date = new Date().toLocaleString("sv-SE");

    const lastOrderIdRequest = await fetch('https://www.brisbiere.com/orders/lastOrderId', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token 
    },
    });
    const lastOrderId = await lastOrderIdRequest.json();
    const currentId = JSON.parse(lastOrderId)+1;

    const getProductId = (product)=>{
        switch(product){
            case "First Batch" : return "1";
            case "Big Poppa-K" : return "2";
            case "k-101" : return "3";
            case "Perla" : return "4";
            case "Prieta" : return "5";
            case "Trigueña" : return "6";
            case "Rubia" : return "7";     
            case "Mestiza" :  return "8";            
            case "Fractal" : return "9";
        }
    }

    orderItems.forEach((beer)=>{

        let beerPrice = beer.price.split(" ");
        let totalPerBeer = beerPrice[1]*beer.quantity;

        let itemBody = {
            "orderId": currentId,
            "productId": getProductId(beer.title),
            "name": beer.title,
            "quantity": beer.quantity,
            "beerPrice": beerPrice[1],
            "beerTotal": totalPerBeer
        }

        orderItemsArray = [...orderItemsArray, itemBody];
    })
    
    addressUser = JSON.stringify(addressUser);
    
    let totalProduct = totalProducts.split(" ");
    let totalProductFloat = parseFloat(totalProduct[1]);
    let dateTime = date.split(" ");
    let datef = dateTime[0]+ "T" + dateTime[1]; 

    let approvedOrder = {
        "orderId": currentId,
        "trackingGuide": "En proceso",
        "status": "En proceso",
        "address": addressUser,
        "date": datef,
        "userId": JSON.parse(localStorage.usuarioId),
        "payment": "Tarjeta Cred",
        "total": totalProductFloat,
        "items": orderItemsArray
    }

    const request = await fetch('https://www.brisbiere.com/orders/save', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        },
        body: JSON.stringify(approvedOrder)
    });
}

async function sendEmail(){
    let email = localStorage.getItem("email");
    let subject = "Confirmación de orden.";
    let message = `Su compra por ${totalProducts} ha sido confirmada.`;
    let date = new Date().toLocaleString("sv-SE");
    
    const emailRequest = {
        "toUser": email,
        "subject": subject,
        "message": message,
        "content": content,
        "totalCompra": totalProducts,
        "fecha": date
    };

    console.log(emailRequest);

    const request = await fetch("https://www.brisbiere.com/email/sendMessage",{
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token 
        },
        body: JSON.stringify(emailRequest)
    })
}