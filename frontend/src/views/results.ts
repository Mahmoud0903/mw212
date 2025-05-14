export {};

document.addEventListener('DOMContentLoaded', () => {
  const searchInput = document.getElementById(
    'searchInputKatalog',
  ) as HTMLInputElement | null;
  const urlParams = new URLSearchParams(window.location.search);
  const searchTerm = urlParams.get('search');

  if (searchInput && searchTerm) {
    searchInput.value = decodeURIComponent(searchTerm);
    // Hier könntest du dann die eigentliche Suche mit dem 'searchTerm' starten
    console.log('Suchbegriff auf der Ergebnisseite:', searchTerm);
  } else if (searchInput) {
    searchInput.placeholder = 'Gib einen Suchbegriff ein';
  } else {
    console.error('Suchfeld auf der Ergebnisseite nicht gefunden.');
  }
});
