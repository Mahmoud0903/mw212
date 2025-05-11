import { User } from '../models/user';

export async function fetchUser(id: number) {
  console.log(22);
  const response = await fetch(`http://localhost:8080/api/nutzer/${id}`);
  const user = await response.json();

  console.log(user);
  return new User(user);
}
