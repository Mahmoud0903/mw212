import { formatDatum, getReservierungenByNutzerId } from 'src/services/reservieren.service';

document.addEventListener('DOMContentLoaded', () => {
  generateReservierungenList();
});

async function generateReservierungenList(): Promise<void> {
  try {
    const reservierungenContainer = document.getElementById('reservierungenContainer');
    // Derzeit nur 1 Dummy User mit der Id 1
    const reservierungen = await getReservierungenByNutzerId(1);

    if (!reservierungenContainer) {
      console.error('Ergebnis-Container nicht gefunden.');
      return;
    }

    reservierungen.forEach((reservierung) => {
      const reservierungHtml = `<div class="border border-gray-200 rounded-md p-4 flex items-start">
              <div class="w-24 h-32 bg-gray-200 rounded-sm mr-4 flex-shrink-0 flex items-center justify-center text-gray-400 text-xs">
                <img src="${reservierung.medium.bildLink}" alt="Cover von ${reservierung.medium.titel}" class="object-cover w-full h-full">
              </div>
              <div>
                <h3 class="text-lg font-semibold text-blue-700">Sapiens: ${reservierung.medium.titel}</h3>
                <p class="text-gray-600 text-sm">${reservierung.medium.autor}</p>
                <p class="text-gray-500 text-xs">${reservierung.reservierungsdatum}</p>
                <p class="${addDateStyle(formatDatum(reservierung.reserviertBis))} font-semibold text-sm ">Reserviert bis: ${formatDatum(
        reservierung.reserviertBis,
      )} </p>
                <div class="mt-2">
                  <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-3 rounded-md text-sm">Stornieren</button>
                  <button class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-bold py-1 px-3 rounded-md ml-2 text-sm">Details</button>
                </div>
              </div>
            </div>`;
      reservierungenContainer?.insertAdjacentHTML('beforeend', reservierungHtml);
    });
  } catch (error) {
    console.error('Fehler beim Laden der Medien:', error);
  }
}

function addDateStyle(date: string) {
  const day = date.substring(0, 2);
  const month = date.substring(3, 5);
  const year = date.substring(6, 10);
  const formattedDate = year.concat('-', month, '-', day);
  const convertedDate = new Date(formattedDate);
  const today = new Date();
  const diffTime = Math.abs(Math.round(convertedDate.getTime() - today.getTime()));
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

  if (diffDays < 2) {
    return 'text-red-500';
  } else if (diffDays < 3) {
    return 'text-yellow-500';
  } else {
    return 'text-green-500';
  }
}
