document.addEventListener('DOMContentLoaded', () => {
    let articleData;
    fetch('data.json')
        .then(response => response.json())
        .then(data => {
            articleData = data.articles;
            displayArticles('all');
            displayPopularNews();
            displayFeaturedSlider();
        })
        .catch(error => console.error('Error loading data:', error));

    // Tab switching
    const tabs = document.querySelectorAll('.tabs li');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const category = tab.dataset.category;
            displayArticles(category);
        });
    });

    // Initialize with 'All' selected
    tabs[0].setAttribute('aria-selected', 'true');

    // Search
    const searchButton = document.querySelector('.search-bar button');
    searchButton.addEventListener('click', () => {
        const query = document.querySelector('.search-bar input').value.toLowerCase();
        const filtered = articleData.filter(article =>
            article.title.toLowerCase().includes(query) ||
            article.author.toLowerCase().includes(query) ||
            article.category.toLowerCase().includes(query)
        );
        displayArticles(null, filtered);
    });

    // Handle multimedia (via hash, but not articles)
    window.addEventListener('hashchange', () => {
        const hash = window.location.hash;
        if (hash === '#multimedia') {
            displayMultimedia();
        } else if (hash.startsWith('#category/')) {
            const category = hash.split('/')[1];
            displayArticles(category);
            tabs.forEach(tab => tab.setAttribute('aria-selected', tab.dataset.category === category ? 'true' : 'false'));
        } else {
            displayArticles('all');
            tabs[0].setAttribute('aria-selected', 'true');
        }
    });

    // Initial load based on URL hash
    if (window.location.hash === '#multimedia') {
        displayMultimedia();
    } else if (window.location.hash.startsWith('#category/')) {
        const category = window.location.hash.split('/')[1];
        displayArticles(category);
        tabs.forEach(tab => tab.setAttribute('aria-selected', tab.dataset.category === category ? 'true' : 'false'));
    }
});

function displayArticles(category, articles = articleData) {
    const tabContent = document.querySelector('.tab-content');
    let filteredArticles = category && category !== 'all' ?
        articles.filter(a => a.category === category) : articles;
    tabContent.innerHTML = filteredArticles.map(a => `
        <article>
            <h3>${a.title}</h3>
            <p>${a.summary}</p>
            <a href="article${a.id}.html" aria-label="Read more about ${a.title}">Read More</a>
        </article>
    `).join('');
}

function displayPopularNews() {
    const sidebar = document.querySelector('.sidebar');
    const popular = articleData.sort((a, b) => b.title.localeCompare(a.title)).slice(0, 3); // Sort by title for demo
    sidebar.innerHTML += popular.map(a => `
        <article>
            <h3>${a.title}</h3>
            <p>${a.summary}</p>
            <a href="article${a.id}.html" aria-label="Read more about ${a.title}">Read More</a>
        </article>
    `).join('');
}

function displayFeaturedSlider() {
    const slider = document.querySelector('.featured-slider');
    const featured = articleData.filter(a => a.category === 'technology').slice(0, 3); // Feature tech articles
    slider.innerHTML = featured.map(a => `
        <article>
            <h3>${a.title}</h3>
            <p>${a.summary}</p>
            <a href="article${a.id}.html" aria-label="Read more about ${a.title}">Read More</a>
        </article>
    `).join('');
}

function displayMultimedia() {
    const mainContent = document.getElementById('main-content');
    const multimedia = articleData.flatMap(a => a.multimedia);
    mainContent.innerHTML = `
        <h1>Multimedia Gallery</h1>
        <div class="multimedia-grid">
            ${multimedia.map(m => {
                if (m.type === 'video') return `<div class="media-item"><video controls style="width:100%; max-width:400px;" aria-label="${m.title || 'Video content'}"><source src="${m.src}" type="video/mp4">Your browser does not support the video tag.</video><p>${m.title || 'Video'}</p></div>`;
                if (m.type === 'image') return `<div class="media-item"><img src="${m.src}" alt="${m.title || 'Image content'}" style="width:100%; max-width:400px; border-radius:4px;"><p>${m.title || 'Image'}</p></div>`;
                if (m.type === 'audio') return `<div class="media-item"><audio controls style="width:100%; max-width:400px;" aria-label="${m.title || 'Audio content'}"><source src="${m.src}" type="audio/mp3">Your browser does not support the audio tag.</audio><p>${m.title || 'Audio'}</p></div>`;
                return '';
            }).join('')}
        </div>
    `;
}