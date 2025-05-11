import { Medium } from 'src/models/medium';

export async function fetchMedium(id: number): Promise<Medium> {
  const response = await fetch(`http://localhost:8080/api/medium/${id}`);

  const medium = await response.json();

  console.log(medium);
  return new Medium(medium);
}
