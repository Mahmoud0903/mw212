import { Medium, MediumStatus } from './medium';

export class Buch extends Medium {
  isbn: number;
  seitenanzahl: number;

  constructor(buch: {
    mediumId: number;
    titel: string;
    autor: string;
    kategorie: string;
    bildLink: string;
    status: MediumStatus;
    mediumTyp: string;
    isbn: number;
    seitenanzahl: number;
  }) {
    super(buch); // Übergibt die Basisklassenfelder
    this.isbn = buch.isbn;
    this.seitenanzahl = buch.seitenanzahl;
  }
}
