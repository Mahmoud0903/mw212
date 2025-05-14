export {};

document.addEventListener('DOMContentLoaded', () => {
  const searchInput = document.getElementById('searchInputKatalog') as HTMLInputElement | null;
  const searchButton = document.getElementById('searchButtonKatalog') as HTMLButtonElement | null;

  if (searchButton && searchInput) {
    searchButton.addEventListener('click', () => {
      const searchTerm = searchInput.value.trim();
      if (searchTerm) {
        window.location.href = `results.html?search=${encodeURIComponent(searchTerm)}`;
      } else {
        alert('Bitte gib einen Suchbegriff ein.');
      }
    });

    searchInput.addEventListener('keypress', (event) => {
      if (event.key === 'Enter') {
        searchButton.click();
      }
    });
  } else {
    console.error('Suchfeld oder Button im Katalog nicht gefunden.');
  }
});
