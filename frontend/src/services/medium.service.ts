import { Buch } from 'src/models/buch';
import { Medium } from 'src/models/medium';

export async function fetchMedium(): Promise<Medium> {
  const response = await fetch(`http://localhost:8080/api/medien`);
  const medium = await response.json();
  return new Medium(medium);
}

export async function fetchMediumById(id: number): Promise<any> {
  const response = await fetch(`http://localhost:8080/api/medien/${id}`);

  const medium = await response.json();

  return new Medium(medium);
}

export async function fetchMediumByInput(input: string): Promise<(Buch | Medium)[]> {
  const response = await fetch(`http://localhost:8080/api/medien/suche?begriff=${encodeURIComponent(input)}`);

  if (!response.ok) {
    throw new Error('Fehler beim Laden der Medien');
  }

  const medien = await response.json();

  // Später erweitern mit E-books etc
  return medien.map((m: any) => {
    switch (m.mediumTyp?.toUpperCase()) {
      case 'BUCH':
        return new Buch(m);
      default:
        return new Medium(m); // Fallback
    }
  });
}
