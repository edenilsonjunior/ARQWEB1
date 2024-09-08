const navbarContainer = document.getElementById('nav-bar');
navbarContainer.innerHTML = '';

document.addEventListener('DOMContentLoaded', ()=> {

    fetch("load-data")
        .then(response => response.json())
        .then((data)=>{

            console.log(data);

        })
        .catch(console.error);
});
