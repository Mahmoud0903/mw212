import { User } from '../models/user';

export async function fetchUser(id: number) {
  const response = await fetch(`http://localhost:8080/api/nutzer/${id}`);
  const user = await response.json();
  return new User(user);
}
