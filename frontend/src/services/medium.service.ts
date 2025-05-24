import { Buch } from 'src/models/buch';
import { Medium } from 'src/models/medium';

function mapToMedium(m: any): Buch | Medium {
  switch (m.mediumTyp?.toUpperCase()) {
    case 'BUCH':
      return new Buch(m);
    default:
      return new Medium(m);
  }
}

export async function fetchMediumById(id: number): Promise<Buch | Medium> {
  const response = await fetch(`http://localhost:8080/api/medien/${id}`);
  if (!response.ok) {
    throw new Error('Fehler beim Laden des Mediums');
  }
  const m = await response.json();
  return mapToMedium(m);
}

export async function fetchMediumByInput(input: string): Promise<(Buch | Medium)[]> {
  const response = await fetch(`http://localhost:8080/api/medien/suche?begriff=${encodeURIComponent(input)}`);

  if (!response.ok) {
    throw new Error('Fehler beim Laden der Medien');
  }

  const medien = await response.json();

  // Später erweitern mit E-books etc
  return medien.map((m: any) => mapToMedium(m));
}
