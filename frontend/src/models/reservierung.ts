import { Medium } from './medium';
import { User } from './user';

export interface Reservierung {
  id: number;
  user: User;
  medium: Medium;
  reservierungsdatum: Date;
  status: string;
  reserviertBis: string;
}
