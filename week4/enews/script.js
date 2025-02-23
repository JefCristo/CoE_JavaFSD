document.addEventListener('DOMContentLoaded', () => {
    let articleData;
    fetch('data.json')
        .then(response => {
            if (!response.ok) throw new Error(`Failed to load data: ${response.status} ${response.statusText}`);
            return response.json();
        })
        .then(data => {
            articleData = data.articles;
            console.log('Articles loaded successfully:', articleData);
            initializeHomepage();
            initializeArticlePage();
        })
        .catch(error => {
            console.error('Error loading data:', error);
            document.querySelector('.tab-content')?.innerHTML = '<p>Error loading articles. Please ensure data.json is accessible and try again later.</p>';
        });

    // Initialize homepage features (tabs, search, slider, popular news)
    function initializeHomepage() {
        if (!document.querySelector('.category-tabs')) return;

        // Tab switching
        const tabs = document.querySelectorAll('.tabs li');
        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                tabs.forEach(t => t.setAttribute('aria-selected', 'false'));
                tab.setAttribute('aria-selected', 'true');
                const category = tab.dataset.category;
                displayArticles(category);
            });
        });
        tabs[0].setAttribute('aria-selected', 'true');

        // Search
        const searchButton = document.getElementById('searchButton');
        searchButton?.addEventListener('click', () => {
            const query = (document.getElementById('searchInput') )?.value.toLowerCase() || '';
            const filtered = articleData.filter(article =>
                article.title.toLowerCase().includes(query) ||
                article.author.toLowerCase().includes(query) ||
                article.category.toLowerCase().includes(query)
            );
            displayArticles(null, filtered);
        });

        // Featured Slider and Popular News
        displayFeaturedSlider();
        displayPopularNews();
    }

    // Initialize article page features (comments, social sharing)
    function initializeArticlePage() {
        const article = document.querySelector('article[data-id]');
        if (article) {
            const articleId = article.getAttribute('data-id');
            if (articleId) {
                loadArticleComments(articleId);
                setupSocialSharing(articleId);
            }
        }
    }

    function displayArticles(category, articles = articleData) {
        const tabContent = document.querySelector('.tab-content');
        if (!tabContent) return;
        let filteredArticles = category && category !== 'all' ?
            articles.filter(a => a.category === category) : articles;
        if (!filteredArticles || filteredArticles.length === 0) {
            tabContent.innerHTML = '<p>No articles found.</p>';
            return;
        }
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
        if (!sidebar || !articleData) {
            sidebar.innerHTML += '<p>No popular news available.</p>';
            return;
        }
        const popular = articleData.sort((a, b) => b.date.localeCompare(a.date)).slice(0, 3);
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
        if (!slider || !articleData) {
            slider.innerHTML = '<p>No featured articles available.</p>';
            return;
        }
        const featured = articleData.filter(a => a.category === 'technology').slice(0, 3);
        slider.innerHTML = featured.map(a => `
            <article>
                <h3>${a.title}</h3>
                <p>${a.summary}</p>
                <a href="article${a.id}.html" aria-label="Read more about ${a.title}">Read More</a>
            </article>
        `).join('');
    }

    function loadArticleComments(articleId) {
        const commentsDiv = document.querySelector(`.comments[data-article-id="${articleId}"]`);
        if (!commentsDiv || !articleData) return;
        const article = articleData.find(a => a.id === articleId);
        if (!article) {
            commentsDiv.innerHTML = '<p>Comments not available for this article.</p>';
            return;
        }
        commentsDiv.innerHTML = `
            <h3>Comments</h3>
            ${article.comments.map(c => `<p><strong>${c.author}</strong>: ${c.text} <small>(${new Date(c.date).toLocaleDateString()})</small></p>`).join('')}
            <form onsubmit="addComment(event, '${articleId}')" aria-label="Add a comment">
                <input type="text" placeholder="Your name" required aria-label="Your name" id="commentAuthor_${articleId}">
                <textarea placeholder="Your comment" required aria-label="Your comment" id="commentText_${articleId}"></textarea>
                <button type="submit">Post Comment</button>
            </form>
        `;
    }

    function setupSocialSharing(articleId) {
        const socialShare = document.querySelector('.social-share');
        if (!socialShare || !articleData) return;
        const article = articleData.find(a => a.id === articleId);
        if (!article) return;
        socialShare.innerHTML = `
            <a href="https://twitter.com/share?url=${encodeURIComponent(`http://localhost:8000/article${articleId}.html`)}&text=${encodeURIComponent(article.title)}" target="_blank" aria-label="Share on Twitter">Share on Twitter</a>
            <a href="https://www.facebook.com/sharer/sharer.php?u=${encodeURIComponent(`http://localhost:8000/article${articleId}.html`)}" target="_blank" aria-label="Share on Facebook">Share on Facebook</a>
        `;
    }

    function addComment(event, articleId) {
        event.preventDefault();
        const authorInput = document.getElementById(`commentAuthor_${articleId}`) ;
        const textInput = document.getElementById(`commentText_${articleId}`) ;
        const newComment = {
            author: authorInput.value,
            text: textInput.value,
            date: new Date().toISOString()
        };
        const article = articleData.find(a => a.id === articleId);
        if (article) {
            article.comments.push(newComment);
            loadArticleComments(articleId);
            authorInput.value = '';
            textInput.value = '';
        }
    }

    // Multimedia gallery (for #multimedia hash on homepage)
    function displayMultimedia() {
        const mainContent = document.getElementById('main-content');
        if (!mainContent || !articleData) {
            mainContent.innerHTML = '<p>No multimedia content available.</p>';
            return;
        }
        const multimedia = articleData.flatMap(a => a.multimedia);
        if (!multimedia.length) {
            mainContent.innerHTML = '<p>No multimedia content available.</p>';
            return;
        }
        mainContent.innerHTML = `
            <h1>Multimedia Gallery</h1>
            <div class="multimedia-grid">
                ${multimedia.map(m => {
                    if (m.type === 'video') return `<div class="media-item"><video controls style="width:100%; max-width:400px;" aria-label="${m.title || 'Video content'}"><source src="${m.src}" type="video/mp4">Your browser does not support the video tag.</video><p>${m.title || 'Video'}</p></div>`;
                    if (m.type === 'image') return `<div class="media-item"><img src="${m.src}" alt="${m.title || 'Image content'}" style="width:100%; max-width:400px; border-radius:8px;"><p>${m.title || 'Image'}</p></div>`;
                    if (m.type === 'audio') return `<div class="media-item"><audio controls style="width:100%; max-width:400px;" aria-label="${m.title || 'Audio content'}"><source src="${m.src}" type="audio/mp3">Your browser does not support the audio tag.</audio><p>${m.title || 'Audio'}</p></div>`;
                    return '';
                }).join('')}
            </div>
        `;

        // Handle hash-based navigation for multimedia on homepage
        if (window.location.hash === '#multimedia') {
            displayMultimedia();
        }
    }

    // Initialize based on current page
    if (window.location.pathname.includes('article')) {
        initializeArticlePage();
    } else {
        initializeHomepage();
        if (window.location.hash === '#multimedia') {
            displayMultimedia();
        } else if (window.location.hash.startsWith('#category/')) {
            const category = window.location.hash.split('/')[1];
            displayArticles(category);
            const tabs = document.querySelectorAll('.tabs li');
            tabs.forEach(tab => tab.setAttribute('aria-selected', tab.dataset.category === category ? 'true' : 'false'));
        }
    }
});