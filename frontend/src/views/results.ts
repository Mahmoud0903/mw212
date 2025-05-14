import { fetchMediumByInput } from 'src/services/medium.service';

export {};

// Initialisierung
document.addEventListener('DOMContentLoaded', () => {
  const searchInput = document.getElementById('searchInputKatalog') as HTMLInputElement | null;
  const searchButton = document.getElementById('buttonSearch');
  const urlParams = new URLSearchParams(window.location.search);
  const searchTerm = urlParams.get('search');

  if (searchInput) {
    if (searchTerm) {
      searchInput.value = decodeURIComponent(searchTerm);
      generateResultsHtml(searchTerm);
    } else {
      searchInput.placeholder = 'Gib einen Suchbegriff ein';
    }

    searchInput.addEventListener('keydown', (e) => {
      if (e.key === 'Enter') handleSearch();
    });
  } else {
    console.error('Suchfeld auf der Ergebnisseite nicht gefunden.');
  }

  searchButton?.addEventListener('click', handleSearch);
});

// Hilfsfunktionen
function getInputValueById(id: string): string {
  const input = document.getElementById(id) as HTMLInputElement | null;
  return input?.value.trim() ?? '';
}

function getStatusClass(status: string): string {
  switch (status.toUpperCase()) {
    case 'VERFUEGBAR':
      return 'text-green-500';
    case 'RESERVIERT':
      return 'text-yellow-500';
    case 'AUSGELIEHEN':
      return 'text-red-500';
    default:
      return 'text-gray-500';
  }
}

function updateHeadline(searchTerm: string, anzahl: number): void {
  const heading = document.getElementById('resultsHeadline');
  if (heading) {
    heading.innerHTML = `Suchergebnisse für <span class="font-bold italic">"${searchTerm}"</span> (${anzahl} Ergebnis${anzahl !== 1 ? 'se' : ''})`;
  }
}

// Event-Handler
function handleSearch(): void {
  const searchTerm = getInputValueById('searchInputKatalog');
  if (searchTerm.length > 0) {
    generateResultsHtml(searchTerm);
  } else {
    console.warn('Bitte Suchbegriff eingeben.');
  }
}

// HTML-Erzeugung
async function generateResultsHtml(searchTerm: string): Promise<void> {
  try {
    const medien = await fetchMediumByInput(searchTerm);
    const resultsContainer = document.getElementById('resultsContainer');

    if (!resultsContainer) {
      console.error('Ergebnis-Container nicht gefunden.');
      return;
    }

    resultsContainer.innerHTML = ''; // Clear previous results

    medien.forEach((medium) => {
      const mediumHtml = `
        <div class="bg-white rounded-lg shadow-md p-6 flex">
          <div class="w-32 h-40 bg-gray-200 rounded-md mr-6 flex-shrink-0 flex items-center justify-center overflow-hidden">
            <img src="${medium.bildLink}" alt="Cover von ${medium.titel}" class="object-cover w-full h-full">
          </div>
          <div>
            <h3 class="text-lg font-semibold text-blue-700 mb-1">${medium.titel}</h3>
            <p class="text-gray-600 mb-1">Autor: ${medium.autor}</p>
            <p class="font-semibold ${getStatusClass(medium.status)}">${medium.status}</p>
            <div class="mt-2">
              <a href="#" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md mr-2 text-sm">Details</a>
              <button class="bg-yellow-400 hover:bg-yellow-500 text-blue-700 font-bold py-2 px-4 rounded-md text-sm">Vormerken</button>
            </div>
          </div>
        </div>
      `;
      resultsContainer.insertAdjacentHTML('beforeend', mediumHtml);
    });

    updateHeadline(searchTerm, medien.length);
  } catch (error) {
    console.error('Fehler beim Laden der Medien:', error);
  }
}
