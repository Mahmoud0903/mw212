export async function postReservierung(nutzerid: number, mediumId: number) {
  const response = await fetch(`http://localhost:8080/api/reservierungen?nutzerId=${nutzerid}&mediumId=${mediumId}`, { method: 'post' });

  const responseData = response.json();

  return responseData;
}

export async function getReservierungByMediumId(mediumId: number) {
  const response = await fetch(`http://localhost:8080/api/reservierungen/${mediumId}`);

  if (!response.ok || response.status === 204) {
    console.warn(`Keine Reservierung für Medium ID ${mediumId}`);
    return null;
  }
  const text = await response.text();
  if (!text) {
    return null;
  }

  try {
    return JSON.parse(text);
  } catch (e) {
    console.error('Fehler beim Parsen der JSON-Antwort:', e);
    return null;
  }
}

export function formatDatum(datum: string): string {
  const date = new Date(datum);
  return date.toLocaleDateString('de-DE', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
  });
}
