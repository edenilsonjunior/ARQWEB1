import {loadData} from '../components/global.js';

document.addEventListener("DOMContentLoaded", async function () {
    const carouselSlide = document.getElementById('carousel-slide');

    // Carregar os dados das imagens usando a função loadData
    const data = await loadData();

    if (data && data.images) {
        // Inserindo as imagens no carrossel
        data.images.forEach((imgUrl) => {
            const imgElement = document.createElement('img');
            imgElement.src = imgUrl;
            carouselSlide.appendChild(imgElement);
        });

        // Lógica do carrossel
        let currentIndex = 0;
        const totalImages = data.images.length;

        function showImage(index) {
            const offset = index * -100;
            carouselSlide.style.transform = `translateX(${offset}%)`;
        }

        document.getElementById('prevBtn').addEventListener('click', function () {
            currentIndex = (currentIndex > 0) ? currentIndex - 1 : totalImages - 1;
            showImage(currentIndex);
        });

        document.getElementById('nextBtn').addEventListener('click', function () {
            currentIndex = (currentIndex < totalImages - 1) ? currentIndex + 1 : 0;
            showImage(currentIndex);
        });
    } else {
        console.error("Nenhuma imagem foi encontrada.");
    }
});
